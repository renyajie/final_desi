import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { CoreModule } from './core/core.module';
import { TestModule } from './test/test.module';
import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { StartModule } from './start/start.module';
import { MainModule } from './main/main.module';
import { PersonInfoService } from './core/person-info.service';
import { PlaceService } from './core/place.service';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    StartModule,
    MainModule,
    TestModule,
    CoreModule,
    AppRoutingModule
  ],
  providers: [PersonInfoService, PlaceService],
  bootstrap: [AppComponent]
})
export class AppModule { }
