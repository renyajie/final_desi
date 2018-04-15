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
   * 获取场地信息
   */
  getAllPlace(isPage?, id?, pn?, sName?, address?, phone?) {
    const url = this.url + 'getPlace';
    const params = new HttpParams()
    .set('isPage', isPage + '')
    .set('id', id ? id + '' : '')
    .set('pn', pn ? pn : '')
    .set('sName', sName ? sName : '')
    .set('address', address ? address : '')
    .set('phone', phone ? phone : '')
    return this.httpClient.get(url, { params });
  }

  /**
   * 删除场馆
   * @param id 
   */
  deletePlace(id) {
    const url = this.url + 'deletePlace';
    const params = new HttpParams()
    .set('id', id);
    return this.httpClient.delete(url, { params });
  }

  /**
   * 获取一个场馆信息
   * @param id 
   */
  getOnePlace(id) {
    const url = this.url + 'getOnePlace';
    const params = new HttpParams()
    .set('id', id);
    return this.httpClient.get(url, { params });
  }

  /**
   * 更新场馆信息
   * @param place 
   */
  placeUpdate(place: Place) {
    const url = this.url + 'updatePlace';
    return this.httpClient.post(url, place);
  }

  /**
   * 更新场馆信息，不包括图片
   * @param place 
   */
  updatePlaceSimple(place: Place) {
    const url = this.url + 'updatePlaceSimple';
    return this.httpClient.post(url, place);
  }

  /**
   * 添加场馆信息
   * @param place 
   */
  addPlace(place: Place) {
    let tmp = new Place(null, null);
    tmp = Object.assign(tmp, place);
    const url = this.url + 'addPlace';
    return this.httpClient.post(url, tmp);
  }

}
