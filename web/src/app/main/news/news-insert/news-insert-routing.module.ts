import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { NewsInsertComponent } from './news-insert.component';

const newsInsertRoutes: Routes = [
  { 
    path: '', 
    component: NewsInsertComponent
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(newsInsertRoutes)
  ]
})
export class NewsInsertRoutingModule { }
