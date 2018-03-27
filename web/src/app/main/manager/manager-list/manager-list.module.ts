import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { ManagerListRoutingModule } from './manager-list-routing.module';
import { ManagerListComponent } from './manager-list.component';
import { ManagerDetailComponent } from '../manager-detail/manager-detail.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    RouterModule,
    ManagerListRoutingModule
  ],
  declarations: [
    ManagerListComponent, ManagerDetailComponent
  ]
})
export class ManagerListModule { }
