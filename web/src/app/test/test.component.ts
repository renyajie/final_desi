import { Component, OnInit } from '@angular/core';
import { SystemManager } from '../poto/system_manager';
import { TestService } from './test.service';

@Component({
  selector: 'app-test',
  templateUrl: './test.component.html',
  styleUrls: ['./test.component.css']
})
export class TestComponent implements OnInit {

  sysManager: SystemManager= {
    sName: '冰糖'
  }

  constructor(
    private testService: TestService
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

}
