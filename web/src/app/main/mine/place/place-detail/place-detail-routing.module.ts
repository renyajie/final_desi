import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { PlaceDetailComponent } from "./place-detail.component";

const placeDetailRoutes: Routes = [
  { 
    path: '', 
    component: PlaceDetailComponent
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(placeDetailRoutes)
  ]
})
export class PlaceDetailRoutingModule { }
