import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { PlaceComponent } from './place.component';

const placeRoutes: Routes = [
  { 
    path: '', 
    component: PlaceComponent,
    children: [
      { path: 'info', loadChildren: 'app/main/mine/place/place-detail/place-detail.module#PlaceDetailModule'},
      { path: 'edit', loadChildren: 'app/main/mine/place/place-edit/place-edit.module#PlaceEditModule'},
      { path: '', redirectTo: 'info', pathMatch: 'full' }
    ]
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(placeRoutes)
  ]
})
export class PlaceRoutingModule { }
