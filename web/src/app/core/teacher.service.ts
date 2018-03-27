import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';

import { PersonInfoService } from './person-info.service';
/**
 * 进行和场馆有关的交互
 */
@Injectable()
export class TeacherService {

  url = 'api/setting/';

  constructor(
    private httpClient: HttpClient,
    private personInfoService: PersonInfoService
  ) { }

  /**
   * 获取所有的教师信息
   */
  getTeacher(id?) {
    const url = this.url + 'getTeacher';
    const params = new HttpParams()
    .set('id', id ? id : '')
    .set('pId', this.personInfoService.manager.pId + '')
    return this.httpClient.get(url, { params });
  }

}
