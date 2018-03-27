import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import 'rxjs/add/operator/switchMap';

import { Place } from '../../../poto/place';
import { ClassKind } from '../../../poto/class_kind';

import { ClassService } from '../../../core/class.service';
import { PersonInfoService } from '../../../core/person-info.service';
import { PlaceService } from '../../../core/place.service';

import { SimpleToken } from '../../../utility/simple_token';

@Component({
  selector: 'app-class-kind-insert',
  templateUrl: './class-kind-insert.component.html',
  styleUrls: ['./class-kind-insert.component.css']
})
export class ClassKindInsertComponent implements OnInit {

  classKind$: Observable<ClassKind>;

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

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private classService: ClassService,
    private personInfoService: PersonInfoService,
    private placeService: PlaceService
  ) { }

  ngOnInit() {
    let place: Place;
    let classKind = new ClassKind();
    this.placeService.getAllPlace(0, this.personInfoService.manager.pId).subscribe(
      data => {
        if(data['code'] == 100) {
          data['extend']['info'].map(p => {
            place = Place.fromJSON(p);
          })
          classKind.pId = place.id;
          classKind.pName = place.sName;
          this.classKind$ = of(classKind);
        }
        else {
          alert("发生错误");
        }
      }
    )
  }

  //添加课程种类
  addClassKind(classKind: ClassKind) {
    //检查数据的完备性
    if(classKind.claKName == null || classKind.claKName.length == 0) {
      alert("课程名称不能为空");
      return;
    }
    if(classKind.property == null || classKind.property.length == 0) {
      alert("课程属性不能为空");
      return;
    }
    if(classKind.difficulty == null) {
      alert("难度不能为空");
      return;
    }
    if(classKind.intro == null || classKind.intro.length == 0) {
      alert("课程简介不能为空");
      return;
    }
    //提交服务器
    this.classService.addClassKind(classKind).subscribe(
      data => {
        if (data['code'] === 100) {
          alert("信息更新成功");
        }
        //若发生错误，提示出错
        else {
          //提示字段错误信息
          let notices: string[] = ['property', 'claKName', 'difficulty', 'intro'];
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

  //返回课程种类列表
  goBack() {
    this.router.navigate(['main/class/kind']);
  }

}
