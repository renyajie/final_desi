import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { ClassNumberRoutingModule } from './class-number-routing.module';
import { ClassNumberComponent } from './class-number.component';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    ClassNumberRoutingModule
  ],
  declarations: [
    ClassNumberComponent
  ]
})
export class ClassNumberModule { }
