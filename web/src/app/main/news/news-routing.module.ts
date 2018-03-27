import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { NewsComponent } from './news.component';

const newsRoutes: Routes = [
  { 
    path: '', 
    component: NewsComponent,
    children: [
      { path: 'list', loadChildren: 'app/main/news/news-list/news-list.module#NewsListModule'},
      { path: 'insert', loadChildren: 'app/main/news/news-insert/news-insert.module#NewsInsertModule'},
      { path: '', redirectTo: 'list', pathMatch: 'full' }
    ]
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(newsRoutes)
  ]
})
export class NewsRoutingModule { }
