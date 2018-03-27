import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { ManagerListComponent } from './manager-list.component';
import { ManagerDetailComponent } from '../manager-detail/manager-detail.component';

const managerListRoutes: Routes = [
  { 
    path: '', 
    children: [
      { path: '', component: ManagerListComponent },
      { path: ':id', component: ManagerDetailComponent }
    ]
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(managerListRoutes)
  ]
})
export class ManagerListRoutingModule { }
