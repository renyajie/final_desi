import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import 'rxjs/add/operator/switchMap';

import { CardKind } from '../../../poto/card_kind';
import { CardService } from '../../../core/card.service';

@Component({
  selector: 'app-card-kind-detail',
  templateUrl: './card-kind-detail.component.html',
  styleUrls: ['./card-kind-detail.component.css']
})
export class CardKindDetailComponent implements OnInit {

  cardKind$: Observable<CardKind>;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private cardService: CardService
  ) { }

  ngOnInit() {
    this.route.paramMap.switchMap((params: ParamMap) =>
      this.cardService.getCardKindInfo(params.get('id'))).subscribe(
      data => {
        //若服务器成功返回消息
        if (data['code'] === 100) {
          this.cardKind$ = of(data['extend']['info']);
        }
        //若发生错误，提示出错
        else {
          alert("发生错误");
        }
      }
    );
  }

  //提交更新信息
  submitData(cardKind: CardKind) {
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
    this.cardService.cardKindUpdate(cardKind).subscribe(
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
    this.router.navigate(['../'], { relativeTo: this.route });
  }
}
