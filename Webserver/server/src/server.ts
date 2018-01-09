import * as express from 'express';
import * as path from 'path';
import * as bodyparser from 'body-parser';
import * as http from 'http';
import * as fs from 'fs';
import * as ejwt from 'express-jwt';

import { log } from './main';
import { ApiRoutes } from './api-routes';
import { AppRoutes } from './app-routes';

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
    // tslint:disable-next-line:max-line-length
    // this._express.use(ejwt({ secret: this._publkey }).unless({ path: ['/api/extensions', '/api/ip', '/api/login', '/api/version', '/api/bootstrap', '/api/node_modules'] }));
    // this._express.use((req, res, next) => {
    //   log.fine(req.user);
    //   next();
    // });

    this._express.use('/api', ApiRoutes.ApiRouter.Routes);
    this._express.use('/', AppRoutes.AppRouter.Routes);
  }

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
