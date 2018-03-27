import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router'
import { Observable } from 'rxjs/Observable';

import 'rxjs/add/operator/map';
import { of } from 'rxjs/observable/of';
import { AuthService } from '../../../core/auth.service';

import { Manager } from '../../../poto/manager';

@Component({
  selector: 'app-manager-list',
  templateUrl: './manager-list.component.html',
  styleUrls: ['./manager-list.component.css']
})
export class ManagerListComponent implements OnInit {

  id: string;
  account: string;
  mName: string;
  sName: string;

  pageInfo$: Observable<any>;
  managers$: Observable<any>;

  constructor(
    private authService: AuthService
  ) { }

  ngOnInit() {
    this.getManagerList();
  }

  //清空搜索内容
  clear() {
    this.id = '';
    this.account = '';
    this.mName = '';
    this.sName = '';
  }

  getManagerList(pn?, id?, account?, mName?, sName?) {
    //发出搜索，并展示结果
    const managers = [];
    this.authService.getManagerList(pn, id, account, mName, sName).subscribe(
      data => {
         //若成功返回数据，为元素赋值
         if (data['code'] === 100) {
          data['extend']['pageInfo']['list'].map(manager => {
            managers.push(Manager.fromJSON(manager));
          });
          this.managers$ = of(managers);
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
