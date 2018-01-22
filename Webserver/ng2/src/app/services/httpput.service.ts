import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import 'rxjs/Rx';

import * as itf from '../interfaces';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class HttpputService {
  private api = '/api/putMeHere?q=';
  private headers = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) {}

  putTimes(times): Promise<itf.Times> {
    return this.http
      .post(this.api + 'times', JSON.stringify(times), {
        headers: this.headers
      })
      .toPromise()
      .catch(this.handleError);
  }

  login(user): Promise<itf.Login> {
    return this.http
      .post('/login', JSON.stringify(user), { headers: this.headers })
      .toPromise()
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
