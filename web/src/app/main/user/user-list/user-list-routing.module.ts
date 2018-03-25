import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { UserListComponent } from './user-list.component';
import { UserDetailComponent } from '../user-detail/user-detail.component';

const userListRoutes: Routes = [
  { 
    path: '', 
    children: [
      { path: '', component: UserListComponent },
      { path: ':id', component: UserDetailComponent }
    ]
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(userListRoutes)
  ]
})
export class UserListRoutingModule { }
