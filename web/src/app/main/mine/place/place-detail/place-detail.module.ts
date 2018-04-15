import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { PlaceDetailRoutingModule } from './place-detail-routing.module';
import { PlaceDetailComponent } from './place-detail.component';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    PlaceDetailRoutingModule
  ],
  declarations: [PlaceDetailComponent]
})
export class PlaceDetailModule { }
