import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { MineComponent } from './mine.component';

const mineRoutes: Routes = [
  { 
    path: '', 
    component: MineComponent,
    children: [
      { path: 'manager', loadChildren: 'app/main/mine/manager/manager.module#ManagerModule'},
      { path: 'place', loadChildren: 'app/main/mine/place/place.module#PlaceModule'},
      { path: 'sys-manager', loadChildren: 'app/main/mine/sys-manager/sys-manager.module#SysManagerModule'},
    ]
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(mineRoutes)
  ]
})
export class MineRoutingModule { }
