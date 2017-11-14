import { Component, OnInit } from '@angular/core';
import { HttpgetService } from './httpget.service';
import * as itf from './interfaces';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
})
export class HomeComponent implements OnInit {
  private warning_messages: itf.Warning[];
  private error_messages: itf.Error[];
  private last_time: string;
  private next_time: string;
  private next_time_in: string;
  private machine_state: string;

  private time1: string;
  private time2: string;
  private time3: string;
  private time4: string;

  private time1_show = false;
  private time2_show = false;
  private time3_show = false;
  private time4_show = false;

  private warning = false;
  private danger = false;

  public constructor(private httpgetService: HttpgetService) { }

  ngOnInit(): void {

    this.callMeMaybe();

    setInterval(() => {this.callMeMaybe(); }, 30000);
  }

  callMeMaybe(): void {
    this.httpgetService.getWarnings().then((res) => {
      if (res.warnings !== undefined) {
        this.warning_messages = res.warnings;
        this.warning = true;
      }
    });

    this.httpgetService.getErrors().then((res) => {
      if (res.errors !== undefined) {
        this.error_messages = res.errors;
        this.danger = true;
      }
    });

    this.httpgetService.getTimes().then((res) => {
      if (res.time1 !== '--:--') {
        this.time1 = res.time1;
        this.time1_show = true;
      }
      if (res.time2 !== '--:--') {
        this.time2 = res.time2;
        this.time2_show = true;
      }
      if (res.time3 !== '--:--') {
        this.time3 = res.time3;
        this.time3_show = true;
      }
      if (res.time4 !== '--:--') {
        this.time4 = res.time4;
        this.time4_show = true;
      }
    });

    this.httpgetService.getStatus().then((res) => {
      this.last_time = res.last_time;
      this.next_time = res.next_time;
      this.next_time_in = res.next_time_in;
      this.machine_state = res.machine_state;
  });
  }
}
