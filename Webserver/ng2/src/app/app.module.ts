import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
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
    CatComponent
  ],
  imports: [
    NgbModule.forRoot(),
    BrowserModule,
    AppRoutingModule,
    HttpModule,
    FormsModule,
    BrowserAnimationsModule
  ],
  providers: [UpdateService, HttpgetService, HttpputService, TimeCalculator],
  bootstrap: [AppComponent]
})
export class AppModule { }
