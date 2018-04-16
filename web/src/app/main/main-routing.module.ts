import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { MainComponent } from './main.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { PageNotFoundComponent } from '../core/page-not-found.component';

const mainRoutes: Routes = [
  { 
    path: 'main', 
    component: MainComponent,
    children: [
      { path: 'user', loadChildren: 'app/main/user/user.module#UserModule' },
      { path: 'order', loadChildren: 'app/main/order/order.module#OrderModule' },
      { path: 'card', loadChildren: 'app/main/card/card.module#CardModule' },
      { path: 'class', loadChildren: 'app/main/class/class.module#ClassModule' },
      { path: 'tag', loadChildren: 'app/main/tag/tag.module#TagModule' },
      { path: 'news', loadChildren: 'app/main/news/news.module#NewsModule' },
      { path: 'mine', loadChildren: 'app/main/mine/mine.module#MineModule' },
      { path: 'place', loadChildren: 'app/main/place/place.module#PlaceModule' },
      { path: 'manager', loadChildren: 'app/main/manager/manager.module#ManagerModule' },
      { path: 'analyse', loadChildren: 'app/main/analyse/analyse.module#AnalyseModule' },
      { path: 'welcome', component: WelcomeComponent },
      { path: '', redirectTo: 'welcome', pathMatch: 'full' },
      { path: '**', component: PageNotFoundComponent }
    ] 
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(mainRoutes)
  ],
  exports: [
    RouterModule
  ]
})
export class MainRoutingModule { }
