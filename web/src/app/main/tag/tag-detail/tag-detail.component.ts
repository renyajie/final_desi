import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import 'rxjs/add/operator/switchMap';

import { ClassTag } from '../../../poto/class_tag';
import { SimpleToken } from '../../../utility/simple_token';
import { TagService } from '../../../core/tag.service';

@Component({
  selector: 'app-tag-detail',
  templateUrl: './tag-detail.component.html',
  styleUrls: ['./tag-detail.component.css']
})
export class TagDetailComponent implements OnInit {

  classTag$: Observable<ClassTag>;
  difficultys: SimpleToken[] = [
    new SimpleToken("1星", "1"),
    new SimpleToken("2星", "2"),
    new SimpleToken("3星", "3"),
    new SimpleToken("4星", "4"),
    new SimpleToken("5星", "5")
  ];
  propertys: SimpleToken[] = [
    new SimpleToken("私教", "s"),
    new SimpleToken("团课", "g")
  ];

  tagOne: number = -1;
  tagTwo: number = -1;
  tagThree: number = -1;
  tagFour: number = -1;
  id: number = -1;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private tagService: TagService
  ) { }

  ngOnInit() {
    
    let classTag;
    this.route.paramMap.switchMap((params: ParamMap) =>
      this.tagService.getOneClassTag(params.get('id'))).subscribe(
      data => {
        //若服务器成功返回消息
        if (data['code'] === 100) {
          classTag = ClassTag.fromJSON(data['extend']['info']);

          //获取编号
          this.id = classTag.id;

          //去除第一类标签的值
          if(classTag.relaxed === 1) {
            this.tagOne = 1;
          }
          else if(classTag.intense === 1) {
            this.tagOne = 2;
          }
          else {
            this.tagOne = 3;
          }

          //取出第二类标签
          if(classTag.recovery === 1) {
            this.tagTwo = 1;
          }
          else {
            this.tagTwo = 2;
          }

          //取出第三类和第四类标签的值
          this.tagThree = classTag.nurse;
          this.tagFour = classTag.consume;

          this.classTag$ = of(classTag);
        }
        //若发生错误，提示出错
        else {
          alert("发生错误");
        }
      }
    );
  }

  get diagnostic() { return JSON.stringify(
    'id: ' + this.id + ', tagOne: ' + this.tagOne 
    + ', tagTwo: ' + this.tagTwo + ', tagThree: ' + this.tagThree 
    + ', tagFour: ' + this.tagFour); }

  //提交更新信息
  submitData() {
    
    this.tagService.updateClassTag(
      this.id, this.tagOne, this.tagTwo, this.tagThree, this.tagFour).subscribe(
      data => {
        if (data['code'] === 100) {
          alert("信息更新成功");
        }
        //若发生错误，提示出错
        else {
          //提示字段错误信息
          alert("发生错误");
        }
      }
    )
  }

  //返回课程标签列表
  goBack() {
    this.router.navigate(['../'], { relativeTo: this.route });
  }

}
