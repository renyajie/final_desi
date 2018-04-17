import { Component, OnInit } from '@angular/core';
import { PersonInfoService } from '../../core/person-info.service';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnInit {

  isSysManager: boolean;

  constructor(
    private personInfoService: PersonInfoService
  ) { }

  ngOnInit() {
    this.isSysManager = this.personInfoService.isSysManager;
  }

}
