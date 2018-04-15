import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { PlaceEditComponent } from './place-edit.component';

const placeEditRoutes: Routes = [
  { 
    path: ':id', 
    component: PlaceEditComponent
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(placeEditRoutes)
  ]
})
export class PlaceEditRoutingModule { }
