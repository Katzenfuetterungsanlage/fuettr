import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { PositionComponent } from './components/position.component';
import { HomeComponent } from './components/home.component';
import { FeedComponent } from './components/feed.component';
import { InfoComponent } from './components/info.component';
import { UpdateComponent } from './components/update.component';
import { Error404Component } from './components/error404.component';
import { LoginComponent } from './components/login.component';
import { AuthGuard } from './services/auth-guard.service';

const routes: Routes = [
  { path: 'home', component: HomeComponent, data: { title: 'Füttr' }, canActivate: [AuthGuard] },
  { path: '', pathMatch: 'full', redirectTo: 'login' },
  { path: 'login', component: LoginComponent, data: { title: 'Füttr - Login' } },
  {
    path: 'position',
    component: PositionComponent,
    data: { title: 'Füttr - Positionen' },
    canActivate: [AuthGuard]
  },
  {
    path: 'feed',
    component: FeedComponent,
    data: { title: 'Füttr - Fütterung' },
    canActivate: [AuthGuard]
  },
  { path: 'info', component: InfoComponent, data: { title: 'Füttr - Info' }, canActivate: [AuthGuard] },
  {
    path: 'update',
    component: UpdateComponent,
    data: { title: 'Füttr - Update' },
    canActivate: [AuthGuard]
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
