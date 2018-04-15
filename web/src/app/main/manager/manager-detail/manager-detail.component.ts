import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import 'rxjs/add/operator/switchMap';

import { AuthService } from '../../../core/auth.service';
import { Manager } from '../../../poto/manager';
import { SimpleToken } from '../../../utility/simple_token';
import { PlaceService } from '../../../core/place.service';
import { Place } from '../../../poto/place';

@Component({
  selector: 'app-manager-detail',
  templateUrl: './manager-detail.component.html',
  styleUrls: ['./manager-detail.component.css']
})
export class ManagerDetailComponent implements OnInit {

  manager$: Observable<Manager>;
  places$: Observable<Place[]>;
  genders: SimpleToken[] = [
    new SimpleToken("男", "男"),
    new SimpleToken("女", "女")
  ];

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private authService: AuthService,
    private placeService: PlaceService
  ) { }

  ngOnInit() {
    //获取场馆信息
    const places = [];
    this.placeService.getAllPlace(0).subscribe(
      data => {
        if(data['code'] == 100) {
          data['extend']['info'].map(p => {
            places.push(Place.fromJSON(p));
          })
          this.places$ = of(places);
        }
        else {
          alert("发生错误");
        }
      }
    )    
    //获取管理员信息
    this.route.paramMap.switchMap((params: ParamMap) =>
      this.authService.getManagerInfo(params.get('id'))).subscribe(
      data => {
        //若服务器成功返回消息
        if (data['code'] === 100) {
          this.manager$ = of(data['extend']['info']);
        }
        //若发生错误，提示出错
        else {
          alert("发生错误");
        }
      }
    );
  }

  submitData(manager: Manager) {
    if(manager.pId == null) {
      alert("场馆不能为空");
      return;
    }
    if(manager.phone == null || manager.phone.length == 0) {
      alert("手机号不能为空");
      return;
    }
    if(manager.account == null || manager.account.length == 0) {
      alert("账号不能为空");
      return;
    }
    if(manager.passwd == null || manager.passwd.length == 0) {
      alert("密码不能为空");
      return;
    }
    if(manager.mName == null || manager.mName.length == 0) {
      alert("姓名不能为空");
      return;
    }
    if(manager.gender == null || manager.gender.length == 0) {
      alert("性别不能为空");
      return;
    }

    this.authService.managerUpdate(manager).subscribe(
      data => {
        if(data['code'] == 100) {
          alert("更新成功");
        }
        else {
          //提示字段错误信息
          let notices: string[] = ['phone', 'account', 'passwd', 'mName', 'gender', 'pId'];
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

  //返回管理员列表
  goBack() {
    this.router.navigate(['../'], { relativeTo: this.route });
  }

}
