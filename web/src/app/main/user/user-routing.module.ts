import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { UserComponent } from './user.component';

const userRoutes: Routes = [
  { 
    path: '', 
    component: UserComponent,
    children: [
      { path: 'list', loadChildren: 'app/main/user/user-list/user-list.module#UserListModule'},
      { path: '', redirectTo: 'list', pathMatch: 'full' }
    ]
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(userRoutes)
  ],
  exports: [
    RouterModule
  ]
})
export class UserRoutingModule { }
