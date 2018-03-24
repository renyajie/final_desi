import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HeaderModule } from './header/header.module';
import { MainRoutingModule } from './main-routing.module';

import { MainComponent } from './main.component';


@NgModule({
  imports: [
    CommonModule,
    HeaderModule,
    MainRoutingModule
  ],
  declarations: [
    MainComponent
  ]
})
export class MainModule { }
