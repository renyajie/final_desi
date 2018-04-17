import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router'
import { Observable } from 'rxjs/Observable';

import 'rxjs/add/operator/map';
import { of } from 'rxjs/observable/of';

import { ClassService } from '../../core/class.service';
import { ScoreService } from '../../core/score.service';

import { ClassKind } from '../../poto/class_kind';
import { Score } from '../../poto/score';


@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.css']
})
export class CommentComponent implements OnInit {

  classKinds$: Observable<ClassKind[]>;
  pageInfo$: Observable<any>;
  scores$: Observable<any>;

  //用户搜索的课程种类编号
  classKId: string = '';

  constructor(
    private classService: ClassService,
    private scoreService: ScoreService
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
    this.getScore();
  }

  /**
   * 清空选择输入框样式，重置字段
   */
  clear() {
    this.classKId = '';
  }

  getScore(pn?, classKId?) {
    //发出搜索，并展示结果
    const scores = [];
    this.scoreService.getScore(pn, classKId).subscribe(
        data => {
          //若成功返回数据，为元素赋值
          if (data['code'] === 100) {
            data['extend']['pageInfo']['list'].map(score => {
              scores.push(Score.fromJSON(score));
            });
            this.scores$ = of(scores);
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
