import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { CardInfoComponent } from './card-info.component';

const cardInfoRoutes: Routes = [
  { 
    path: '', 
    component: CardInfoComponent
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(cardInfoRoutes)
  ]
})
export class CardInfoRoutingModule { }
