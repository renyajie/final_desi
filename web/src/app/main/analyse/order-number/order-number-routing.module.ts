import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { OrderNumberComponent } from './order-number.component';

const orderNumberRoutes: Routes = [
  { 
    path: '', 
    component: OrderNumberComponent
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(orderNumberRoutes)
  ]
})
export class OrderNumberRoutingModule { }
