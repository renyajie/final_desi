import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import { of } from 'rxjs/observable/of';

import { AuthService } from '../../core/auth.service';
import { PlaceService } from '../../core/place.service';

import { Manager } from '../../poto/manager';
import { Place } from '../../poto/place';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  places$: Observable<Place[]>;
  manager: Manager;

  constructor(
    private authService: AuthService,
    private router: Router,
    private placeService: PlaceService
  ) { 
    this.manager = new Manager(null, null);
  }

  get diagnostic() { return JSON.stringify(this.manager); }

  ngOnInit() {
    // 获取场馆基本信息，用于下拉列表
    let places = [];
    this.placeService.getAllPlace(0)
    .subscribe(
      data => {
        //若服务器成功返回数据
        if(data['code'] === 100) {
          data['extend']['info'].map(place => {
            places.push(Place.fromJSON(place));
          })
          //TODO removes
          console.log(places);
          this.places$ = of(places);
        }
        //若出错
        else {
          alert("服务器发生错误");
        }
      }
    )
  }

  /**
   * 提交数据
   */
  submitData() {
    //检查数据的合法性
    if (this.manager.mName == null || this.manager.mName.length === 0) {
      alert("姓名不能为空");
      return;
    }
    if (this.manager.phone == null || this.manager.phone.length === 0) {
      alert("手机不能为空");
      return;
    }
    if (this.manager.gender == null || this.manager.gender.length === 0) {
      alert("性别不能为空");
      return;
    }
    if (this.manager.account == null || this.manager.account.length === 0) {
      alert("账号不能为空");
      return;
    }
    if (this.manager.passwd == null || this.manager.passwd.length === 0) {
      alert("密码不能为空");
      return;
    }
    if (this.manager.pId == null) {
      alert("场馆编号不能为空");
      return;
    }
    //提交数据
    this.authService.managerRegister(this.manager).subscribe(
      data => {
        //若服务器返回成功
        if (data['code'] === 100) {
          alert("注册信息成功");
          this.router.navigate(['start/login']);
        }
        else {
          //提示其他错误
          if(data['extend']['other'] != null) {
            alert(data['extend']['other']);
            return;
          }

          //提示字段错误信息
          let notices: string[] = ['mName', 'phone', 'account', 'passwd', 'gender', 'pId'];
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

  /**
   * 返回登录界面
   */
  goToLogin() {
    this.router.navigate(['start/login']);
  }

}
