import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { StartRoutingModule } from './start-routing.module';

import { StartComponent } from './start.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { AuthService } from '../core/auth.service';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    StartRoutingModule
  ],
  declarations: [
    StartComponent,
    LoginComponent,
    RegisterComponent
  ],
  providers: [
    AuthService
  ]
})
export class StartModule { }
