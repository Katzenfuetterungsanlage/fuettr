import * as express from 'express';
import * as path from 'path';
import * as fs from 'fs';

import { Server } from './server';
import { log } from './main';

export class AppRoutes {
  // #region Static Methods

  private static _appRouter: AppRoutes;

  public static get AppRouter(): AppRoutes {
    if (AppRoutes._appRouter === undefined) {
      AppRoutes._appRouter = new AppRoutes();
    }
    return AppRoutes._appRouter;
  }

  // #endregion

  private _routes: express.Router;

  public constructor() {
    this._routes = express();

    this._routes.use(express.static(path.join(__dirname, '../../ng2/dist')));
    this._routes.get('**', (req, res, next) => {
      res.sendFile(path.join(__dirname, '../../ng2/dist/index.html'));
    });
    this._routes.use((req, res, next) => this.error404Handler(req, res, next));
    // tslint:disable-next-line:max-line-length
    this._routes.use((err: express.Errback, req: express.Request, res: express.Response, next: express.NextFunction) =>
      this.errorHandler(err, req, res, next)
    );
  }

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

  public get Routes(): express.Router {
    return this._routes;
  }
}
