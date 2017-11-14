import { Component, OnInit, Inject } from '@angular/core';
import { DOCUMENT } from '@angular/common';

@Component({
  selector: 'app-error404',
  templateUrl: './error404.component.html',
})
export class Error404Component implements OnInit {

  private url: string;

  constructor(@Inject(DOCUMENT) private document: Document) {
    this.url = this.document.location.href;
   }

  ngOnInit() {
  }

}
