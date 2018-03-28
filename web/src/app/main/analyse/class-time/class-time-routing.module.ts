import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { ClassTimeComponent } from './class-time.component';

const classTimeRoutes: Routes = [
  { 
    path: '', 
    component: ClassTimeComponent
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(classTimeRoutes)
  ]
})
export class ClassTimeRoutingModule { }
