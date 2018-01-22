import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login.component';
import { PositionComponent } from './components/position.component';
import { FeedComponent } from './components/feed.component';
import { HomeComponent } from './components/home.component';
import { InfoComponent } from './components/info.component';
import { UpdateComponent } from './components/update.component';
import { Error404Component } from './components/error404.component';

import { UpdateService } from './services/update.service';
import { HttpgetService } from './services/httpget.service';
import { HttpputService } from './services/httpput.service';
import { TimeCalculator } from './services/time.calculator.service';
import { AuthService } from './services/auth.service';
import { InterceptorService } from './interceptor.service';
import { CatComponent } from './cat/cat.component';

@NgModule({
  declarations: [
    AppComponent,
    PositionComponent,
    HomeComponent,
    FeedComponent,
    InfoComponent,
    UpdateComponent,
    Error404Component,
    CatComponent,
    LoginComponent
  ],
  imports: [NgbModule.forRoot(), BrowserModule, HttpClientModule, AppRoutingModule, HttpModule, FormsModule, BrowserAnimationsModule],
  providers: [UpdateService, HttpgetService, HttpputService, TimeCalculator, AuthService, InterceptorService],
  bootstrap: [AppComponent]
})
export class AppModule {}
