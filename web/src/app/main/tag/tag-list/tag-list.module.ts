import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { TagListRoutingModule } from './tag-list-routing.module';
import { TagListComponent } from './tag-list.component';
import { TagDetailComponent } from '../tag-detail/tag-detail.component';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    TagListRoutingModule
  ],
  declarations: [TagListComponent, TagDetailComponent]
})
export class TagListModule { }
