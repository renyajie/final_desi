import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router'
import { Observable } from 'rxjs/Observable';

import 'rxjs/add/operator/map';
import { of } from 'rxjs/observable/of';
import { CardKind } from '../../../poto/card_kind';
import { CardInfo } from '../../../poto/card_info';
import { CardService } from '../../../core/card.service';

@Component({
  selector: 'app-card-info',
  templateUrl: './card-info.component.html',
  styleUrls: ['./card-info.component.css']
})
export class CardInfoComponent implements OnInit {

  cardKinds$: Observable<CardKind[]>;
  pageInfo$: Observable<any>;
  cardInfos$: Observable<any>;
  
  //用于搜索的用户编号，会员卡编号，课程种类
  userId: string = '';
  cardKId: string = '';

  constructor(
    private cardService: CardService
  ) { }

  ngOnInit() {
    //获取卡种信息
    let cardKinds = [];
    this.cardService.getCardKind(0).subscribe(
      data => {
        //若服务器成功返回数据
        if(data['code'] === 100) {
          data['extend']['info'].map(cardKind => {
            cardKinds.push(CardKind.fromJSON(cardKind));
          })
          this.cardKinds$ = of(cardKinds);
        }
        //若出错
        else {
          alert("服务器发生错误");
        }
      }
    )
    this.getCardInfo();
  }

  /**
   * 清空选择输入框样式，重置字段
   */
  clear() {
    this.userId = '';
    this.cardKId = '';
  }

  getCardInfo(pn?, cardKId?, userId?) {
    
    const cardInfos = [];
    this.cardService.getCardInfo(pn, cardKId, userId).subscribe(
      data => {
        //若成功返回数据，为元素赋值
        if (data['code'] === 100) {
          data['extend']['pageInfo']['list'].map(cardInfo => {
            cardInfos.push(CardInfo.fromJSON(cardInfo));
          });
          this.cardInfos$ = of(cardInfos);
          this.pageInfo$ = of(data['extend']['pageInfo']);
        }
        //若发生错误
        else {
          alert("服务器响应错误")
        }
      }
    )
  }

}
