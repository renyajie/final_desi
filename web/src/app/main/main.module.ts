import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HeaderModule } from './header/header.module';
import { MainRoutingModule } from './main-routing.module';
import { WelcomeModule } from './welcome/welcome.module';

import { ClassService } from '../core/class.service';

import { MainComponent } from './main.component';
import { CardService } from '../core/card.service';
import { TeacherService } from '../core/teacher.service';
import { NewsService } from '../core/news.service';
import { AnalyseService } from '../core/analyse.service';
import { TagService } from '../core/tag.service';
import { ScoreService } from '../core/score.service';

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
    ClassService, CardService, TeacherService, NewsService, AnalyseService, TagService, ScoreService
  ]
})
export class MainModule { }
