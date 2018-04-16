import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';

import { ClassTag } from '../poto/class_tag';
import { PersonInfoService } from './person-info.service';

@Injectable()
export class TagService {

  url = 'api/setting/'

  constructor(
    private httpClient: HttpClient,
    private personInfoService: PersonInfoService
  ) { }

  /**
   * 获取课程标签
   * @param pn 第几页
   * @param classKId 课程编号
   */
  getClassTag(pn?, classKId?) {
    const url = this.url + 'getClassTag';
    const params = new HttpParams()
    .set('pn', pn ? pn : '')
    .set('classKindId', classKId ? classKId : '')
    .set('placeId', this.personInfoService.manager.pId + '');
    return this.httpClient.get(url, { params });
  }

  /**
   * 获取一个课程标签
   * @param id 课程编号
   */
  getOneClassTag(id) {
    const url = this.url + 'getOneClassTag';
    const params = new HttpParams()
    .set('id', id + '');
    return this.httpClient.get(url, { params });
  }

  /**
   * 更新课程标签
   * @param id 标签编号
   * @param tagOne 
   * @param tagTwo 
   * @param tagThree 
   * @param tagFour 
   */
  updateClassTag(id: number, tagOne: number, tagTwo: number, tagThree: number, tagFour: number) {
    const url = this.url + 'updateClassTag';
    const params = new HttpParams()
    .set('id', id + '')
    .set('tagOne', tagOne + '')
    .set('tagTwo', tagTwo + '')
    .set('tagThree', tagThree + '')
    .set('tagFour', tagFour + '');
    return this.httpClient.post(url, params);
  }

}
