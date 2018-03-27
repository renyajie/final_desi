import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import 'rxjs/add/operator/switchMap';

import { CardKind } from '../../../poto/card_kind';
import { Place } from '../../../poto/place';

import { CardService } from '../../../core/card.service';
import { PersonInfoService } from '../../../core/person-info.service';
import { PlaceService } from '../../../core/place.service';

@Component({
  selector: 'app-card-kind-insert',
  templateUrl: './card-kind-insert.component.html',
  styleUrls: ['./card-kind-insert.component.css']
})
export class CardKindInsertComponent implements OnInit {

  cardKind$: Observable<CardKind>;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private cardService: CardService,
    private personInfoService: PersonInfoService,
    private placeService: PlaceService
  ) { }

  ngOnInit() {
    let place: Place;
    let cardKind = new CardKind();
    this.placeService.getAllPlace(0, this.personInfoService.manager.pId).subscribe(
      data => {
        if(data['code'] == 100) {
          data['extend']['info'].map(p => {
            place = Place.fromJSON(p);
          })
          cardKind.pId = place.id;
          cardKind.pName = place.sName;
          this.cardKind$ = of(cardKind);
        }
        else {
          alert("发生错误");
        }
      }
    )
  }

  //添加会员卡
  addCardKind(cardKind: CardKind) {
    //检查数据的完备性
    if(cardKind.cardKName == null || cardKind.cardKName.length == 0) {
      alert("会员卡名称不能为空");
      return;
    }
    if(cardKind.capacity == null) {
      alert("会员卡容量不能为空");
      return;
    }
    if(cardKind.expend == null) {
      alert("会员卡价格不能为空");
      return;
    }
    //提交服务器
    this.cardService.addCardKind(cardKind).subscribe(
      data => {
        if (data['code'] === 100) {
          alert("信息更新成功");
        }
        //若发生错误，提示出错
        else {
          //提示字段错误信息
          let notices: string[] = ['capacity', 'cardKName', 'expend'];
          const errorFields = data['extend']['errorFields'];
          for (let notice of notices) {
            if(errorFields[notice] != null) {
              alert(errorFields[notice]);
            }
          }
        }
      }
    )
  }

  //返回会员卡种类列表
  goBack() {
    this.router.navigate(['main/card']);
  }

}
