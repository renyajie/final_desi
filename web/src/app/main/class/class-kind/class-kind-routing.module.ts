import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { ClassKindComponent } from './class-kind.component';
import { ClassKindDetailComponent } from '../class-kind-detail/class-kind-detail.component';

const classKindRoutes: Routes = [
  { 
    path: '', 
    children: [
      {path: '', component: ClassKindComponent},
      {path: ':id', component: ClassKindDetailComponent}
    ]
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(classKindRoutes)
  ]
})
export class ClassKindRoutingModule { }
