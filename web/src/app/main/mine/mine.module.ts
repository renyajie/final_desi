import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { MineRoutingModule } from './mine-routing.module';
import { MineComponent } from './mine.component';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    MineRoutingModule
  ],
  declarations: [MineComponent]
})
export class MineModule { }
