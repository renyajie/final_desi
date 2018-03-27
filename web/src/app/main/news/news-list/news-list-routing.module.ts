import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { NewsListComponent } from './news-list.component';
import { NewsDetailComponent } from '../news-detail/news-detail.component';

const newsListRoutes: Routes = [
  { 
    path: '', 
    children: [
      {path: '', component: NewsListComponent},
      {path: ':id', component: NewsDetailComponent}
    ]
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(newsListRoutes)
  ]
})
export class NewsListRoutingModule { }
