import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { SysManagerRoutingModule } from './sys-manager-routing.module';
import { SysManagerComponent } from './sys-manager.component';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    SysManagerRoutingModule
  ],
  declarations: [SysManagerComponent]
})
export class SysManagerModule { }
