import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../../core/auth.service';
import { PersonInfoService } from '../../../core/person-info.service';

import { SimpleToken } from '../../../utility/simple_token';
import { SystemManager } from '../../../poto/system_manager';

@Component({
  selector: 'app-sys-manager',
  templateUrl: './sys-manager.component.html',
  styleUrls: ['./sys-manager.component.css']
})
export class SysManagerComponent implements OnInit {

  sysManager: SystemManager;
  genders: SimpleToken[] = [
    new SimpleToken("男", "m"),
    new SimpleToken("女", "f")
  ];

  constructor(
    private authService: AuthService,
    private personInfoService: PersonInfoService
  ) { 
    this.sysManager = this.personInfoService.sysManager;
  }

  ngOnInit() {
  }

  submitData(sysManager: SystemManager) {
    if(sysManager.phone == null || sysManager.phone.length == 0) {
      alert("手机号不能为空");
      return;
    }
    if(sysManager.account == null || sysManager.account.length == 0) {
      alert("账号不能为空");
      return;
    }
    if(sysManager.passwd == null || sysManager.passwd.length == 0) {
      alert("密码不能为空");
      return;
    }
    if(sysManager.sName == null || sysManager.sName.length == 0) {
      alert("姓名不能为空");
      return;
    }
    if(sysManager.gender == null || sysManager.gender.length == 0) {
      alert("性别不能为空");
      return;
    }

    this.authService.sysManagerUpdate(sysManager).subscribe(
      data => {
        if(data['code'] == 100) {
          alert("更新成功");
        }
        else {
          //提示字段错误信息
          let notices: string[] = ['phone', 'account', 'passwd', 'sName', 'gender'];
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
