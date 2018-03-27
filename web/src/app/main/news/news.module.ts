import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { NewsRoutingModule } from './news-routing.module';
import { NewsComponent } from './news.component';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    NewsRoutingModule
  ],
  declarations: [NewsComponent]
})
export class NewsModule { }
