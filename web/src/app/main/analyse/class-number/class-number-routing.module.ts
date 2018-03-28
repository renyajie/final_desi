import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { ClassNumberComponent } from './class-number.component';

const classNumberRoutes: Routes = [
  { 
    path: '', 
    component: ClassNumberComponent
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(classNumberRoutes)
  ]
})
export class ClassNumberRoutingModule { }
