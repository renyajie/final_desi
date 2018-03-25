import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router'
import { DatepickerModule } from 'ngx-bootstrap';
import { Observable } from 'rxjs/Observable';

import 'rxjs/add/operator/map';
import { of } from 'rxjs/observable/of';

import { DateFormat } from '../../../utility/date-format';
import { CardKind } from '../../../poto/card_kind';
import { CardOrder } from '../../../poto/card_order';

import { CardService } from '../../../core/card.service';

@Component({
  selector: 'app-card-order',
  templateUrl: './card-order.component.html',
  styleUrls: ['./card-order.component.css']
})
export class CardOrderComponent implements OnInit {

  cardKinds$: Observable<CardKind[]>;
  pageInfo$: Observable<any>;
  cardOrders$: Observable<any>;

  //日期选择框文本
  beforeBtnText = '选择最早日期';
  afterBtnText = '选择最晚日期';
  //是否显示日期选择框
  showBeforeDate = false;
  showAfterDate = false;
  //用户选择的最早日期和最晚日期
  beforeDate: Date;
  afterDate: Date;
  //判断用户是否为第一次选择
  firstChooseForBeforeDate = true;
  firstChooseForAfterDate = true;
  //当前日期，最大可选日期和最小可选日期
  current: Date;
  maxDate: Date;
  minDate: Date;
  //用于搜索的用户编号，会员卡编号，课程种类
  userId: string = '';
  cardId: string = '';
  cardKId: string = '';

  constructor(
    private cardService: CardService
  ) { 
    //用到的参数一定要初始化，你无法预知你会什么时候调用它。
    this.beforeDate = new Date();
    this.afterDate = new Date();
    this.current = new Date();
    this.maxDate = new Date();
    this.minDate = new Date();
    //设置最大可选日期是今天，最小可选日期是3年前
    this.maxDate.setDate(this.current.getDate());
    this.minDate.setDate(this.current.getDate() - 3 * 365);
  }

  ngOnInit() {
    //获取卡种信息
    let cardKinds = [];
    this.cardService.getCardKind().subscribe(
      data => {
        //若服务器成功返回数据
        if(data['code'] === 100) {
          data['extend']['info'].map(cardKind => {
            cardKinds.push(CardKind.fromJSON(cardKind));
          })
          //TODO removes
          console.log(cardKinds);
          this.cardKinds$ = of(cardKinds);
        }
        //若出错
        else {
          alert("服务器发生错误");
        }
      }
    )
    this.getCardOrder();
  }

  showBeforePicker(event: any) {
    if (this.firstChooseForBeforeDate) {
      this.firstChooseForBeforeDate = false;
    }
    this.showBeforeDate = !this.showBeforeDate;
    this.beforeBtnText = this.showBeforeDate ? '完成日期选择' : '选择最早日期';
  }

  showAfterPicker(event: any) {
    if (this.firstChooseForAfterDate) {
      this.firstChooseForAfterDate = false;
    }
    this.showAfterDate = !this.showAfterDate;
    this.afterBtnText = this.showAfterDate ? '完成日期选择' : '选择最晚日期';
  }

  get beforeDateText() {
    if (this.firstChooseForBeforeDate) {
      return '未选择最早日期';
    }
    return DateFormat.formatWithDay(this.beforeDate);
  }

  get afterDateText() {
    if (this.firstChooseForAfterDate) {
      return '未选择最晚日期';
    }
    return DateFormat.formatWithDay(this.afterDate);
  }

  /**
   * 清空选择输入框样式，重置字段
   */
  clear() {
    this.firstChooseForAfterDate = true;
    this.firstChooseForBeforeDate = true;
    this.afterDate = new Date();
    this.beforeDate = new Date();
    this.userId = '';
    this.cardId = '';
    this.cardKId = '';
  }

  getCardOrder(pn?, cardKId?, userId?) {
    //判断日期选择的合理性
    if (!this.firstChooseForBeforeDate && !this.firstChooseForAfterDate) {
      if (this.beforeDate.getDate() > this.afterDate.getDate()) {
        alert("最早日期不能晚于最迟日期，请重新选择");
        this.clear();
      }
    }
    //发出搜索，并展示结果
    const cardOrders = [];
    this.cardService.getCardOrder(
      pn, cardKId, userId,
      this.firstChooseForBeforeDate ? null : DateFormat.formatWithDay(this.beforeDate),
      this.firstChooseForAfterDate ? null : DateFormat.formatWithDay(this.afterDate)).subscribe(
        data => {
          //若成功返回数据，为元素赋值
          if (data['code'] === 100) {
            data['extend']['pageInfo']['list'].map(cardOrder => {
              cardOrders.push(CardOrder.fromJSON(cardOrder));
            });
            this.cardOrders$ = of(cardOrders);
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
