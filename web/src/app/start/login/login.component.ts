import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../core/auth.service';
import { PersonInfoService } from '../../core/person-info.service';
import { SystemManager } from '../../poto/system_manager';
import { Manager } from '../../poto/manager';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  //判断用户选择的登录身份
  isSysManager: number;
  account: string;
  passwd: string;

  constructor(
    public router: Router,
    public authService: AuthService,
    public personInfoService: PersonInfoService
  ) { 
    this.isSysManager = 1;
  }

  get diagnostic() { return JSON.stringify('account: ' + this.account + ', passwd: ' + this.passwd); }

  ngOnInit() {
  }

  //登录方法
  login() {
    //判断账号密码是否为空
    if (this.account == null || this.account.length == 0) {
      alert("账号不能为空");
      return;
    }
    if (this.passwd == null || this.passwd.length == 0) {
      alert("密码不能为空");
      return;
    }
    //根据用户身份选择登录
    if(this.isSysManager) {
      this.authService.sysManagerLogIn(
        { account: this.account, passwd: this.passwd } as SystemManager)
        .subscribe(
          data => {
            //若登陆成功，保存身份信息，并跳转主页面
          if (data['code'] == 100) {
            this.personInfoService.isSysManager = true;
            this.personInfoService.sysManager = SystemManager.fromJSON(data['extend']['info']);
            this.personInfoService.id = this.personInfoService.sysManager.id;
            console.log(this.personInfoService.sysManager);
            this.router.navigate(['main']);
          }
          //若失败，提示失败
          else {
            alert("登录信息错误")
          }
        }
        )
    } else {
      this.authService.managerLogIn(
        { account: this.account, passwd: this.passwd } as Manager)
        .subscribe(
          data => {
            //若登陆成功，保存身份信息，并跳转主页面
          if (data['code'] == 100) {
            this.personInfoService.isSysManager = false;
            this.personInfoService.manager = Manager.fromJSON(data['extend']['info']);
            this.personInfoService.id = this.personInfoService.manager.id;
            console.log(this.personInfoService.manager);
            this.router.navigate(['main']);
          }
          //若失败，提示失败
          else {
            alert("登录信息错误")
          }
        }
        )
    }
  }

  //注册方法
  register() {
    this.router.navigate(['start/register']);
  }

}
