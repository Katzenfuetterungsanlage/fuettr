import { Component, OnInit } from '@angular/core';

import { HttpputService } from '../services/httpput.service';

import * as itf from '../interfaces';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent implements OnInit {
  ngOnInit() {}
}
