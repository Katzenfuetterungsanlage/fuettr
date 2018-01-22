import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { PositionComponent } from './components/position.component';
import { HomeComponent } from './components/home.component';
import { FeedComponent } from './components/feed.component';
import { InfoComponent } from './components/info.component';
import { UpdateComponent } from './components/update.component';
import { Error404Component } from './components/error404.component';
import { LoginComponent } from './components/login.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent, data: { title: 'Füttr' } },
  { path: '', pathMatch: 'full', redirectTo: 'login' },
  { path: 'login', component: LoginComponent, data: { title: 'Füttr - Login' } },
  {
    path: 'position',
    component: PositionComponent,
    data: { title: 'Füttr - Positionen' }
  },
  {
    path: 'feed',
    component: FeedComponent,
    data: { title: 'Füttr - Fütterung' }
  },
  { path: 'info', component: InfoComponent, data: { title: 'Füttr - Info' } },
  {
    path: 'update',
    component: UpdateComponent,
    data: { title: 'Füttr - Update' }
  },
  {
    path: '**',
    component: Error404Component,
    data: { title: 'Füttr - 404 (not found)' }
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
