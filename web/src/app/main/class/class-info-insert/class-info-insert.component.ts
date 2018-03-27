import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { DatepickerModule } from 'ngx-bootstrap';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import 'rxjs/add/operator/switchMap';

import { SimpleToken } from '../../../utility/simple_token';
import { ClassService } from '../../../core/class.service';
import { ClassInfo } from '../../../poto/class_info';
import { DateFormat } from '../../../utility/date-format';
import { ClassKind } from '../../../poto/class_kind';
import { TeacherService } from '../../../core/teacher.service';
import { Teacher } from '../../../poto/teacher';
import { PlaceService } from '../../../core/place.service';
import { Place } from '../../../poto/place';
import { PersonInfoService } from '../../../core/person-info.service';

@Component({
  selector: 'app-class-info-insert',
  templateUrl: './class-info-insert.component.html',
  styleUrls: ['./class-info-insert.component.css']
})
export class ClassInfoInsertComponent implements OnInit {

  classInfo$: Observable<ClassInfo>;
  classKinds$: Observable<ClassKind[]>;
  teachers$: Observable<Teacher[]>;

  my_staTime: string;
  my_endTime: string;

  courseTimes:SimpleToken[] = [
    new SimpleToken('07:00:00', '07:00:00'),
    new SimpleToken('08:00:00', '08:00:00'),
    new SimpleToken('09:00:00', '09:00:00'),
    new SimpleToken('10:00:00', '10:00:00'),
    new SimpleToken('11:00:00', '11:00:00'),
    new SimpleToken('12:00:00', '12:00:00'),
    new SimpleToken('13:00:00', '13:00:00'),
    new SimpleToken('14:00:00', '14:00:00'),
    new SimpleToken('15:00:00', '15:00:00'),
    new SimpleToken('16:00:00', '16:00:00'),
    new SimpleToken('17:00:00', '17:00:00'),
    new SimpleToken('18:00:00', '18:00:00'),
    new SimpleToken('19:00:00', '19:00:00')
  ];

  //日期选择框文本
  beforeBtnText = '选择上课日期';
  //是否显示日期选择框
  showBeforeDate = false;
  //用户选择的上课日期
  beforeDate: Date;
  //判断用户是否为第一次选择
  firstChooseForBeforeDate = true;
  //当前日期，最大可选日期和最小可选日期
  current: Date;
  maxDate: Date;
  minDate: Date;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private classService: ClassService,
    private teacherService: TeacherService,
    private placeService: PlaceService,
    private personInfoService: PersonInfoService
  ) { 
    //用到的参数一定要初始化，你无法预知你会什么时候调用它。
    this.beforeDate = new Date();
    this.current = new Date();
    this.maxDate = new Date();
    this.minDate = new Date();
    //设置最大可选日期是30天后，最小可选日期是今天
    this.maxDate.setDate(this.current.getDate() + 3 * 10);
    this.minDate.setDate(this.current.getDate());
  }

  ngOnInit() {
    //获取瑜伽馆信息
    let place: Place;
    let classInfo = new ClassInfo();
    this.placeService.getAllPlace(this.personInfoService.manager.pId).subscribe(
      data => {
        if(data['code'] == 100) {
          data['extend']['info'].map(p => {
            place = Place.fromJSON(p);
          })
          classInfo.pId = place.id;
          classInfo.pName = place.sName;
          this.classInfo$ = of(classInfo);
        }
        else {
          alert("发生错误");
        }
      }
    )
    //获取课程种类
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
    //获取教师列表
    let teachers = [];
    this.teacherService.getTeacher().subscribe(
      data => {
        //若服务器成功返回数据
        if(data['code'] === 100) {
          data['extend']['info'].map(teacher => {
            teachers.push(Teacher.fromJSON(teacher));
          })
          this.teachers$ = of(teachers);
        }
        //若出错
        else {
          alert("服务器发生错误");
        }
      }
    )
  }

  showBeforePicker(event: any) {
    if (this.firstChooseForBeforeDate) {
      this.firstChooseForBeforeDate = false;
    }
    this.showBeforeDate = !this.showBeforeDate;
    this.beforeBtnText = this.showBeforeDate ? '完成日期选择' : '选择上课日期';
  }

  get beforeDateText() {
    if (this.firstChooseForBeforeDate) {
      return  '未选择上课日期';
    }
    return DateFormat.formatWithDay(this.beforeDate);
  }

  //提交更新信息
  addClassInfo(classInfo: ClassInfo) {
    if(this.firstChooseForBeforeDate) {
      alert("请选择课程日期");
      return;
    }
    if(this.my_staTime == null || this.my_staTime.length == 0) {
      alert("请选择课程开始时间");
      return;
    }
    if(this.my_endTime == null || this.my_endTime.length == 0) {
      alert("请选择课程结束时间");
      return;
    }
    //检查时间的合理性
    let startHour = this.my_staTime.substring(0, this.my_staTime.indexOf(':'));
    let endHour = this.my_endTime.substring(0, this.my_endTime.indexOf(':'));
    if(startHour >= endHour) {
      alert("课程开始时间必须小于课程结束时间");
      return;
    }
    classInfo.cDay = this.beforeDateText;
    classInfo.staTime = classInfo.cDay + ' ' + this.my_staTime;
    classInfo.endTime = classInfo.cDay + ' ' + this.my_endTime;
    //检查数据的完备性
    if(classInfo.claKId == null) {
      alert("课程不能为空");
      return;
    }
    if(classInfo.teaId == null) {
      alert("教师不能为空");
      return;
    }
    if(classInfo.cDay == null || classInfo.cDay.length == 0) {
      alert("上课日期不能为空");
      return;
    }
    if(classInfo.staTime == null || classInfo.staTime.length == 0) {
      alert("开始时间不能为空");
      return;
    }
    if(classInfo.endTime == null || classInfo.endTime.length == 0) {
      alert("结束时间不能为空");
      return;
    }
    if(classInfo.expend == null || classInfo.expend.length == 0) {
      alert("价格不能为空");
      return;
    }
    if(classInfo.allowance == null || classInfo.allowance.length == 0) {
      alert("课程容量不能为空");
      return;
    }
    //提交服务器
    this.classService.addClassInfo(classInfo).subscribe(
      data => {
        if (data['code'] === 100) {
          alert("信息更新成功");
        }
        //若发生错误，提示出错
        else {
          //提示字段错误信息
          let notices: string[] = ['claKId', 'teaId', 'cDay', 'staTime', 'endTime', 'expend'];
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

  //返回课程信息列表
  goBack() {
    this.router.navigate(['main/class/info']);
  }

}
