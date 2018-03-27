import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { ClassComponent } from './class.component';

const classRoutes: Routes = [
  { 
    path: '', 
    component: ClassComponent,
    children: [
      { path: 'info', loadChildren: 'app/main/class/class-info/class-info.module#ClassInfoModule'},
      { path: 'kind', loadChildren: 'app/main/class/class-kind/class-kind.module#ClassKindModule'},
      { path: 'info-insert', loadChildren: 'app/main/class/class-info-insert/class-info-insert.module#ClassInfoInsertModule'},
      { path: 'kind-insert', loadChildren: 'app/main/class/class-kind-insert/class-kind-insert.module#ClassKindInsertModule'},
      { path: '', redirectTo: 'kind', pathMatch: 'full' }
    ]
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(classRoutes)
  ]
})
export class ClassRoutingModule { }
