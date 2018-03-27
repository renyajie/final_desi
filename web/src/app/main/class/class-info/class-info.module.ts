import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { DatepickerModule } from 'ngx-bootstrap';

import { ClassInfoRoutingModule } from './class-info-routing.module';
import { ClassInfoComponent } from './class-info.component';
import { ClassInfoDetailComponent } from '../class-info-detail/class-info-detail.component';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    ClassInfoRoutingModule,
    DatepickerModule.forRoot()
  ],
  declarations: [
    ClassInfoComponent, ClassInfoDetailComponent
  ]
})
export class ClassInfoModule { }
