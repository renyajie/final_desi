import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { PlaceComponent } from './place.component';

const placeRoutes: Routes = [
  { 
    path: '', component: PlaceComponent
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(placeRoutes)
  ]
})
export class PlaceRoutingModule { }
