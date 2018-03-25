import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router'
import { Observable } from 'rxjs/Observable';

import 'rxjs/add/operator/map';
import { of } from 'rxjs/observable/of';
import { AuthService } from '../../../core/auth.service';

import { User } from '../../../poto/user';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  id: string;
  phone: string;
  uName: string;

  pageInfo$: Observable<any>;
  users$: Observable<any>;

  constructor(
    private authService: AuthService
  ) { }

  ngOnInit() {
    //页面初始化获取所有记录
    this.getUser();
  }

  //清空搜索内容
  clear() {
    this.id = '';
    this.phone = '';
    this.uName = '';
  }

  getUser(pn?, id?, phone?, uName?) {
    //发出搜索，并展示结果
    const users = [];
    this.authService.getUser(pn, id, phone, uName).subscribe(
      data => {
         //若成功返回数据，为元素赋值
         if (data['code'] === 100) {
          data['extend']['pageInfo']['list'].map(user => {
            users.push(User.fromJSON(user));
          });
          this.users$ = of(users);
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
