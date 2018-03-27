import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { ClassKindInsertRoutingModule } from './class-kind-insert-routing.module';
import { ClassKindInsertComponent } from './class-kind-insert.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    RouterModule,
    ClassKindInsertRoutingModule
  ],
  declarations: [
    ClassKindInsertComponent
  ]
})
export class ClassKindInsertModule { }
