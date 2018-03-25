import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { CardKindComponent } from './card-kind.component';


const cardKindRoutes: Routes = [
  { 
    path: '', 
    component: CardKindComponent
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(cardKindRoutes)
  ]
})
export class CardKindRoutingModule { }
