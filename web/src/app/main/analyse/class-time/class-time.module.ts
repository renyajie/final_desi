import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { ClassTimeRoutingModule } from './class-time-routing.module';
import { ClassTimeComponent } from './class-time.component';

// Import the library
import { FusionChartsModule } from 'angular2-fusioncharts';
// Import FusionCharts library
import * as FusionCharts from 'fusioncharts';
// Import FusionCharts Charts module
import * as Charts from 'fusioncharts/fusioncharts.charts';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    ClassTimeRoutingModule,
    FusionChartsModule.forRoot(FusionCharts, Charts)
  ],
  declarations: [
    ClassTimeComponent
  ]
})
export class ClassTimeModule { }
