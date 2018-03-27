import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { ClassRoutingModule } from './class-routing.module';
import { ClassComponent } from './class.component';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    ClassRoutingModule
  ],
  declarations: [
    ClassComponent
  ]
})
export class ClassModule { }
