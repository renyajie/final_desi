import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';

import { PageNotFoundComponent } from './core/page-not-found.component';

const appRoutes: Routes = [
  //登录启动页: 
  { path: '', redirectTo: 'start', pathMatch: 'full' },
  //主界面: 
  //{ path: '', redirectTo: '/main', pathMatch: 'full' },
  //测试连接页: 
  //{ path: '', redirectTo: '/test', pathMatch: 'full' },
  { path: '**', component: PageNotFoundComponent }
];

@NgModule({
  imports: [
    RouterModule.forRoot(appRoutes)
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule { }
