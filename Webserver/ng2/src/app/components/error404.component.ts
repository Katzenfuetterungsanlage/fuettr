import { Component, Inject, Input } from '@angular/core';
import { DOCUMENT } from '@angular/common';
import { AppComponent } from '../app.component';
import { Router } from '@angular/router/src/router';
import { OnInit } from '@angular/core/src/metadata/lifecycle_hooks';

@Component({
  selector: 'app-error404',
  templateUrl: './error404.component.html'
})
export class Error404Component {
  public url: string;

  constructor(
    @Inject(DOCUMENT) private document: Document,
    private app: AppComponent
  ) {
    this.url = this.document.location.href;
    app.lic();
    this.app.navShow = false;
  }
}
