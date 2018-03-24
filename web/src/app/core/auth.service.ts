import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';

import { SystemManager } from '../poto/system_manager';
import { Manager } from '../poto/manager';

/**
 * 处理登录，注册，更新
 */
@Injectable()
export class AuthService {

  url = 'api/auth/';

  constructor(
    private httpClient: HttpClient
  ) { }

  /**
   * 管理员登录
   * @param manager 
   */
  managerLogIn(manager: Manager) {
    const url = this.url + 'managerLogIn';
    return this.httpClient.post(url, manager);
  }

  /**
   * 系统管理员
   * @param sysManager 
   */
  sysManagerLogIn(sysManager: SystemManager) {
    const url = this.url + 'sysManagerLogIn';
    return this.httpClient.post(url, sysManager);
  }

  /**
   * 管理员注册
   * @param manager 
   */
  managerRegister(manager: Manager) {
    let tmp = new Manager(null, null);
    tmp = Object.assign(tmp, manager);
    const url = this.url + 'managerRegister';
    return this.httpClient.post(url, tmp);
  }

  /**
   * 管理员更新信息
   * @param manager 
   */
  managerUpdate(manager: Manager) {
    const url = this.url + 'managerUpdate';
    return this.httpClient.put(url, manager);
  }

  /**
   * 系统管理员更新信息
   * @param sysManager 
   */
  sysManagerUpdate(sysManager: SystemManager) {
    const url = this.url + 'sysManagerUpdate';
    return this.httpClient.put(url, sysManager);
  }

}
