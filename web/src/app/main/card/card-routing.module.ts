import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { CardComponent } from './card.component';

const cardRoutes: Routes = [
  { 
    path: '', 
    component: CardComponent,
    children: [
      { path: 'info', loadChildren: 'app/main/card/card-info/card-info.module#CardInfoModule'},
      { path: 'kind', loadChildren: 'app/main/card/card-kind/card-kind.module#CardKindModule'},
      { path: 'insert', loadChildren: 'app/main/card/card-kind-insert/card-kind-insert.module#CardKindInsertModule'},
      { path: '', redirectTo: 'kind', pathMatch: 'full' }
    ]
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(cardRoutes)
  ]
})
export class CardRoutingModule { }
