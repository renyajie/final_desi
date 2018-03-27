import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { NewsListRoutingModule } from './news-list-routing.module';
import { NewsListComponent } from './news-list.component';
import { NewsDetailComponent } from '../news-detail/news-detail.component';
import { DatepickerModule } from 'ngx-bootstrap';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    NewsListRoutingModule,
    DatepickerModule.forRoot()
  ],
  declarations: [
    NewsListComponent, NewsDetailComponent
  ]
})
export class NewsListModule { }
