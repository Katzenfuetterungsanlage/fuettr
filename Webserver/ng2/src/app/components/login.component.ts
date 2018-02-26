import { Component, LOCALE_ID, Inject } from '@angular/core';

import { HttpputService } from '../services/httpput.service';
import { AuthService } from '../services/auth.service';

import * as itf from '../interfaces';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent {
  public fail = false;
  public languages = [{ code: 'en', img: 'assets/en.png' }, { code: 'de', img: 'assets/de.png' }];

  constructor(private authService: AuthService, private router: Router, @Inject(LOCALE_ID) protected localeId: string) {}

  public submit(user) {
    this.authService
      .login(user)
      .then(() => {
        this.router.navigateByUrl('/home');
      })
      .catch(() => {
        this.fail = true;
        setTimeout(() => (this.fail = false), 3000);
      });
  }
}
