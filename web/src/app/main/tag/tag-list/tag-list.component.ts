import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router'
import { Observable } from 'rxjs/Observable';

import 'rxjs/add/operator/map';
import { of } from 'rxjs/observable/of';

import { ClassService } from '../../../core/class.service';
import { TagService } from '../../../core/tag.service';

import { ClassTag } from '../../../poto/class_tag';
import { ClassKind } from '../../../poto/class_kind';
import { SimpleToken } from '../../../utility/simple_token';

@Component({
  selector: 'app-tag-list',
  templateUrl: './tag-list.component.html',
  styleUrls: ['./tag-list.component.css']
})
export class TagListComponent implements OnInit {

  classKinds$: Observable<ClassKind[]>;
  pageInfo$: Observable<any>;
  classTags$: Observable<any>;

  propertys: SimpleToken[] = [
    new SimpleToken("私教", "s"),
    new SimpleToken("团课", "g")
  ];

  //用户搜索的课程种类编号
  classKId: string = '';

  constructor(
    private classService: ClassService,
    private tagService: TagService
  ) { }

  ngOnInit() {
    let classKinds = [];
    this.classService.getClassKind(0).subscribe(
      data => {
        //若服务器成功返回数据
        if(data['code'] === 100) {
          data['extend']['info'].map(classKind => {
            classKinds.push(ClassKind.fromJSON(classKind));
          })
          this.classKinds$ = of(classKinds);
        }
        //若出错
        else {
          alert("服务器发生错误");
        }
      }
    )
    this.getTag();
  }

  /**
   * 清空选择输入框样式，重置字段
   */
  clear() {
    this.classKId = '';
  }

  getTag(pn?, classKId?) {
    //发出搜索，并展示结果
    const classTags = [];
    this.tagService.getClassTag(pn, classKId).subscribe(
        data => {
          //若成功返回数据，为元素赋值
          if (data['code'] === 100) {
            data['extend']['pageInfo']['list'].map(classTag => {
              classTags.push(ClassTag.fromJSON(classTag));
            });
            this.classTags$ = of(classTags);
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
