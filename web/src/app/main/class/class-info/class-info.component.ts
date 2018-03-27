import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router'
import { DatepickerModule } from 'ngx-bootstrap';
import { Observable } from 'rxjs/Observable';

import 'rxjs/add/operator/map';
import { of } from 'rxjs/observable/of';

import { ClassService } from '../../../core/class.service';
import { DateFormat } from '../../../utility/date-format';
import { ClassInfo } from '../../../poto/class_info';
import { ClassKind } from '../../../poto/class_kind';
import { SimpleToken } from '../../../utility/simple_token';

@Component({
  selector: 'app-class-info',
  templateUrl: './class-info.component.html',
  styleUrls: ['./class-info.component.css']
})
export class ClassInfoComponent implements OnInit {

  classKinds$: Observable<ClassKind[]>;
  pageInfo$: Observable<any>;
  classInfos$: Observable<any>;

  propertys: SimpleToken[] = [
    new SimpleToken("私教", "s"),
    new SimpleToken("团课", "g")
  ];

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
  //用于搜索的用户编号，课程种类
  teaName: string = '';
  classKId: string = '';
  property: string = '';

  constructor(
    private classService: ClassService
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
    let classKinds = [];
    this.classService.getClassKind(0).subscribe(
      data => {
        //若服务器成功返回数据
        if(data['code'] === 100) {
          data['extend']['info'].map(classKind => {
            classKinds.push(ClassKind.fromJSON(classKind));
          })
          //TODO removes
          console.log(classKinds);
          this.classKinds$ = of(classKinds);
        }
        //若出错
        else {
          alert("服务器发生错误");
        }
      }
    )
    this.getClassInfo();
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
    this.teaName = '';
    this.classKId = '';
    this.property = '';
  }

  getClassInfo(pn?, classKId?, teaName?, property?) {
    //判断日期选择的合理性
    if (!this.firstChooseForBeforeDate && !this.firstChooseForAfterDate) {
      if (this.beforeDate.getDate() > this.afterDate.getDate()) {
        alert("最早日期不能晚于最迟日期，请重新选择");
        this.clear();
      }
    }
    //发出搜索，并展示结果
    const classInfos = [];
    this.classService.getClassInfo(
      pn, classKId, teaName, property,
      this.firstChooseForBeforeDate ? null : DateFormat.formatWithDay(this.beforeDate),
      this.firstChooseForAfterDate ? null : DateFormat.formatWithDay(this.afterDate)).subscribe(
        data => {
          //若成功返回数据，为元素赋值
          if (data['code'] === 100) {
            data['extend']['pageInfo']['list'].map(classInfo => {
              classInfos.push(ClassInfo.fromJSON(classInfo));
            });
            this.classInfos$ = of(classInfos);
            this.pageInfo$ = of(data['extend']['pageInfo']);
          }
          //若发生错误
          else {
            alert("服务器响应错误")
          }
        }
      )
  }

  //删除课程信息
  deleteClassInfo(classId) {
    this.classService.deleteClassInfo(classId).subscribe(
      data => {
        if(data['code'] == 100) {
          //提示成功并重新获取课程信息
          alert("删除成功");
          this.clear();
          this.getClassInfo();
        }
        else
        {
          alert("发生错误");
        }
      }
    )
  } 

}
