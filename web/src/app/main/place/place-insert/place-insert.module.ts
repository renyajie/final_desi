import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { PlaceInsertComponent } from './place-insert.component';
import { PlaceInsertRoutingModule } from './place-insert-routing.module';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    RouterModule,
    PlaceInsertRoutingModule
  ],
  declarations: [PlaceInsertComponent]
})
export class PlaceInsertModule { }
