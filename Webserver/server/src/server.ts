import * as express from 'express';
import * as path from 'path';
import * as bodyparser from 'body-parser';
import * as http from 'http';
import * as fs from 'fs';
import * as jwt from 'jsonwebtoken';
import { SHA512 } from 'crypto-js';
import { Login } from './interfaces';

import { log } from './main';
import { ApiRoutes } from './api-routes';
import { AppRoutes } from './app-routes';
import { FuettrDB } from './fuettr-db';

export class Server {
  // #region Singleton

  private static _instance: Server;

  public static get Instance(): Server {
    if (Server._instance === undefined) {
      Server._instance = new Server();
    }
    return Server._instance;
  }

  // #endregion

  _express = express();
  private _publkey: Buffer;
  private _privkey: Buffer;

  constructor() {
    this._publkey = fs.readFileSync(path.join(__dirname, '../keys/server-public.pem'));
    this._privkey = fs.readFileSync(path.join(__dirname, '../keys/server-private.pem'));

    this._express.use(bodyparser.json());
    this._express.use(bodyparser.urlencoded({ extended: true }));
    this._express.set('views', path.join(__dirname, '/views'));
    const pugEngine = this._express.set('view engine', 'pug');
    pugEngine.locals.pretty = true;
    this._express.use(this.logger);
    this._express.use(express.static(path.join(__dirname, '../public')));
    this._express.use('/assets', express.static(path.join(__dirname, '../../ng2/dist/assets')));
    this._express.post('/login', (req, res, next) => this.login(req, res, next));
    this._express.post('/logout', (req, res, next) => this.logout(req, res, next));
    this._express.use('/api', ApiRoutes.ApiRouter.Routes);
    this._express.use('/', AppRoutes.AppRouter.Routes);
  }

  public async login(req: express.Request, res: express.Response, next: express.NextFunction) {
    let User: Login = { token: '', isLoggedIn: false };
    const username = req.body.user;
    // const userpass = SHA512(req.body.password).toString();
    const userpass = req.body.password;
    const Users = await FuettrDB.Instance.getUsers();
    for (let i = 0; i < Users.length; i++) {
      let User = JSON.parse(JSON.stringify(Users[i]));
      let pass = User.user_password;
      let name = User.user_name;
      if (userpass === pass && username === name) {
        jwt.sign({ user: username }, this._privkey, { expiresIn: '10h', algorithm: 'RS256' }, (err, token) => {
          if (err != undefined) {
            log.warn(err);
          }
          if (token != undefined) {
            User.token = token;
            User.isLoggedIn = true;
            res.send(JSON.stringify(User));
          }
        });
      } else {
        User.token = '';
        User.isLoggedIn = false;
        res.status(401).send(JSON.stringify(User));
      }
    }
  }

  public logout(req: express.Request, res: express.Response, next: express.NextFunction) {}

  public logger(req: express.Request, res: express.Response, next: express.NextFunction) {
    const clientSocket = req.socket.remoteAddress + ':' + req.socket.remotePort;
    log.info(req.method, req.url, clientSocket);
    next();
  }

  public start(port: number): Promise<Server> {
    return new Promise<Server>((resolve, reject) => {
      const server = http.createServer(this._express).listen(port, () => {
        log.info('Server running on port ' + port);
        server.on('close', () => {
          log.fine('Server stopped.');
        });
        server.on('err', err => {
          log.warn(err);
        });
      });
    });
  }
}
