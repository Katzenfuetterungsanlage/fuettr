import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ControlComponent } from './control.component';
import { HomeComponent } from './home.component';
import { FeedComponent } from './feed.component';
import { InfoComponent } from './info.component';
import { UpdateComponent } from './update.component';
import { Error404Component } from './error404.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: '', pathMatch: 'full', redirectTo: 'home' },
  { path: 'control', component: ControlComponent },
  { path: 'feed', component: FeedComponent },
  { path: 'info', component: InfoComponent },
  { path: 'update', component: UpdateComponent },
  { path: '**', component: Error404Component }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
