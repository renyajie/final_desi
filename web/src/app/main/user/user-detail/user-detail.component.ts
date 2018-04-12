import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import 'rxjs/add/operator/switchMap';

import { User } from '../../../poto/user';
import { AuthService } from '../../../core/auth.service';

@Component({
  selector: 'app-user-detail',
  templateUrl: './user-detail.component.html',
  styleUrls: ['./user-detail.component.css']
})
export class UserDetailComponent implements OnInit {

  user$: Observable<User>;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private authService: AuthService
  ) { }

  ngOnInit() {
    this.route.paramMap.switchMap((params: ParamMap) =>
      this.authService.getUserInfo(params.get('id'))).subscribe(
      data => {
        //若服务器成功返回消息
        if (data['code'] === 100) {
          this.user$ = of(data['extend']['info']);
        }
        //若发生错误，提示出错
        else {
          alert("发生错误");
        }
      }
      );
  }

  //提交更新信息
  submitData(user: User) {
    //检查数据的完备性
    if(user.uName == null || user.uName.length == 0) {
      alert("姓名不能为空");
      return;
    }
    if(user.gender == null || user.gender.length == 0) {
      alert("密码不能为空");
      return;
    }
    if(user.age == null) {
      alert("年龄不能为空");
      return;
    }
    if(user.phone == null || user.phone.length == 0) {
      alert("手机号码不能为空");
      return;
    }
    if(user.passwd == null || user.passwd.length == 0) {
      alert("密码不能为空");
      return;
    }
    this.authService.userUpdate(user).subscribe(
      data => {
        if (data['code'] === 100) {
          alert("信息更新成功");
        }
        //若发生错误，提示出错
        else {
          //提示字段错误信息
          let notices: string[] = ['mName', 'phone', 'passwd', 'age', 'gender'];
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

  //返回用户列表
  goBack() {
    this.router.navigate(['../'], { relativeTo: this.route });
  }

}
