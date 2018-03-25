import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { CardOrderComponent } from './card-order.component';

const cardOrderRoutes: Routes = [
  { 
    path: '', 
    component: CardOrderComponent
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(cardOrderRoutes)
  ],
  exports: [
    RouterModule
  ]
})
export class CardOrderRoutingModule { }
