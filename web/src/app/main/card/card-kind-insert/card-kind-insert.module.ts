import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { CardKindInsertRoutingModule } from './card-kind-insert-routing.module';
import { CardKindInsertComponent } from './card-kind-insert.component';
import { FormsModule } from '@angular/forms';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    CardKindInsertRoutingModule
  ],
  declarations: [
    CardKindInsertComponent
  ]
})
export class CardKindInsertModule { }
