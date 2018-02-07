import { Injectable, Injector } from '@angular/core';
import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { AuthService } from './services/auth.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private inj: Injector) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const auth = this.inj.get(AuthService);
    console.log(req.urlWithParams);
    if (req.urlWithParams === 'https://raw.githubusercontent.com/Katzenfuetterungsanlage/fuettr/master/version.json') {
      return next.handle(req);
    }
    const request = req.clone({ headers: req.headers.set('Authorization', 'Bearer ' + auth.token) });
    return next.handle(request);
  }
}
