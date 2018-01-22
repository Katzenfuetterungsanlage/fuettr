import { Injectable } from '@angular/core';
import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { AuthService } from './services/auth.service';

@Injectable()
export class InterceptorService implements HttpInterceptor {
  constructor(private authService: AuthService) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const request = req.clone({ headers: req.headers.set('Authorization', 'Bearer ' + this.authService.token) });
    return next.handle(request);
  }
}
