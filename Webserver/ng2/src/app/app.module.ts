import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ControlComponent } from './control.component';
import { FeedComponent } from './feed.component';
import { HomeComponent } from './home.component';
import { InfoComponent } from './info.component';
import { UpdateComponent } from './update.component';
import { Error404Component } from './error404.component';

import { UpdateService } from './update.service';
import { HttpgetService } from './httpget.service';

@NgModule({
  declarations: [
    AppComponent,
    ControlComponent,
    HomeComponent,
    FeedComponent,
    InfoComponent,
    UpdateComponent,
    Error404Component
  ],
  imports: [BrowserModule, AppRoutingModule, HttpModule, FormsModule],
  providers: [UpdateService, HttpgetService],
  bootstrap: [AppComponent]
})
export class AppModule { }
