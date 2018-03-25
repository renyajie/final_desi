import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';

import { UserListRoutingModule } from './user-list-routing.module';

import { UserListComponent } from './user-list.component';
import { UserDetailComponent } from '../user-detail/user-detail.component';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    UserListRoutingModule
  ],
  declarations: [
    UserListComponent,
    UserDetailComponent
  ]
})
export class UserListModule { }
