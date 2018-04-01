import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { ClassNumberRoutingModule } from './class-number-routing.module';
import { ClassNumberComponent } from './class-number.component';

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
    ClassNumberRoutingModule,
    FusionChartsModule.forRoot(FusionCharts, Charts)
  ],
  declarations: [
    ClassNumberComponent
  ]
})
export class ClassNumberModule { }
