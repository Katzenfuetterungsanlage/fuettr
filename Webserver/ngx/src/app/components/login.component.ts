import { Component, LOCALE_ID, Inject, OnInit } from '@angular/core';

import { AuthService } from '../services/auth.service';

import * as itf from '../interfaces';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent implements OnInit {
  public fail = false;
  public languages = [{ code: 'en', img: 'assets/en.png' }, { code: 'de', img: 'assets/de.png' }];
  public activelang;

  constructor(private authService: AuthService, private router: Router, @Inject(LOCALE_ID) protected localeId: string) {}

  public ngOnInit(): void {
    if (this.localeId === 'de') {
      this.activelang = 'de';
    } else {
      this.activelang = 'en';
    }
  }

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
