import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import 'rxjs/add/operator/switchMap';

import { PlaceService } from '../../../../core/place.service';
import { PersonInfoService } from '../../../../core/person-info.service';
import { Place } from '../../../../poto/place';

@Component({
  selector: 'app-place-detail',
  templateUrl: './place-detail.component.html',
  styleUrls: ['./place-detail.component.css']
})
export class PlaceDetailComponent implements OnInit {

  place$: Observable<Place>;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private placeService: PlaceService,
    private personInfoService: PersonInfoService
  ) { }

  ngOnInit() {
    this.placeService.getOnePlace(this.personInfoService.manager.pId).subscribe(
      data => {
        //若服务器成功返回消息
        if (data['code'] === 100) {
          this.place$ = of(data['extend']['info']);
        }
        //若发生错误，提示出错
        else {
          alert("发生错误");
        }
      }
    )
  }

  //进入场馆编辑页面
  edit() {
    this.router.navigate(['main/mine/place/edit']);
  }

}
