import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { TestComponent } from './test.component';

const testRoutes: Routes = [
  { path: 'test', component: TestComponent }
];

@NgModule({
  imports: [
    RouterModule.forChild(testRoutes)
  ]
})
export class TestRoutingModule { }
