import { Component, OnInit } from '@angular/core';

import { UpdateService } from '../services/update.service';
import { HttpgetService } from '../services/httpget.service';
import { AppComponent } from '../app.component';

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

  constructor(private updateService: UpdateService, private httpgetService: HttpgetService, private app: AppComponent) {}

  ngOnInit() {
    this.updateService.getVersion().then(lVersion => {
      this.version = lVersion.version.toString();
    });

    this.httpgetService.getInfo().then(res => {
      this.serialnumber = res.serialnumber;
      this.processor = res.internal;
      this.wlanstate = res.wlanState;
    });

    this.httpgetService.getIp().then(res => {
      this.ipadress = res.ip;
    });
    this.app.lic();
    setTimeout(() => {
      this.app.navShow = false;
    }, 0);
  }
}
