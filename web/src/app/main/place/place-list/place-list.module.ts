import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { PlaceListRoutingModule } from './place-list-routing.module';
import { PlaceListComponent } from './place-list.component';
import { PlaceDetailComponent } from '../place-detail/place-detail.component';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    PlaceListRoutingModule
  ],
  declarations: [PlaceListComponent, PlaceDetailComponent]
})
export class PlaceListModule { }
