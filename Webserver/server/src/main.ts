process.env['DEBUG'] = '*::INFO, *::WARN, *::ERR, *::SEVERE, *::';
process.env['DEBUG_COLORS'] = 'true';
process.env['DEBUG_STREAM'] = 'stdout';

import * as express from 'express';
import * as path from 'path';
import * as bodyparser from 'body-parser';
import * as debugsx from 'debug-sx';

import * as http from 'http';
import * as https from 'https';
import * as child from 'child_process';
import * as fs from 'fs';
import { Server } from './server';
import { FuettrDB } from './fuettr-db';

const date = new Date();
export const log: debugsx.IFullLogger = debugsx.createFullLogger('main');
const consolelogger: debugsx.IHandler = debugsx.createConsoleHandler('stdout', '*::INFO, *::FINE, *::SEVERE, *::ERR, *::WARN', '-*', [
  { level: 'INFO', color: 'cyan', inverse: true },
  { level: 'FINE', color: 'white', inverse: true },
  { level: 'SEVERE', color: 'red', inverse: true },
  { level: 'ERR', color: 'red', inverse: true },
  { level: 'WARN', color: 'magenta', inverse: true }
]);
let filelogger: debugsx.IHandler;
if (process.platform !== 'win32') {
  // tslint:disable-next-line:max-line-length
  filelogger = debugsx.createFileHandler(
    '/var/log/fuettr/' + 'server_' + date.toLocaleDateString() + '_' + date.getHours() + '.' + date.getMinutes() + '.' + date.getSeconds() + '.log',
    '*::INFO, *::FINE, *::SEVERE, *::ERR, *::WARN',
    '-*',
    [
      { level: 'INFO', color: 'cyan', inverse: true },
      { level: 'FINE', color: 'white', inverse: true },
      { level: 'SEVERE', color: 'red', inverse: true },
      { level: 'ERR', color: 'red', inverse: true },
      { level: 'WARN', color: 'magenta', inverse: true }
    ]
  );
} else {
  filelogger = debugsx.createFileHandler(
    'log/' + 'server_' + date.toLocaleDateString() + '_' + date.getHours() + '.' + date.getMinutes() + '.' + date.getSeconds() + '.log',
    '*::INFO, *::FINE, *::SEVERE, *::ERR, *::WARN',
    '-*',
    [
      { level: 'INFO', color: 'cyan', inverse: true },
      { level: 'FINE', color: 'white', inverse: true },
      { level: 'SEVERE', color: 'red', inverse: true },
      { level: 'ERR', color: 'red', inverse: true },
      { level: 'WARN', color: 'magenta', inverse: true }
    ]
  );
}
debugsx.addHandler(consolelogger, filelogger);

const port = 17325;

class Main {
  constructor() {}

  public async init() {
    const db = await FuettrDB.createInstance().catch(err => {
      log.severe(err);
      process.exit();
    });
    Server.Instance.start(port).catch(err => {
      log.severe(err);
      process.exit();
    });
  }
}

async function main() {
  const m = new Main();
  await m.init();
}

main();
