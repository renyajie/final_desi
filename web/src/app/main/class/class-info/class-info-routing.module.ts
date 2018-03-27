import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { ClassInfoComponent } from './class-info.component';
import { ClassInfoDetailComponent } from '../class-info-detail/class-info-detail.component';

const classInfoRoutes: Routes = [
  { 
    path: '', 
    children: [
      {path: '', component: ClassInfoComponent},
      {path: ':id', component: ClassInfoDetailComponent}
    ]
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(classInfoRoutes)
  ]
})
export class ClassInfoRoutingModule { }
