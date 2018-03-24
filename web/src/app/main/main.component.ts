import { Component, OnInit } from '@angular/core';
import { PersonInfoService } from '../core/person-info.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  isSysManager: boolean

  constructor(
    private personInfoSevice: PersonInfoService
  ) { 
    this.isSysManager = this.personInfoSevice.isSysManager;
  }

  ngOnInit() {
  }

}
