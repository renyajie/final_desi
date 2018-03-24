import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';

import { SystemManager } from '../poto/system_manager';
import { Manager } from '../poto/manager';

/**
 * 记录进入系统的用户信息
 */
@Injectable()
export class PersonInfoService {

  url = 'api/auth/';

  private _isSysManager: boolean;
  private _id: number;
  private _sysManager: SystemManager;
  private _manager: Manager;

  constructor(private httpClient: HttpClient) {
    //TODO: 测试信息，测试账号为1的管理员
    this.id = 1;
    this._isSysManager = false;
  }

  set id(id: number) {
    this._id = id;
  }

  get id() {
    return this._id;
  }

  set isSysManager(isSysManager: boolean) {
    this._isSysManager = isSysManager;
  }

  get isSysManager() {
    return this._isSysManager;
  }

  set sysManager(sysManager: SystemManager) {
    this._sysManager = sysManager;
  }

  get sysManager() {
    return this._sysManager;
  }

  set manager(manager: Manager) {
    this._manager = manager;
  }

  get manager() {
    return this._manager;
  }

  getPersonInfo() {
    //根据用户登录信息获取用户身份参数
    //根据请求者的身份构造不同的url和请求参数
    let url = '';
    if (this._isSysManager) {
      url = this.url + 'getSysManagerInfo';
    } else {
      url = this.url + 'getManagerInfo';
    }
    const params = new HttpParams()
    .set('id', this.id + '');
    return this.httpClient.get(url, {params});
  }

}
