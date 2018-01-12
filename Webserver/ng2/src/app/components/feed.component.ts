import { Component, OnInit, trigger, state, animate, transition, style } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

import { HttpgetService } from '../services/httpget.service';
import { HttpputService } from '../services/httpput.service';
import { TimeCalculator } from '../services/time.calculator.service';
import { AppComponent } from '../app.component';

import * as itf from '../interfaces';

@Component({
  selector: 'app-feed',
  templateUrl: './feed.component.html',
  animations: [
    trigger('SaveAnimation', [
      state('false', style({ 'margin-left': '-126px' })),
      state('true', style({ 'margin-left': '0' })),
      transition('* => *', animate('300ms'))
    ])
  ]
})
export class FeedComponent implements OnInit {
  public submit = false;
  public saved = true;
  public failed = false;
  public savedstate = false;
  public time1: string;
  public time2: string;
  public time3: string;
  public time4: string;
  public check1: boolean;
  public check2: boolean;
  public check3: boolean;
  public check4: boolean;
  public doppelpoint1: boolean;
  public doppelpoint2: boolean;
  public doppelpoint3: boolean;
  public doppelpoint4: boolean;

  public time1Minutes: number;
  public time1Valid = false;
  public time1notNull = false;
  public time2Minutes: number;
  public time2ValidMin = false;
  public time2Valid = false;
  public time2notNull = false;
  public time3Minutes: number;
  public time3ValidMin = false;
  public time3Valid = false;
  public time3notNull = false;
  public time4Minutes: number;
  public time4ValidMin = false;
  public time4Valid = false;
  public time4notNull = false;

  constructor(
    private httpgetService: HttpgetService,
    private httpputService: HttpputService,
    private timeCalculator: TimeCalculator,
    private app: AppComponent
  ) { }

  onKey(): void {
    this.doppelpoint();
    this.time1Valid = true;
    this.time2Valid = true;
    this.time3Valid = true;
    this.time4Valid = true;
    this.time1notNull = true;
    this.time2notNull = true;
    this.time3notNull = true;
    this.time4notNull = true;

    this.time1Minutes = this.timeCalculator.toMinutes(this.time1);
    this.time2Minutes = this.timeCalculator.toMinutes(this.time2);
    this.time3Minutes = this.timeCalculator.toMinutes(this.time3);
    this.time4Minutes = this.timeCalculator.toMinutes(this.time4);

    this.time1Valid = this.timeCalculator.isValid(this.time1);
    if (this.check1) {
      if (this.time1Minutes === null) {
        this.time2ValidMin = true;
      } else {
        if (this.check2) {
          this.time2ValidMin = this.time2Minutes > this.time1Minutes;
        } else {
          this.time2ValidMin = true;
        }
      }
      // tslint:disable-next-line:max-line-length
      if ((this.time1[0] === '-' && this.time1[1] === '-' && this.time1[3] === '-' && this.time1[4] === '-') || this.time1 === null || this.time1 === undefined) {
        this.time1notNull = false;
      }
    } else {
      this.time2ValidMin = true;
      this.time1Valid = true;
    }

    this.time2Valid = this.timeCalculator.isValid(this.time2);
    if (this.check2) {
      if (this.time2Minutes === null && this.time1Minutes === null) {
        this.time3ValidMin = true;
      } else {
        if (this.check3) {
          this.time3ValidMin = this.time3Minutes > this.time2Minutes;
        } else {
          this.time3ValidMin = true;
        }
      }
      // tslint:disable-next-line:max-line-length
      if ((this.time2[0] === '-' && this.time2[1] === '-' && this.time2[3] === '-' && this.time2[4] === '-') || this.time2 === null || this.time2 === undefined) {
        this.time2notNull = false;
      }
    } else {
      if (this.check1) {
        if (this.time1Minutes === null) {
          this.time3ValidMin = true;
        } else {
          if (this.check3) {
            this.time3ValidMin = this.time3Minutes > this.time1Minutes;
          } else {
            this.time3ValidMin = true;
          }
        }
      } else {
        this.time3ValidMin = true;
      }
      this.time2Valid = true;
    }

    this.time3Valid = this.timeCalculator.isValid(this.time3);
    if (this.check3) {
      if (this.time3Minutes === null && this.time2Minutes === null && this.time1Minutes === null) {
        this.time4ValidMin = true;
      } else {
        this.time4ValidMin = this.time4Minutes > this.time3Minutes;
      }
      // tslint:disable-next-line:max-line-length
      if ((this.time3[0] === '-' && this.time3[1] === '-' && this.time3[3] === '-' && this.time3[4] === '-') || this.time3 === null || this.time3 === undefined) {
        this.time3notNull = false;
      }
      this.time4Valid = this.timeCalculator.isValid(this.time4);
    } else {
      if (this.check2) {
        if (this.time2Minutes === null && this.time1Minutes === null) {
          this.time4ValidMin = true;
        } else {
          this.time4ValidMin = this.time4Minutes > this.time2Minutes;
        }
      } else {
        if (this.check1) {
          if (this.time1Minutes === null) {
            this.time4ValidMin = true;
          } else {
            this.time4ValidMin = this.time4Minutes > this.time1Minutes;
          }
        } else {
          this.time4ValidMin = true;
        }
      }
      this.time3Valid = true;
    }

    if (!this.check4) {
      this.time4Valid = true;
      this.time4ValidMin = true;
    } else {
      // tslint:disable-next-line:max-line-length
      if ((this.time4[0] === '-' && this.time4[1] === '-' && this.time4[3] === '-' && this.time4[4] === '-') || this.time4 === null || this.time4 === undefined) {
        this.time4notNull = false;
      }
    }
  }

  ngOnInit(): void {
    this.httpgetService.get('times').then((res: itf.Times) => {
      this.time1 = res.time1;
      this.time2 = res.time2;
      this.time3 = res.time3;
      this.time4 = res.time4;
      this.check1 = res.time1_active;
      this.check2 = res.time2_active;
      this.check3 = res.time3_active;
      this.check4 = res.time4_active;

      this.onKey();
      this.app.lic();
      setTimeout(() => {
        this.app.navShow = false;
      }, 0);
    });
  }

  cancel(): void {
    this.httpgetService.get('times').then((res: itf.Times) => {
      this.time1 = res.time1;
      this.time2 = res.time2;
      this.time3 = res.time3;
      this.time4 = res.time4;
      this.check1 = res.time1_active;
      this.check2 = res.time2_active;
      this.check3 = res.time3_active;
      this.check4 = res.time4_active;
      this.onKey();
    });
  }

  save(): void {

    if (this.time1 === '' || this.time1 === null || this.time1 === undefined) { this.time1 = '--:--'; }
    if (this.time2 === '' || this.time2 === null || this.time2 === undefined) { this.time2 = '--:--'; }
    if (this.time3 === '' || this.time3 === null || this.time3 === undefined) { this.time3 = '--:--'; }
    if (this.time4 === '' || this.time4 === null || this.time4 === undefined) { this.time4 = '--:--'; }

    const value = {
      identifier: 'Times',
      time1: this.time1,
      time2: this.time2,
      time3: this.time3,
      time4: this.time4,
      time1_active: this.check1,
      time2_active: this.check2,
      time3_active: this.check3,
      time4_active: this.check4
    };

    this.httpputService
      .putTimes(value)
      .then(res => {
        this.time1 = res.time1;
        this.time2 = res.time2;
        this.time3 = res.time3;
        this.time4 = res.time4;
        this.check1 = res.time1_active;
        this.check2 = res.time2_active;
        this.check3 = res.time3_active;
        this.check4 = res.time4_active;
        this.savedstate = true;
        setTimeout(() => {
          this.savedstate = false;
        }, 1500);
      })
      .catch(() => {
        this.failed = true;
        setTimeout(() => {
          this.failed = false;
        }, 1500);
      });
  }

  doppelpoint(): void {
    if (this.time1.length === 3) {
      this.doppelpoint1 = true;
    }
    if (this.time1.length === 2) {
      if (!this.doppelpoint1) {
        this.time1 += ':';
        this.doppelpoint1 = true;
      }
    }
    if (this.time1.length === 1) {
      this.doppelpoint1 = false;
    }
    if (this.time2.length === 3) {
      this.doppelpoint2 = true;
    }
    if (this.time2.length === 2) {
      if (!this.doppelpoint2) {
        this.time2 += ':';
        this.doppelpoint2 = true;
      }
    }
    if (this.time2.length === 1) {
      this.doppelpoint2 = false;
    }
    if (this.time3.length === 3) {
      this.doppelpoint3 = true;
    }
    if (this.time3.length === 2) {
      if (!this.doppelpoint3) {
        this.time3 += ':';
        this.doppelpoint3 = true;
      }
    }
    if (this.time3.length === 1) {
      this.doppelpoint3 = false;
    }
    if (this.time4.length === 3) {
      this.doppelpoint4 = true;
    }
    if (this.time4.length === 2) {
      if (!this.doppelpoint4) {
        this.time4 += ':';
        this.doppelpoint4 = true;
      }
    }
    if (this.time4.length === 1) {
      this.doppelpoint4 = false;
    }
  }
}
