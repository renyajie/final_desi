import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { ManagerRoutingModule } from './manager-routing.module';
import { ManagerComponent } from './manager.component';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    ManagerRoutingModule
  ],
  declarations: [ManagerComponent]
})
export class ManagerModule { }
