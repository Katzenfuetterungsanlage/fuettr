import { log } from './main';
import * as mongodb from 'mongodb';
import * as fs from 'fs';
import * as path from 'path';

export class FuettrDB {
  public static async createInstance(socket = 'localhost:27017'): Promise<FuettrDB> {
    if (FuettrDB._instance) {
      throw Error('instance already created');
    }
    const instance = new FuettrDB();
    await instance.start();
    FuettrDB._instance = instance;
    return FuettrDB._instance;
  }

  private static _instance: FuettrDB;

  public static get Instance(): FuettrDB {
    if (!FuettrDB._instance) {
      throw new Error('instance not created yet');
    }
    return FuettrDB._instance;
  }

  private _times: mongodb.Collection;
  private _info: mongodb.Collection;
  private _hardware: mongodb.Collection;
  private _users: mongodb.Collection;
  private _serialnumber = parseInt(fs.readFileSync(path.join(__dirname, '../../../seriennummer')).toString());

  private constructor() {}

  public async getTimes(): Promise<string> {
    const Times = await this._times.find({ identifier: 'Times' }).toArray();
    return Times[0];
  }

  public async getStatus(): Promise<string> {
    const Status = await this._info.find({ identifier: 'Status' }).toArray();
    return Status[0];
  }

  public async getInfo(): Promise<string> {
    const Info = await this._info.find({ identifier: 'Info' }).toArray();
    return Info[0];
  }

  public async getPositions(): Promise<string> {
    const Positions = await this._hardware.find({ identifier: 'Hardware' }).toArray();
    return Positions[0];
  }

  public async getUsers(): Promise<string[]> {
    const Users = await this._users.find({ identifier: 'User' }).toArray();
    return Users;
  }

  public async putTimes(times: Object) {
    this._times.updateOne({ identifier: 'Times' }, { $set: times });
  }

  private async start() {
    const url = 'mongodb://localhost:27017/fuettr';
    try {
      const dbServer = await mongodb.MongoClient.connect(url);
      const dbFuettr = await dbServer.db('fuettr');
      const collTimes = await dbFuettr.collection('data_times');
      const collUsers = await dbFuettr.collection('data_user');
      const collInfo = await dbFuettr.collection('data_info');
      const collHardware = await dbFuettr.collection('data_hardware');

      let sizetimes, sizeinfo, sizehardware;
      try {
        sizetimes = await collTimes.count({});
      } catch {
        sizetimes = 0;
      }
      try {
        sizeinfo = await collInfo.count({});
      } catch {
        sizeinfo = 0;
      }
      try {
        sizehardware = await collHardware.count({});
      } catch {
        sizehardware = 0;
      }

      if (sizetimes === 0) {
        const mockData = [
          {
            identifier: 'Times',
            time1: '--:--',
            time2: '--:--',
            time3: '--:--',
            time4: '--:--',
            time1_active: false,
            time2_active: false,
            time3_active: false,
            time4_active: false
          }
        ];
        await collTimes.insertMany(mockData);
      }
      if (sizeinfo === 0) {
        const mockData = [
          { identifier: 'Status', lastFeeding: '', nextFeeding: '', nextFeedingIn: '', machineState: '' },
          { identifier: 'Info', serialnumber: this._serialnumber, internal: '', wlanState: '' }
        ];
        await collInfo.insertMany(mockData);
      }
      if (sizehardware === 0) {
        const mockData = [{ identifier: 'Hardware', motor1: '', motor2: '', sensor1: '', sensor2: '' }];
        await collHardware.insertMany(mockData);
      }

      this._times = collTimes;
      this._info = collInfo;
      this._hardware = collHardware;
      this._users = collUsers;
      log.info('Database connected.');
    } catch (err) {
      throw err;
    }
  }
}
