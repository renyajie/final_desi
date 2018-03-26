import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';

import { Place } from '../poto/place';
/**
 * 进行和场馆有关的交互
 */
@Injectable()
export class PlaceService {

  url = 'api/setting/';

  constructor(
    private httpClient: HttpClient
  ) { }

  /**
   * 获取所有的场地信息
   */
  getAllPlace(id?, placeName?) {
    const url = this.url + 'getPlace';
    const params = new HttpParams()
    .set('id', id ? id : '')
    .set('placeName', placeName ? placeName : '')
    return this.httpClient.get(url, { params });
  }

}
