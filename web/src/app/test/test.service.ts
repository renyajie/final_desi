import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';

import { SystemManager } from '../poto/system_manager';

/**
 * 测试web和后台的连接
 */
@Injectable()
export class TestService {

  url = 'api/test/angular/';

  constructor(
    private httpClient: HttpClient) { }

  /**
   * 添加系统管理员
   * @param sysManager 
   */
  addASystemManager(sysManager: SystemManager) {
    let tmp = new SystemManager(null, null);
    tmp = Object.assign(tmp, sysManager);
    const testUrl = this.url + 'addSysManager';
    return this.httpClient.post(testUrl, tmp);
  }

}
