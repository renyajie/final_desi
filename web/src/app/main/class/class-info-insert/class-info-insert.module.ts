import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { DatepickerModule } from 'ngx-bootstrap';
import { ClassInfoInsertRoutingModule } from './class-info-insert-routing.module';
import { ClassInfoInsertComponent } from './class-info-insert.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    RouterModule,
    ClassInfoInsertRoutingModule,
    DatepickerModule.forRoot()
  ],
  declarations: [
    ClassInfoInsertComponent
  ]
})
export class ClassInfoInsertModule { }
