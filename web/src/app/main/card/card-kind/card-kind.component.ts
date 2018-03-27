import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router'
import { Observable } from 'rxjs/Observable';

import 'rxjs/add/operator/map';
import { of } from 'rxjs/observable/of';

import { CardKind } from '../../../poto/card_kind';

import { CardService } from '../../../core/card.service';

@Component({
  selector: 'app-card-kind',
  templateUrl: './card-kind.component.html',
  styleUrls: ['./card-kind.component.css']
})
export class CardKindComponent implements OnInit {

  cardKinds$: Observable<CardKind[]>;
  pageInfo$: Observable<any>;

  cardKId: string;
  cardKName: string;
  capacity: string;
  expend: string;

  constructor(
    private cardService: CardService
  ) { }

  ngOnInit() {
    this.clear();
    this.getCardKind();
  }

  /**
   * 清空选择输入框样式，重置字段
   */
  clear() {
    this.cardKId = '';
    this.cardKName = '';
    this.capacity = '';
    this.expend = '';
  }

  getCardKind(pn?, cardKId?, cardKName?, capacity?, expend?) {
    const cardKinds = [];
    this.cardService.getCardKind(1, pn, cardKId, cardKName, capacity, expend).subscribe(
      data => {
        //若成功返回数据，为元素赋值
        if (data['code'] === 100) {
          data['extend']['pageInfo']['list'].map(cardKind => {
            cardKinds.push(CardKind.fromJSON(cardKind));
          });
          this.cardKinds$ = of(cardKinds);
          this.pageInfo$ = of(data['extend']['pageInfo']);
        }
        //若发生错误
        else {
          alert("服务器响应错误")
        }
      }
    )
  }

  //删除会员卡种类
  deleteCardKind(cardKId) {
    this.cardService.deleteCardKind(cardKId).subscribe(
      data => {
        if(data['code'] == 100) {
          //提示成功并重新获取会员卡列表
          alert("删除成功");
          this.clear();
          this.getCardKind();
        }
        else
        {
          alert("发生错误");
        }
      }
    )
  } 

}
