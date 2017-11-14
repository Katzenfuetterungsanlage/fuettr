import * as express from 'express';
import * as path from 'path';

import * as http from 'http';
import * as child from 'child_process';
import * as fs from 'fs';



process.env['DEBUG'] = '*';
process.env['DEBUG_COLORS'] = "true";
process.env['DEBUG_STREAM'] = "stdout";
import * as debugsx from 'debug-sx';
const debug: debugsx.IFullLogger = debugsx.createFullLogger('main');
let consolelogger: debugsx.IHandler = debugsx.createConsoleHandler('stdout', "*");
let date = new Date();
let filelogger: debugsx.IHandler = debugsx.createFileHandler(
  'logs/' + date.getFullYear() + '-' + date.getUTCMonth() + '-' + date.getUTCDay() + '_' + date.getUTCHours() + '-' + date.getUTCMinutes() + '-' + date.getUTCSeconds() + '.log', );

debugsx.addHandler(consolelogger, filelogger);

const serverApp = express();
serverApp.set('views', path.join(__dirname, '/views'));
const pugEngine = serverApp.set('view engine', 'pug');
pugEngine.locals.pretty = true;

serverApp.use(logger);
serverApp.use(express.static(path.join(__dirname, '../public')));
serverApp.use('/node_modules', express.static(path.join(__dirname, '../../ng2/node_modules')));
serverApp.use(express.static(path.join(__dirname, '../../ng2/dist')));
serverApp.get('/api/callMeMaybe', callMeMaybe);
serverApp.get('/api/getUpdate', update);
serverApp.get('/api/shutdown', shutdown);
serverApp.get('/api/version', (req, res) => { res.sendFile(path.join(__dirname, '../../../../version.json')); });
serverApp.get('/api/extensions', (req, res) => { res.sendFile(path.join(__dirname, 'views/README.html')); });
serverApp.get('**', (req, res) => { res.sendFile(path.join(__dirname, '../../ng2/dist/index.html')); });
serverApp.use(error404Handler);
serverApp.use(errorHandler);

const port = 80;
const server = http.createServer(serverApp).listen(port);
debug.info('Server running on port ' + port);



function error404Handler(req: express.Request, res: express.Response, next: express.NextFunction) {
  const clientSocket = req.socket.remoteAddress + ':' + req.socket.remotePort;
  debug.warn('Error 404 for %s %s from %s', req.method, req.url, clientSocket);
  res.status(404).sendFile(path.join(__dirname, 'views/error404.html'));
}


function errorHandler(err: express.Errback, req: express.Request, res: express.Response, next: express.NextFunction) {
  const ts = new Date().toLocaleString();
  debug.severe('Error %s\n%e', ts, err);
  res.status(500).render('error500.pug',
    {
      time: ts,
      href: 'mailto:greflm13@htl-kaindorf.ac.at?subject=Füttr server failed ' + ts,
      serveradmin: 'Florian Greistorfer',
    });
}


function getFromJava(res: express.Response, path: string) {
  http.get({ port: 666, host: 'localhost', path: '/' + path }, (resp) => {
    let data = '';

    resp.on('data', (chunk) => {
      data += chunk;
    });

    resp.on('end', () => {
      try {
        res.json(JSON.parse(data));
      } catch (err) {
        debug.severe(err);
      }
    });

  }).on("error", (err) => {
    debug.severe(err);
  });
}


function callMeMaybe(req: express.Request, res: express.Response, next: express.NextFunction) {
  switch (req.query.q) {
    case 'warnings': {
      res.sendFile(path.join(__dirname, '../testfiles/warnings.json'));
      // getFromJava(res, 'warnings');
      break;
    }

    case 'errors': {
      res.sendFile(path.join(__dirname, '../testfiles/errors.json'));
      // getFromJava(res, 'errors');
      break;
    }

    case 'times': {
      res.sendFile(path.join(__dirname, '../testfiles/times.json'));
      // getFromJava(res, 'times');
      break;
    }

    case 'status': {
      res.sendFile(path.join(__dirname, '../testfiles/status.json'));
      // getFromJava(res, 'status');
      break;
    }

    case 'info': {
      res.sendFile(path.join(__dirname, '../testfiles/info.json'));
      // getFromJava(res, 'info');
      break;
    }

    default: { error404Handler(req, res, next); }
  }
}


function update(req: express.Request, res: express.Response, next: express.NextFunction) {
  res.sendFile(path.join(__dirname, 'views/update.html'))
  child.exec(`cd .. &&git reset --hard && git pull && sudo npm-install-missing`, (error, stdout, stderr) => {
    if (stdout !== '') {
      debug.info(stdout);
    }
    if (error !== null) {
      debug.warn(error);
    }
    child.exec(`cd ../ng2 && sudo npm-install-missing`, (error, stdout, stderr) => {
      if (stdout !== '') {
        debug.info(stdout);
      }
      if (error !== null) {
        debug.warn(error);
      }
      child.exec(`sudo reboot`, (error, stdout, stderr) => {
        if (stdout !== '') {
          debug.info(stdout);
        }
        if (error !== null) {
          debug.warn(error);
        }
      });
    });
  });

}


function shutdown() {
  child.exec('sudo poweroff');
}


function logger(req: express.Request, res: express.Response, next: express.NextFunction) {
  const clientSocket = req.socket.remoteAddress + ':' + req.socket.remotePort;
  debug.info(req.method, req.url, clientSocket);
  next();
}