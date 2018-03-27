import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { ClassKindInsertComponent } from './class-kind-insert.component';

const classKindInsertRoutes: Routes = [
  { 
    path: '', 
    component: ClassKindInsertComponent
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(classKindInsertRoutes)
  ]
})
export class ClassKindInsertRoutingModule { }
