import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { ClassInfoInsertComponent } from './class-info-insert.component';

const classInfoInsertRoutes: Routes = [
  { 
    path: '', 
    component: ClassInfoInsertComponent
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(classInfoInsertRoutes)
  ]
})
export class ClassInfoInsertRoutingModule { }
