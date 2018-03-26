import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { CardKindComponent } from './card-kind.component';
import { CardKindDetailComponent } from '../card-kind-detail/card-kind-detail.component';


const cardKindRoutes: Routes = [
  { 
    path: '', 
    children: [
      {path: '', component: CardKindComponent},
      {path: ':id', component: CardKindDetailComponent}
    ]
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(cardKindRoutes)
  ]
})
export class CardKindRoutingModule { }
