import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';
import { Version } from '../interfaces';
import { AppComponent } from '../app.component';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { UpdateService } from '../services/update.service';

@Component({
  selector: 'app-update',
  templateUrl: './update.component.html'
})
export class UpdateComponent implements OnInit {
  public show = false;
  public show2 = false;
  public progress = false;
  public updater: string;
  public message: string;
  public newVersion: string;
  public prgbar: string;
  private version: Version;
  private lVersion: Version;
  private t1: number;
  private t2: number;
  private t: number;
  private get2;
  private get1;

  constructor(private updateService: UpdateService, private app: AppComponent, private modalService: NgbModal) { }

  update() {
    this.message = '';
    this.show = false;
    this.show2 = true;
    this.progress = true;
    this.prgbar = 'updating...';
    this.updater = 'in progress...';
    this.updateService.getUpdate();
    const interval = setInterval(() => {
      this.get1 = this.updateService.stillThere().catch(error => {
        console.log('restarting...');
        this.prgbar = 'restarting...';
        this.get2 = this.updateService.stillThere().then(value => {
          console.log('updated...');
          this.message = 'updated';
          clearInterval(interval);
          location.reload();
          this.show2 = false;
          this.progress = false;
          this.updater = '';
        });
      });
    }, 1000);
  }

  shutdown() {
    this.updateService.shutdown();
  }

  open(content) {
    this.modalService.open(content);
  }

  refresh() {
    this.message = 'Checking for updates...';
    this.t1 = Date.now();

    this.updateService
      .checkUpdate()
      .then(version => {
        this.version = version;
        this.updateService
          .getVersion()
          .then(lVersion => {
            this.lVersion = lVersion;
            if (this.version.version !== this.lVersion.version) {
              this.t = this.t2 = Date.now() - this.t1;
              this.message = 'Update found in ' + this.t + 'ms:';
              this.show = true;
              this.newVersion = this.version.version.toString();
            } else {
              this.t = this.t2 = Date.now() - this.t1;

              this.message = 'Up to date in ' + this.t + 'ms';
            }
          })
          .catch(err => {
            alert(err);
          });
      })
      .catch(err => {
        alert(err);
      });
  }

  ngOnInit() {
    this.message = 'Checking for updates...';
    this.t1 = this.t1 = Date.now();
    this.app.lic();
    setTimeout(() => {
      this.app.navShow = false;
    }, 0);

    this.updateService
      .checkUpdate()
      .then(version => {
        this.version = version;
        this.updateService
          .getVersion()
          .then(lVersion => {
            this.lVersion = lVersion;
            if (this.version.version !== this.lVersion.version) {
              this.t = this.t2 = Date.now() - this.t1;
              this.message = 'Update found in ' + this.t + 'ms:';
              this.show = true;
              this.newVersion = this.version.version.toString();
            } else {
              this.t = this.t2 = Date.now() - this.t1;

              this.message = 'Up to date in ' + this.t + 'ms';
            }
          })
          .catch(err => {
            alert(err);
          });
      })
      .catch(err => {
        alert(err);
      });
  }
}
