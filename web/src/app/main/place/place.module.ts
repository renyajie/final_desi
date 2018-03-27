import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { PlaceRoutingModule } from './place-routing.module';
import { PlaceComponent } from './place.component';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    PlaceRoutingModule
  ],
  declarations: [PlaceComponent]
})
export class PlaceModule { }
