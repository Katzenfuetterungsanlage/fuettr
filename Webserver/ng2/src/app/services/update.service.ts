import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Version } from '../interfaces';

import 'rxjs/add/operator/toPromise';

@Injectable()
export class UpdateService {
  private getUrl = 'api/getUpdate';
  private checkUrl = 'https://raw.githubusercontent.com/Katzenfuetterungsanlage/fuettr/master/version.json';
  private lVersionUrl = 'api/version';
  private shutdownUrl = 'api/shutdown';

  constructor(private http: HttpClient) { }

  async getUpdate(): Promise<void> {
    return this.http.get(this.getUrl)
      .toPromise()
      .catch(this.handleError);
  }

  shutdown(): Promise<void> {
    return this.http.get(this.shutdownUrl)
      .toPromise()
      .catch(this.handleError);
  }

  checkUpdate(): Promise<Version> {
    return this.http.get(this.checkUrl)
      .toPromise()
      .catch(this.handleError);
  }

  getVersion(): Promise<Version> {
    return this.http.get(this.lVersionUrl)
      .toPromise()
      .catch(this.handleError);
  }

  stillThere(): Promise<any> {
    return this.http.get(this.lVersionUrl).toPromise();
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error);
    return Promise.reject(error.message || error);
  }
}

