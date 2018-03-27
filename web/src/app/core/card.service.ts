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

  /**
   * 添加会员卡种类
   * @param cardKind 
   */
  addCardKind(cardKind: CardKind) {
    let tmp = new CardKind(null, null);
    tmp = Object.assign(tmp, cardKind);
    const url = this.url + 'addCardKind';
    return this.httpClient.post(url, tmp);
  }

  /**
   * 删除会员卡种类
   * @param cardKId 
   */
  deleteCardKind(cardKId) {
    const url = this.url + 'deleteCardKind';
    const params = new HttpParams()
    .set('cardKId', cardKId);
    return this.httpClient.delete(url, { params });
  }

  /**
   * 更新会员卡种类信息
   * @param cardKind 
   */
  cardKindUpdate(cardKind: CardKind) {
    const url = this.url + 'updateCardKind';
    return this.httpClient.put(url, cardKind);
  }

  /**
   * 获取某种会员卡的详细信息
   * @param id 
   */
  getCardKindInfo(id) {
    const url = this.url + 'getCardKindInfo';
    const params = new HttpParams()
    .set('id', id);
    return this.httpClient.get(url, { params });
  }

  /**
   * 管理员获取会员卡种类
   * @param isPage 0表示不分页，1表示分页
   * @param pn 
   * @param cardKId 
   * @param cardKName 
   * @param capacity 
   * @param expend 
   */
  getCardKind(isPage, pn?, cardKId?, cardKName?, capacity?, expend?) {
    const url = this.url + 'getCardKind';
    let params = new HttpParams()
      .set('isPage', isPage)
      .set('managerId', this.personInfoService.id + '')
      .set('pn', pn ? pn : '')
      .set('cardKId', cardKId ? cardKId : '')
      .set('cardKName', cardKName ? cardKName : '')
      .set('capacity', capacity ? capacity : '')
      .set('expend', expend ? expend : '')
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

  /**
   * 获取会员卡信息
   * @param pn 
   * @param cardKId 
   * @param userId 
   */
  getCardInfo(pn?, cardKId?, userId?) {
    const url = this.url + 'getCardInfo';
    let params = new HttpParams()
      .set('managerId', this.personInfoService.id + '')
      .set('pn', pn ? pn : '')
      .set('cardKId', cardKId ? cardKId : '')
      .set('userId', userId ? userId : '');
    return this.httpClient.get(url, { params });
  }
}
