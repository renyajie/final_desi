import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';

import { StartComponent } from './start.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { PageNotFoundComponent } from '../core/page-not-found.component';

const startRoutes: Routes = [
  { 
    path: 'start', 
    component: StartComponent,
    children: [
      { path: 'login', component: LoginComponent },
      { path: 'register', component: RegisterComponent },
      { path: '', redirectTo: 'login', pathMatch: 'full' },
      { path: '**', component: PageNotFoundComponent }
    ]
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(startRoutes)
  ],
  exports: [
    RouterModule
  ]
})
export class StartRoutingModule { }
