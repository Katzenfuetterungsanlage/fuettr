import { Injectable } from '@angular/core';

import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/of';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/delay';
import { HttpputService } from './httpput.service';

@Injectable()
export class AuthService {
  isLoggedIn = false;
  token: string;

  // store the URL so we can redirect after logging in
  redirectUrl: string;

  constructor(private httpputService: HttpputService) { }

  login(user): Promise<void> {
    return this.httpputService.login(user).then(res => {
      this.isLoggedIn = res.isLoggedIn;
      this.token = res.token;
    });
  }

  logout(): void {
    this.isLoggedIn = false;
    this.httpputService.logout(this.token).then();
  }
}
