import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { TagListComponent } from './tag-list.component';
import { TagDetailComponent } from '../tag-detail/tag-detail.component';

const tagListRoutes: Routes = [
  { 
    path: '', 
    children: [
      {path: '', component: TagListComponent},
      {path: ':id', component: TagDetailComponent}
    ]
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(tagListRoutes)
  ]
})
export class TagListRoutingModule { }
