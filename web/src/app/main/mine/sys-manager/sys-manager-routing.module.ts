import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { SysManagerComponent } from './sys-manager.component';

const sysManagerRoutes: Routes = [
  { 
    path: '', component: SysManagerComponent
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(sysManagerRoutes)
  ]
})
export class SysManagerRoutingModule { }
