import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { TagRoutingModule } from './tag-routing.module';
import { TagComponent } from './tag.component';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    TagRoutingModule
  ],
  declarations: [TagComponent]
})
export class TagModule { }
