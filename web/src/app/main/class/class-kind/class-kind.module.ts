import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { ClassKindRoutingModule } from './class-kind-routing.module';
import { ClassKindDetailComponent } from '../class-kind-detail/class-kind-detail.component';
import { ClassKindComponent } from './class-kind.component';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    ClassKindRoutingModule
  ],
  declarations: [
    ClassKindDetailComponent, ClassKindComponent
  ]
})
export class ClassKindModule { }
