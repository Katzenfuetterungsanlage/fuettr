import { Component } from '@angular/core';

import { HttpputService } from '../services/httpput.service';
import { AuthService } from '../services/auth.service';

import * as itf from '../interfaces';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent {
  constructor(private authService: AuthService, private router: Router) {}

  submit(user) {
    this.authService.login(user);
    this.router.navigateByUrl('/home');
  }
}
