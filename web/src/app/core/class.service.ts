import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';

import { ClassKind } from '../poto/class_kind';
import { ClassInfo } from '../poto/class_info';
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
  getClassKind(isPage, pn?, classKId?, classKName?, property?, difficulty?) {
    const url = this.url + 'getClassKind';
    let params = new HttpParams()
      .set('isPage', isPage)
      .set('managerId', this.personInfoService.id + '')
      .set('pn', pn ? pn : '')
      .set('classKId', classKId ? classKId : '')
      .set('classKName', classKName ? classKName : '')
      .set('property', property ? property : '')
      .set('difficulty', difficulty ? difficulty : '')
    return this.httpClient.get(url, { params });
  }

  /**
   * 获取某个课程种类的信息
   * @param id 
   */
  getClassKindInfo(id) {
    const url = this.url + 'getClassKindInfo';
    const params = new HttpParams()
    .set('id', id);
    return this.httpClient.get(url, { params });
  }

  /**
   * 更新课程种类
   * @param classKind 
   */
  classKindUpdate(classKind: ClassKind) {
    const url = this.url + 'updateClassKind';
    return this.httpClient.put(url, classKind);
  }

  /**
   * 添加课程种类
   * @param classKind 
   */
  addClassKind(classKind: ClassKind) {
    let tmp = new ClassKind(null, null);
    tmp = Object.assign(tmp, classKind);
    const url = this.url + 'addClassKind';
    return this.httpClient.post(url, tmp);
  }

  /**
   * 删除课程种类
   * @param classKId 
   */
  deleteClassKind(classKId) {
    const url = this.url + 'deleteClassKind';
    const params = new HttpParams()
    .set('classKId', classKId);
    return this.httpClient.delete(url, { params });
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
        .set('isPage', 1 + '')
        .set('pn', pn ? pn : '')
        .set('placeId', this.personInfoService.manager.pId + '')
        .set('classKId', classKId ? classKId : '')
        .set('userId', userId ? userId : '')
        .set('cardId', cardId ? cardId : '')
        .set('before', before)
        .set('after', after);
    }
    if (before === null && after === null) {
      params = new HttpParams()
        .set('isPage', 1 + '')
        .set('pn', pn ? pn : '')
        .set('placeId', this.personInfoService.manager.pId + '')
        .set('classKId', classKId ? classKId : '')
        .set('userId', userId ? userId : '')
        .set('cardId', cardId ? cardId : '')
    }
    if (before === null && after != null) {
      params = new HttpParams()
        .set('isPage', 1 + '')
        .set('pn', pn ? pn : '')
        .set('placeId', this.personInfoService.manager.pId + '')
        .set('classKId', classKId ? classKId : '')
        .set('userId', userId ? userId : '')
        .set('cardId', cardId ? cardId : '')
        .set('after', after);
    }
    if (before != null && after === null) {
      params = new HttpParams()
        .set('isPage', 1 + '')
        .set('pn', pn ? pn : '')
        .set('placeId', this.personInfoService.manager.pId + '')
        .set('classKId', classKId ? classKId : '')
        .set('userId', userId ? userId : '')
        .set('cardId', cardId ? cardId : '')
        .set('before', before);
    }
    return this.httpClient.get(url, { params });
  }

  /**
   * 获取某个课程信息
   * @param id 
   */
  getOneClassInfo(id) {
    const url = this.url + 'getOneClassInfo';
    const params = new HttpParams()
    .set('id', id);
    return this.httpClient.get(url, { params });
  }

  /**
   * 获取课程信息
   * @param pn 
   * @param classKId 
   * @param teaName 
   * @param property 
   * @param before 
   * @param after 
   */
  getClassInfo(isPage, pn?, classKId?, teaName?, property?, before?, after?) {
    const url = 'api/order/getClassInfo';
    let params: HttpParams;
    if (before != null && after != null) {
      params = new HttpParams()
        .set('isPage', isPage)
        .set('pn', pn ? pn : '')
        .set('placeId', this.personInfoService.manager.pId + '')
        .set('classKId', classKId ? classKId : '')
        .set('teaName', teaName ? teaName : '')
        .set('property', property ? property : '')
        .set('before', before)
        .set('after', after);
    }
    if (before === null && after === null) {
      params = new HttpParams()
        .set('isPage', isPage)
        .set('pn', pn ? pn : '')
        .set('placeId', this.personInfoService.manager.pId + '')
        .set('classKId', classKId ? classKId : '')
        .set('teaName', teaName ? teaName : '')
        .set('property', property ? property : '')
    }
    if (before === null && after != null) {
      params = new HttpParams()
        .set('isPage', isPage)
        .set('pn', pn ? pn : '')
        .set('placeId', this.personInfoService.manager.pId + '')
        .set('classKId', classKId ? classKId : '')
        .set('teaName', teaName ? teaName : '')
        .set('property', property ? property : '')
        .set('after', after);
    }
    if (before != null && after === null) {
      params = new HttpParams()
        .set('isPage', isPage)
        .set('pn', pn ? pn : '')
        .set('placeId', this.personInfoService.manager.pId + '')
        .set('classKId', classKId ? classKId : '')
        .set('teaName', teaName ? teaName : '')
        .set('property', property ? property : '')
        .set('before', before);
    }
    return this.httpClient.get(url, { params });
  }

  /**
   * 删除课程信息
   * @param classId 
   */
  deleteClassInfo(classId) {
    const url = this.url + 'deleteClassInfo';
    const params = new HttpParams()
    .set('classId', classId);
    return this.httpClient.delete(url, { params });
  }

  /**
   * 更新课程信息
   * @param classInfo 
   */
  classInfoUpdate(classInfo: ClassInfo) {
    let tmp = new ClassKind();
    tmp = Object.assign(tmp, classInfo);
    const url = this.url + 'updateClassInfo';
    return this.httpClient.put(url, tmp);
  }

  /**
   * 添加课程信息
   * @param classInfo 
   */
  addClassInfo(classInfo: ClassInfo) {
    let tmp = new ClassInfo(null, null);
    tmp = Object.assign(tmp, classInfo);
    const url = this.url + 'addClassInfo';
    return this.httpClient.post(url, tmp);
  }

}
