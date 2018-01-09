import { Component } from '@angular/core';
import { AppComponent } from '../app.component';

@Component({
  selector: 'app-cat',
  templateUrl: './cat.component.html'
})
export class CatComponent {
  constructor(private app: AppComponent) {}

  back() {
    this.app.back();
  }
}
