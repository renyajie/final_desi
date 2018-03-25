import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';

import { ClassKind } from '../poto/class_kind';
import { PersonInfoService } from './person-info.service';

/**
 * 和课程信息，课程种类有管的交互
 */
@Injectable()
export class ClassService {

  url = 'api/setting/';

  constructor(
    private httpClient: HttpClient,
    private personInfoService: PersonInfoService
  ) { }

  /**
   * 管理员获取课程种类
   */
  getClassKind() {
    const url = this.url + 'getClassKind';
    let params = new HttpParams()
      .set('managerId', this.personInfoService.id + '');
    return this.httpClient.get(url, { params });
  }

  /**
   * 获取约课订单
   * @param pn 
   * @param classKId 
   * @param userId 
   * @param cardId 
   * @param before 
   * @param after 
   */
  getClassOrder(pn?, classKId?, userId?, cardId?, before?, after?) {
    const url = 'api/order/getClassOrder'
    let params: HttpParams;
    if (before != null && after != null) {
      params = new HttpParams()
        .set('pn', pn ? pn : '')
        .set('classKId', classKId ? classKId : '')
        .set('userId', userId ? userId : '')
        .set('cardId', cardId ? cardId : '')
        .set('before', before)
        .set('after', after);
    }
    if (before === null && after === null) {
      params = new HttpParams()
        .set('pn', pn ? pn : '')
        .set('classKId', classKId ? classKId : '')
        .set('userId', userId ? userId : '')
        .set('cardId', cardId ? cardId : '')
    }
    if (before === null && after != null) {
      params = new HttpParams()
        .set('pn', pn ? pn : '')
        .set('classKId', classKId ? classKId : '')
        .set('userId', userId ? userId : '')
        .set('cardId', cardId ? cardId : '')
        .set('after', after);
    }
    if (before != null && after === null) {
      params = new HttpParams()
        .set('pn', pn ? pn : '')
        .set('classKId', classKId ? classKId : '')
        .set('userId', userId ? userId : '')
        .set('cardId', cardId ? cardId : '')
        .set('before', before);
    }
    return this.httpClient.get(url, { params });
  }

}
