import { Component, OnInit } from '@angular/core';
import { AppComponent } from '../app.component';

import { HttpgetService } from '../services/httpget.service';

import * as itf from '../interfaces';

@Component({
  selector: 'app-position',
  templateUrl: './position.component.html'
})
export class PositionComponent implements OnInit {
  public motor1: string;
  public motor2: string;
  public sensor1: string;
  public sensor2: string;

  constructor(private httpgetService: HttpgetService, private app: AppComponent) { }

  public ngOnInit(): void {
    this.httpgetService.get('positions').then((res: itf.Positions) => {
      this.motor1 = res.motor1;
      this.motor2 = res.motor2;
      this.sensor1 = res.sensor1;
      this.sensor2 = res.sensor2;
    });
    this.app.lic();
    setTimeout(() => {
      this.app.navShow = false;
    }, 0);
  }
}
