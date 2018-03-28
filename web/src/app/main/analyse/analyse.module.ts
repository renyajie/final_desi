import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { AnalyseRoutingModule } from './analyse-routing.module';
import { AnalyseComponent } from './analyse.component';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    AnalyseRoutingModule
  ],
  declarations: [AnalyseComponent]
})
export class AnalyseModule { }
