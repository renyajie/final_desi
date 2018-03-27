import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { PlaceComponent } from './place.component';

const placeRoutes: Routes = [
  { 
    path: '', 
    component: PlaceComponent,
    children: [
      { path: 'list', loadChildren: 'app/main/place/place-list/place-list.module#PlaceListModule'},
      { path: 'insert', loadChildren: 'app/main/place/place-insert/place-insert.module#PlaceInsertModule'},
      { path: '', redirectTo: 'list', pathMatch: 'full' }
    ]
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(placeRoutes)
  ]
})
export class PlaceRoutingModule { }
