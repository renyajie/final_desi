import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { OrderComponent } from './order.component';

const orderRoutes: Routes = [
  { 
    path: '', 
    component: OrderComponent,
    children: [
      { path: 'class', loadChildren: 'app/main/order/class-order/class-order.module#ClassOrderModule'},
      { path: 'card', loadChildren: 'app/main/order/card-order/card-order.module#CardOrderModule'},
      { path: '', redirectTo: 'class', pathMatch: 'full' }
    ]
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(orderRoutes)
  ],
  exports: [
    RouterModule
  ]
})
export class OrderRoutingModule { }
