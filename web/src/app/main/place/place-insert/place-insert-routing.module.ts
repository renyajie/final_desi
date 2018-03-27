import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { PlaceInsertComponent } from './place-insert.component';

const placeInsertRoutes: Routes = [
  { 
    path: '', 
    component: PlaceInsertComponent
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(placeInsertRoutes)
  ]
})
export class PlaceInsertRoutingModule { }
