import { Component, OnInit } from '@angular/core';

import { AuthService } from '../../../core/auth.service';
import { PersonInfoService } from '../../../core/person-info.service';

import { Manager } from '../../../poto/manager';
import { SimpleToken } from '../../../utility/simple_token';

@Component({
  selector: 'app-manager',
  templateUrl: './manager.component.html',
  styleUrls: ['./manager.component.css']
})
export class ManagerComponent implements OnInit {

  manager: Manager;
  genders: SimpleToken[] = [
    new SimpleToken("男", "男"),
    new SimpleToken("女", "女")
  ];

  constructor(
    private authService: AuthService,
    private personInfoService: PersonInfoService
  ) { 
    this.manager = this.personInfoService.manager;
  }

  ngOnInit() {
  }

  submitData(manager: Manager) {
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

    this.authService.sysManagerUpdate(manager).subscribe(
      data => {
        if(data['code'] == 100) {
          alert("更新成功");
        }
        else {
          //提示字段错误信息
          let notices: string[] = ['phone', 'account', 'passwd', 'mName', 'gender'];
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

}
