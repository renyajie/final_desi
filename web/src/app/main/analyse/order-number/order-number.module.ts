import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { OrderNumberRoutingModule } from './order-number-routing.module';
import { OrderNumberComponent } from './order-number.component';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    OrderNumberRoutingModule
  ],
  declarations: [OrderNumberComponent]
})
export class OrderNumberModule { }
