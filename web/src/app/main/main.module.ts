import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HeaderModule } from './header/header.module';
import { MainRoutingModule } from './main-routing.module';
import { WelcomeModule } from './welcome/welcome.module';

import { ClassService } from '../core/class.service';

import { MainComponent } from './main.component';
import { CardService } from '../core/card.service';


@NgModule({
  imports: [
    CommonModule,
    HeaderModule,
    WelcomeModule,
    MainRoutingModule
  ],
  declarations: [
    MainComponent
  ],
  providers: [
    ClassService, CardService
  ]
})
export class MainModule { }
