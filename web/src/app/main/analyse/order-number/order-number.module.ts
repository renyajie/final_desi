import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { OrderNumberRoutingModule } from './order-number-routing.module';
import { OrderNumberComponent } from './order-number.component';

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
    OrderNumberRoutingModule,
    FusionChartsModule.forRoot(FusionCharts, Charts)
  ],
  declarations: [OrderNumberComponent]
})
export class OrderNumberModule { }
