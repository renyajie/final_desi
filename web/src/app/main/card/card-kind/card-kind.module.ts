import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { CardKindRoutingModule } from './card-kind-routing.module';

import { CardKindComponent } from './card-kind.component';
import { CardKindDetailComponent } from '../card-kind-detail/card-kind-detail.component';
import { FormsModule } from '@angular/forms';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    RouterModule,
    CardKindRoutingModule
  ],
  declarations: [
    CardKindComponent, CardKindDetailComponent]
})
export class CardKindModule { }
