import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ManagerComponent } from './manager.component';
import { ManagerRoutingModule } from './manager-routing.module';

@NgModule({
  imports: [
    CommonModule,
    ManagerRoutingModule
  ],
  declarations: [
    ManagerComponent
  ]
})
export class ManagerModule { }
