import * as express from 'express';
import * as path from 'path';
import * as bodyparser from 'body-parser';
import * as debugsx from 'debug-sx';
import * as jwt from 'jsonwebtoken';
import * as ejwt from 'express-jwt';

import * as http from 'http';
import * as https from 'https';
import * as child from 'child_process';
import * as fs from 'fs';
import { log } from './main';
import { FuettrDB } from './fuettr-db';

export class ApiRoutes {
  // #region Getter

  private static _apiRouter: ApiRoutes;

  public static get ApiRouter(): ApiRoutes {
    if (ApiRoutes._apiRouter === undefined) {
      ApiRoutes._apiRouter = new ApiRoutes();
    }
    return ApiRoutes._apiRouter;
  }

  public get Routes(): express.Router {
    return this._routes;
  }

  // #endregion

  private _routes: express.Router;
  private _publkey: Buffer;
  private _privkey: Buffer;
  private token: string;

  public constructor() {
    this._publkey = fs.readFileSync(path.join(__dirname, '../keys/server-public.pem'));
    this._privkey = fs.readFileSync(path.join(__dirname, '../keys/server-private.pem'));
    this._routes = express.Router();

    this._routes.use('/node_modules', express.static(path.join(__dirname, '../node_modules')));
    this._routes.post('/putMeHere', (req, res, next) => this.putMeHere(req, res, next));
    this._routes.get('/callMeMaybe', (req, res, next) => this.callMeMaybe(req, res, next));
    this._routes.get('/getUpdate', (req, res, next) => this.update(req, res, next));
    this._routes.get('/shutdown', this.shutdown);
    this._routes.get('/ip', (req, res, next) => this.getIp(req, res, next));
    this._routes.get('/version', (req, res) => {
      res.sendFile(path.join(__dirname, '../../../version.json'));
    });
    this._routes.use((req, res, next) => this.error404Handler(req, res, next));
    // tslint:disable-next-line:max-line-length
    this._routes.use((err: express.Errback, req: express.Request, res: express.Response, next: express.NextFunction) => this.errorHandler(err, req, res, next));
  }

  // #region Functions

  public error404Handler(req: express.Request, res: express.Response, next: express.NextFunction) {
    const clientSocket = req.socket.remoteAddress + ':' + req.socket.remotePort;
    log.warn('Error 404 for %s %s from %s', req.method, req.url, clientSocket);
    res.status(404).sendFile(path.join(__dirname, 'views/error404.html'));
  }

  public errorHandler(err: express.Errback, req: express.Request, res: express.Response, next: express.NextFunction) {
    const ts = new Date().toLocaleString();
    log.warn('Error %s\n%e', ts, err);
    res.status(500).render('error500.pug', {
      time: ts,
      err: err,
      href: 'mailto:greflm13@htl-kaindorf.ac.at?subject=Füttr server failed ' + ts,
      serveradmin: 'Florian Greistorfer'
    });
  }

  public getFromJava(res: express.Response, path: string) {
    http
      .get({ port: 62222, host: 'localhost', path: '/' + path }, resp => {
        let data = '';

        resp.on('data', chunk => {
          data += chunk;
        });

        resp.on('end', () => {
          try {
            res.json(JSON.parse(data));
          } catch (err) {
            log.warn(err);
          }
        });
      })
      .on('error', err => {
        log.warn(err);
      });
  }

  public async callMeMaybe(req: express.Request, res: express.Response, next: express.NextFunction) {
    switch (req.query.q) {
      case 'errors_warnings': {
        this.getFromJava(res, 'errors_warnings');
        // res.send(JSON.stringify({}));
        break;
      }

      case 'times': {
        const Times = await FuettrDB.Instance.getTimes();
        res.send(Times);
        break;
      }

      case 'status': {
        const Status = await FuettrDB.Instance.getStatus();
        res.send(Status);
        break;
      }

      case 'info': {
        const Info = await FuettrDB.Instance.getInfo();
        res.send(Info);
        break;
      }

      case 'positions': {
        const Positions = await FuettrDB.Instance.getPositions();
        res.send(Positions);
        break;
      }

      default: {
        this.error404Handler(req, res, next);
      }
    }
  }

  public getToJava(path: string, data: string): string {
    const options = {
      host: 'localhost',
      port: 666,
      path: path,
      method: 'PUT'
    };

    let back: string;

    const req = http.request(options, res => {
      res.on('data', chunk => {
        back += chunk;
      });
    });

    req.on('error', error => {
      log.warn(error.message);
    });

    req.write(data);
    req.end();
    return back;
  }

  public async putMeHere(req: express.Request, res: express.Response, next: express.NextFunction) {
    const token = <string>req.headers.authorization.slice(7);
    let auth;
    await jwt.verify(token, this._publkey, err => {
      if (err != undefined) {
        log.fine(err);
        auth = false;
        res.sendStatus(401);
        return;
      }
      auth = true;
    });
    if (auth) {
      switch (req.query.q) {
        case 'times': {
          await FuettrDB.Instance.putTimes(req.body);
          await setTimeout(() => {}, 10);
          const Times = await FuettrDB.Instance.getTimes();
          res.send(Times);
          break;
        }

        case 'changeState': {
          this.getToJava('/ChangeMachineState', JSON.stringify(req.body));
          break;
        }

        default: {
          this.error404Handler(req, res, next);
        }
      }
    }
  }

  public update(req: express.Request, res: express.Response, next: express.NextFunction) {
    res.sendFile(path.join(__dirname, 'views/update.html'));
    child.exec(`cd .. && git reset --hard && git pull && sudo npm-install-missing`, (error, stdout, stderr) => {
      if (stdout !== '') {
        log.info(stdout);
      }
      if (error !== null) {
        log.warn(error);
      }
      child.exec(`cd ../ngx && sudo npm-install-missing`, (error, stdout, stderr) => {
        if (stdout !== '') {
          log.info(stdout);
        }
        if (error !== null) {
          log.warn(error);
        }
        child.exec(`sudo reboot`, (error, stdout, stderr) => {
          if (stdout !== '') {
            log.info(stdout);
          }
          if (error !== null) {
            log.warn(error);
          }
        });
      });
    });
  }

  public shutdown() {
    child.exec('sudo poweroff', error => {
      log.warn(error);
    });
  }

  public getIp(req: express.Request, res: express.Response, next: express.NextFunction) {
    http
      .get({ port: 80, host: 'api.ipify.org', path: '/?format=json' }, resp => {
        let data = '';

        resp.on('data', chunk => {
          data += chunk;
        });

        resp.on('end', () => {
          try {
            res.json(JSON.parse(data));
          } catch (err) {
            log.warn(err);
          }
        });
      })
      .on('error', err => {
        log.warn(err);
      });
  }

  // #endregion
}
