import { Injectable } from '@angular/core';
import { Http } from '@angular/http';

import * as itf from '../interfaces';

import 'rxjs/add/operator/toPromise';

@Injectable()
export class HttpgetService {

  private api = '/api/callMeMaybe?q=';
  private ip = '/api/ip';

  constructor(private http: Http) { }

  getWarnings(): Promise<itf.Warnings> {
    return this.http.get(this.api + 'warnings')
      .toPromise()
      .then(response => response.json() as itf.Warnings)
      .catch(this.handleError);
  }

  getErrors(): Promise<itf.Errors> {
    return this.http.get(this.api + 'errors')
      .toPromise()
      .then(response => response.json() as itf.Errors)
      .catch(this.handleError);
  }

  getTimes(): Promise<itf.Times> {
    return this.http.get(this.api + 'times')
      .toPromise()
      .then(response => response.json() as itf.Times)
      .catch(this.handleError);
  }

  getStatus(): Promise<itf.Status> {
    return this.http.get(this.api + 'status')
      .toPromise()
      .then(response => response.json() as itf.Status)
      .catch(this.handleError);
  }

  getInfo(): Promise<itf.Info> {
    return this.http.get(this.api + 'info')
      .toPromise()
      .then(response => response.json() as itf.Info)
      .catch(this.handleError);
  }

  getIp(): Promise<itf.Ip> {
    return this.http.get(this.ip)
      .toPromise()
      .then(response => response.json() as itf.Ip)
      .catch(this.handleError);
  }

  getPositions(): Promise<itf.Positions> {
    return this.http.get(this.api + 'positions')
      .toPromise()
      .then(response => response.json() as itf.Positions)
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error);
    return Promise.reject(error.message || error);
  }
}
