import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';

import { SystemManager } from '../poto/system_manager';
import { Manager } from '../poto/manager';
import { User } from '../poto/user';

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
   * 系统管理员登录
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

  /**
   * 用户更新信息
   * @param user 
   */
  userUpdate(user: User) {
    const url = this.url + 'userUpdate';
    return this.httpClient.put(url, user);
  }

  /**
   * 查询用户信息
   * @param pn 第几页
   * @param id 用户编号
   * @param phone 手机号
   * @param uName 姓名
   */
  getUser(pn?, id?, phone?, uName?) {
    const url = this.url + 'getUser';
    const params = new HttpParams()
      .set('pn', pn ? pn : '')
      .set('id', id ? id : '')
      .set('phone', phone ? phone : '')
      .set('uName', uName ? uName : '');
    return this.httpClient.get(url, {params});
  }

  /**
   * 获取用户信息
   * @param id 
   */
  getUserInfo(id) {
    const url = this.url + 'getUserInfo';
    const params = new HttpParams()
    .set('id', id);
    return this.httpClient.get(url, { params });
  }

}
