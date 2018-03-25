import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { CardKindRoutingModule } from './card-kind-routing.module';

import { CardKindComponent } from './card-kind.component';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    CardKindRoutingModule
  ],
  declarations: [CardKindComponent]
})
export class CardKindModule { }
