import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { ClassOrderComponent } from './class-order.component';

const classOrderRoutes: Routes = [
  { 
    path: '', 
    component: ClassOrderComponent
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(classOrderRoutes)
  ],
  exports: [
    RouterModule
  ]
})
export class ClassOrderRoutingModule { }
