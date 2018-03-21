import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { TestRoutingModule } from './test-routing.module';

import { TestComponent } from './test.component';
import { TestService } from './test.service';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    TestRoutingModule
  ],
  declarations: [
    TestComponent
  ],
  providers: [
    TestService
  ]
})
export class TestModule { }
