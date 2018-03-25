import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { DatepickerModule } from 'ngx-bootstrap';

import { ClassOrderComponent } from './class-order.component';
import { ClassOrderRoutingModule } from './class-order-routing.module';


@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ClassOrderRoutingModule,
    DatepickerModule.forRoot()
  ],
  declarations: [
    ClassOrderComponent
  ]
})
export class ClassOrderModule { }
