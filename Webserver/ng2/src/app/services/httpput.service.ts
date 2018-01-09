import { Injectable } from '@angular/core';
import { Http, Headers, Response } from '@angular/http';

import 'rxjs/Rx';

import * as itf from '../interfaces';

@Injectable()
export class HttpputService {
  private api = '/api/putMeHere?q=';
  private headers = new Headers({ 'Content-Type': 'application/json' });

  constructor(private http: Http) {}

  putTimes(times): Promise<itf.Times> {
    return this.http
      .post(this.api + 'times', JSON.stringify(times), {
        headers: this.headers
      })
      .toPromise()
      .then(res => res.json() as itf.Times)
      .catch(this.handleError);
  }

  ackErr(data) {
    return this.http.put(this.api + 'ackErr', JSON.stringify(data), {
      headers: this.headers
    });
  }

  ackWarn(data) {
    return this.http.put(this.api + 'ackWarn', JSON.stringify(data), {
      headers: this.headers
    });
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error);
    return Promise.reject(error.message || error);
  }
}
