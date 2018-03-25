import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { DatepickerModule } from 'ngx-bootstrap';

import { CardOrderComponent } from './card-order.component';
import { CardOrderRoutingModule } from './card-order-routing.module';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    CardOrderRoutingModule,
    DatepickerModule.forRoot()
  ],
  declarations: [
    CardOrderComponent
  ]
})
export class CardOrderModule { }
