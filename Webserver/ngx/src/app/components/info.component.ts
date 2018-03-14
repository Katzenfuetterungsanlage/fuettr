import { Component, OnInit } from '@angular/core';

import { UpdateService } from '../services/update.service';
import { HttpService } from '../services/http.service';
import { AppComponent } from '../app.component';

import * as itf from '../interfaces';

@Component({
  selector: 'app-info',
  templateUrl: './info.component.html'
})
export class InfoComponent implements OnInit {
  public serialnumber: string;
  public processor: string;
  public wlanstate: string;
  public ipadress: string;
  public version: string;

  constructor(private updateService: UpdateService, private httpService: HttpService, private app: AppComponent) { }

  public ngOnInit() {
    this.updateService.getVersion().then(lVersion => {
      this.version = lVersion.version.toString();
    });

    this.httpService.get('info').then((res: itf.Info) => {
      this.serialnumber = res.serialnumber;
      this.processor = res.internal;
      this.wlanstate = res.wlanState;
    });

    this.httpService.getIp().then(res => {
      this.ipadress = res.ip;
    });
    this.app.lic();
    setTimeout(() => {
      this.app.navShow = false;
    }, 0);
  }
}
