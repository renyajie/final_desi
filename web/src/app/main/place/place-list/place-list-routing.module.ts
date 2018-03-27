import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { PlaceListComponent } from './place-list.component';
import { PlaceDetailComponent } from '../place-detail/place-detail.component';

const placeListRoutes: Routes = [
  { 
    path: '', 
    children: [
      {path: '', component: PlaceListComponent},
      {path: ':id', component: PlaceDetailComponent}
    ]
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(placeListRoutes)
  ]
})
export class PlaceListRoutingModule { }
