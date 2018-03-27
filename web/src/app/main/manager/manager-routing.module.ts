import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { ManagerComponent } from './manager.component';

const managerRoutes: Routes = [
  { 
    path: '', 
    component: ManagerComponent,
    children: [
      { path: 'list', loadChildren: 'app/main/manager/manager-list/manager-list.module#ManagerListModule'},
      { path: '', redirectTo: 'list', pathMatch: 'full' }
    ]
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(managerRoutes)
  ],
  exports: [
    RouterModule
  ]
})
export class ManagerRoutingModule { }
