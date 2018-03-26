import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { CardInfoRoutingModule } from './card-info-routing.module';
import { CardInfoComponent } from './card-info.component';
import { FormsModule } from '@angular/forms';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    CardInfoRoutingModule
  ],
  declarations: [
    CardInfoComponent
  ]
})
export class CardInfoModule { }
