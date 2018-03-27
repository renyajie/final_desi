import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { ManagerComponent } from './manager.component';

const managerRoutes: Routes = [
  { 
    path: '', component: ManagerComponent
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(managerRoutes)
  ]
})
export class ManagerRoutingModule { }
