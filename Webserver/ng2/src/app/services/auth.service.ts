import { Injectable } from '@angular/core';
import { Subject } from 'rxjs/Subject';
import { User } from '../interfaces';

@Injectable()
export class AuthService {
  private _htlid: string;
  private _token: string;
  private _user: User;

  private _htlidSource = new Subject<string>();
  private _tokenSource = new Subject<string>();
  private _userSource = new Subject<User>();

  public htlidObservable = this._htlidSource.asObservable();
  public tokenObservable = this._tokenSource.asObservable();
  public userObservable = this._userSource.asObservable();

  public set htlid(value: string) {
    if (value !== undefined && !value) {
      throw new Error('invalid value for htlid');
    }
    this._htlid = value;
    this._htlidSource.next(value);
  }

  public get htlid(): string {
    return this._htlid;
  }

  public set token(value: string) {
    if (value !== undefined && !value) {
      throw new Error('invalid value for token');
    }
    this._token = value;
    this._tokenSource.next(value);
  }

  public get token(): string {
    return this._token;
  }

//   public set user(value: User) {
//     if (value !== undefined && !value) {
//       throw new Error('invalid value for user');
//     }
//     if (this._user && this._user.isEqual(value)) {
//       return;
//     }
//     this._user = value;
//     this._userSource.next(value);
//   }
 }
