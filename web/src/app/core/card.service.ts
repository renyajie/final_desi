import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';

import { PersonInfoService } from './person-info.service';

import { CardKind } from '../poto/card_kind';
import { CardOrder } from '../poto/card_order';

/**
 * 处理和会员卡的交互
 */
@Injectable()
export class CardService {

  url = 'api/setting/';

  constructor(
    private httpClient: HttpClient,
    private personInfoService: PersonInfoService
  ) { }

  //管理员获取会员卡种类
  getCardKind() {
    const url = this.url + 'getCardKind';
    let params = new HttpParams()
      .set('managerId', this.personInfoService.id + '');
    return this.httpClient.get(url, { params });
  }

  /**
   * 获取会员卡订单
   * @param pn 
   * @param cardKId 
   * @param userId 
   * @param before 
   * @param after 
   */
  getCardOrder(pn?, cardKId?, userId?, before?, after?) {
    const url = 'api/order/getCardOrder'
    let params: HttpParams;
    if (before != null && after != null) {
      params = new HttpParams()
        .set('pn', pn ? pn : '')
        .set('cardKId', cardKId ? cardKId : '')
        .set('managerId', this.personInfoService.id + '')
        .set('userId', userId ? userId : '')
        .set('before', before)
        .set('after', after);
    }
    if (before === null && after === null) {
      params = new HttpParams()
        .set('pn', pn ? pn : '')
        .set('cardKId', cardKId ? cardKId : '')
        .set('managerId', this.personInfoService.id + '')
        .set('userId', userId ? userId : '')
    }
    if (before === null && after != null) {
      params = new HttpParams()
        .set('pn', pn ? pn : '')
        .set('cardKId', cardKId ? cardKId : '')
        .set('managerId', this.personInfoService.id + '')
        .set('userId', userId ? userId : '')
        .set('after', after);
    }
    if (before != null && after === null) {
      params = new HttpParams()
        .set('pn', pn ? pn : '')
        .set('cardKId', cardKId ? cardKId : '')
        .set('managerId', this.personInfoService.id + '')
        .set('userId', userId ? userId : '')
        .set('before', before);
    }
    return this.httpClient.get(url, { params });
  }
}
