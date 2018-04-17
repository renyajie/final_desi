import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { CommentComponent } from './comment.component';

const commentRoutes: Routes = [
  { 
    path: '', 
    component: CommentComponent
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(commentRoutes)
  ],
  exports: [
    RouterModule
  ]
})
export class CommentRoutingModule { }
