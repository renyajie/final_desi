import { Component, OnInit } from '@angular/core';
import { SystemManager } from '../poto/system_manager';
import { TestService } from './test.service';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-test',
  templateUrl: './test.component.html',
  styleUrls: ['./test.component.css']
})
export class TestComponent implements OnInit {

  sysManager: SystemManager= {
    sName: '冰糖'
  }

  message: string = '未接收到';

  constructor(
    private testService: TestService,
    private httpClient: HttpClient
  ) { }

  ngOnInit() {
  }

  /**
   * 添加系统管理员
   */
  addSystemManager() {
    //判断账号密码是否为空
    if (this.sysManager.account == null || this.sysManager.account.length == 0) {
      alert("账号不能为空");
      return;
    }
    if (this.sysManager.passwd == null || this.sysManager.passwd.length == 0) {
      alert("密码不能为空");
      return;
    }
    
    //调用服务提交添加信息
    this.testService.addASystemManager(this.sysManager)
      .subscribe(data => {
        if(data['code'] == 100) {
          alert(data['extend']['info']);
        }
        else {
          alert('添加失败');
        }
      })
  }

  // POST方法，直接通过匿名的语法向服务器提交一个对象
  postMessage() {
    this.testService.postMessage().subscribe(
      data => {
        this.message
          = `Message: ${data['msg']}`;
      }
    )
  }

}
