import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpgetService } from '../services/httpget.service';
import { HttpputService } from '../services/httpput.service';
import { AppComponent } from '../app.component';
import * as itf from '../interfaces';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html'
})
export class HomeComponent implements OnInit, OnDestroy {
  public warning_messages: itf.Warning[];
  public error_messages: itf.Error[];
  public last_time: string;
  public next_time: string;
  public next_time_in: string;
  public machine_state: string;

  public time1: string;
  public time2: string;
  public time3: string;
  public time4: string;

  public time1_show = false;
  public time2_show = false;
  public time3_show = false;
  public time4_show = false;

  private time;
  private call;

  public constructor(private httpgetService: HttpgetService, private httpputService: HttpputService, private app: AppComponent) { }

  ngOnInit(): void {
    this.callMeMaybe();

    this.call = setInterval(() => {
      this.callMeMaybe();
    }, 30000);
    this.time = setTimeout(() => {
      this.app.navShow = false;
    }, 0);
  }

  ngOnDestroy(): void {
    if (this.time !== undefined) {
      clearInterval(this.time);
      this.time = undefined;
    }
    if (this.call !== undefined) {
      clearInterval(this.call);
      this.call = undefined;
    }
  }

  callMeMaybe(): void {
    this.httpgetService.getWarnings().then(res => {
      this.warning_messages = res.warnings;
    });

    this.httpgetService.getErrors().then(res => {
      this.error_messages = res.errors;
    });

    this.httpgetService.getTimes().then(res => {
      this.time1 = res.time1;
      this.time2 = res.time2;
      this.time3 = res.time3;
      this.time4 = res.time4;
      this.time1_show = res.time1_active;
      this.time2_show = res.time2_active;
      this.time3_show = res.time3_active;
      this.time4_show = res.time4_active;
    });

    this.httpgetService.getStatus().then(res => {
      this.last_time = res.lastFeeding;
      this.next_time = res.nextFeeding;
      this.next_time_in = res.nextFeedingIn;
      this.machine_state = res.machineState;
    });
  }

  ackwarn(warning: itf.Warning) {
    const id: itf.AckWarn = { id: warning.id };
    this.httpputService.ackErr(id).subscribe();
  }

  ackerr(error: itf.Error) {
    const id: itf.AckErr = { id: error.id };
    this.httpputService.ackErr(id).subscribe();
  }
}
