import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { AnalyseComponent } from './analyse.component';

const analyseRoutes: Routes = [
  { 
    path: '', 
    component: AnalyseComponent,
    children: [
      { path: 'class-number', loadChildren: 'app/main/analyse/class-number/class-number.module#ClassNumberModule'},
      { path: 'class-time', loadChildren: 'app/main/analyse/class-time/class-time.module#ClassTimeModule'},
      { path: 'order-number', loadChildren: 'app/main/analyse/order-number/order-number.module#OrderNumberModule'},
      { path: '', redirectTo: 'class-time', pathMatch: 'full' }
    ]
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(analyseRoutes)
  ]
})
export class AnalyseRoutingModule { }
