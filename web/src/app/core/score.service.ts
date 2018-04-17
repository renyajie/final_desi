import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';

import { Score } from '../poto/score';
import { PersonInfoService } from './person-info.service';

@Injectable()
export class ScoreService {

  url = 'api/recommand/';

  constructor(
    private httpClient: HttpClient,
    private personInfoService: PersonInfoService
  ) { }

  /**
   * 获取课程评分
   * @param pn 第几页
   * @param classKId 课程种类编号
   */
  getScore(pn?, classKId?) {
    const url = this.url + 'getScore';
    let params = new HttpParams()
      .set('isPage', 1 + '')
      .set('pn', pn ? pn : '')
      .set('classKId', classKId ? classKId : '')
      .set('placeId', this.personInfoService.manager.pId + '');
    return this.httpClient.get(url, { params });
  }

}
