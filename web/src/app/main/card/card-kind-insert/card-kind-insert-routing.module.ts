import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { CardKindInsertComponent } from './card-kind-insert.component';

const cardInsertRoutes: Routes = [
  { 
    path: '', 
    component: CardKindInsertComponent
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(cardInsertRoutes)
  ]
})
export class CardKindInsertRoutingModule { }
