import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { FileUploadModule } from 'ng2-file-upload';

import { PlaceEditRoutingModule } from './place-edit-routing.module';
import { PlaceEditComponent } from './place-edit.component';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    FileUploadModule,
    PlaceEditRoutingModule
  ],
  declarations: [PlaceEditComponent]
})
export class PlaceEditModule { }
