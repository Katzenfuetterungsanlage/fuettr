import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';

import * as itf from '../interfaces';

import 'rxjs/add/operator/toPromise';

@Injectable()
export class HttpgetService {
  private api = '/api/callMeMaybe?q=';
  private ip = '/api/ip';

  constructor(private httpClient: HttpClient) {}

  public async get(resource: string, options?: { headers?: HttpHeaders }): Promise<any> {
    return await this.httpGet(resource, options).catch(this.handleError);
  }

  public async getIp(): Promise<itf.Ip> {
    return this.httpClient
      .get(this.ip)
      .toPromise()
      .catch(this.handleError);
  }

  private async httpGet(resource: string, options?: { headers?: HttpHeaders }): Promise<any> {
    if (!resource) {
      return Promise.reject(new Error('invalid arguments'));
    }
    const headers = options && options.headers ? options.headers : new HttpHeaders({ 'Content-Type': 'application/json' });
    const httpClientOptions = { headers: headers };
    return await this.httpClient
      .get(this.api + resource, httpClientOptions)
      .toPromise()
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error);
    return Promise.reject(error.message || error);
  }
}
