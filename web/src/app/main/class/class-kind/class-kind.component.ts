import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router'
import { Observable } from 'rxjs/Observable';

import 'rxjs/add/operator/map';
import { of } from 'rxjs/observable/of';

import { ClassService } from '../../../core/class.service';

import { SimpleToken } from '../../../utility/simple_token';
import { ClassKind } from '../../../poto/class_kind';

@Component({
  selector: 'app-class-kind',
  templateUrl: './class-kind.component.html',
  styleUrls: ['./class-kind.component.css']
})
export class ClassKindComponent implements OnInit {

  classKinds$: Observable<ClassKind[]>;
  pageInfo$: Observable<any>;

  classPropertys: SimpleToken[] = [
    new SimpleToken("私教", "s"),
    new SimpleToken("团课", "g")
  ];
  classKId: string;
  classKName: string;
  property: string;
  difficulty: string;

  constructor(
    private classService: ClassService
  ) { }

  ngOnInit() {
    this.clear();
    this.getClassKind();
  }

  /**
   * 清空选择输入框样式，重置字段
   */
  clear() {
    this.classKId = '';
    this.classKName = '';
    this.property = '';
    this.difficulty = '';
  }

  getClassKind(pn?, classKId?, classKName?, property?, difficulty?) {
    const classKinds = [];
    this.classService.getClassKind(1, pn, classKId, classKName, property, difficulty).subscribe(
      data => {
        //若成功返回数据，为元素赋值
        if (data['code'] === 100) {
          data['extend']['pageInfo']['list'].map(classKind => {
            classKinds.push(ClassKind.fromJSON(classKind));
          });
          this.classKinds$ = of(classKinds);
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
  deleteClassKind(classKId) {
    this.classService.deleteClassKind(classKId).subscribe(
      data => {
        if(data['code'] == 100) {
          //提示成功并重新获取会员卡列表
          alert("删除成功");
          this.clear();
          this.getClassKind();
        }
        else
        {
          alert("发生错误");
        }
      }
    )
  } 

}


