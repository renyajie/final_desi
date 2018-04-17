import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { CommentRoutingModule } from './comment-routing.module';
import { CommentComponent } from './comment.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    CommentRoutingModule
  ],
  declarations: [CommentComponent]
})
export class CommentModule { }
