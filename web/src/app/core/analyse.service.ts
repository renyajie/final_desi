import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';

import { PersonInfoService } from './person-info.service';
/**
 * 进行和统计分析有关的交互
 */
@Injectable()
export class AnalyseService {

  url = 'api/analyse/';

  constructor(
    private httpClient: HttpClient,
    private personInfoService: PersonInfoService
  ) { }

  /**
   * 根据时间段统计预约数量
   * @param timeLength 
   */
  getByClassTime(timeLength: number) {
    const url = this.url + 'getByClassTime';
    const params = new HttpParams()
    .set('timeLength', timeLength + '')
    .set('placeId', this.personInfoService.manager.pId + '')
    return this.httpClient.get(url, { params });
  }

  /**
   * 统计每天的预约量
   * @param timeLength 
   */
  getByOrderNumber(timeLength: number) {
    const url = this.url + 'getByOrderNumber';
    const params = new HttpParams()
    .set('timeLength', timeLength + '')
    .set('placeId', this.personInfoService.manager.pId + '')
    return this.httpClient.get(url, { params });
  }

  /**
   * 统计每门课程的预约量
   * @param timeLength 
   */
  getByClassNumber(timeLength: number) {
    const url = this.url + 'getByClassNumber';
    const params = new HttpParams()
    .set('timeLength', timeLength + '')
    .set('placeId', this.personInfoService.manager.pId + '')
    return this.httpClient.get(url, { params });
  }

}
