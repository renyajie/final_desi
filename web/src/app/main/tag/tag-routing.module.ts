import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { TagComponent } from './tag.component';

const tagRoutes: Routes = [
  { 
    path: '', 
    component: TagComponent,
    children: [
      { path: 'list', loadChildren: 'app/main/tag/tag-list/tag-list.module#TagListModule'},
      { path: '', redirectTo: 'list', pathMatch: 'full' }
    ]
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(tagRoutes)
  ]
})
export class TagRoutingModule { }
