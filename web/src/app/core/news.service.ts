import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';

import { News } from '../poto/news';
import { PersonInfoService } from './person-info.service';
/**
 * 进行和通知有关的交互
 */
@Injectable()
export class NewsService {

  url = 'api/news/';

  constructor(
    private httpClient: HttpClient,
    private personInfoService: PersonInfoService
  ) { }

  /**
   * 删除新闻
   * @param id 
   */
  deleteNews(id) {
    const url = this.url + 'deleteNews';
    const params = new HttpParams()
    .set('id', id);
    return this.httpClient.delete(url, { params });
  }

  /**
   * 添加一个新闻
   * @param news 
   */
  addOneNews(news: News) {
    let tmp: News = new News();
    tmp = Object.assign(tmp, news);
    const url = this.url + 'addOneNews';
    return this.httpClient.post(url, tmp);
  }

  /**
   * 获取一个通知
   * @param id 
   */
  getOneNews(id: number | string) {
    const params = new HttpParams()
      .set('newsId', id + '');
    const url = this.url + 'getOneNews';
    return this.httpClient.get(url, { params });
  }

  /**
   * 管理员查询通知
   * @param title 新闻标题
   * @param before 大于等于此日期
   * @param after 小于等于此日期
   */
  getNews(pn?: string, title?: string, before?: string, after?: string) {
    let params: HttpParams;
    if (before != null && after != null) {
      params = new HttpParams()
        .set('pn', pn ? pn : '')
        .set('title', title ? title : '')
        .set('managerId', this.personInfoService.id + '')
        .set('before', before)
        .set('after', after);
    }
    if (before === null && after === null) {
      params = new HttpParams()
        .set('pn', pn ? pn : '')
        .set('managerId', this.personInfoService.id + '')
        .set('title', title ? title : '');
    }
      if (before === null && after != null) {
        params = new HttpParams()
          .set('pn', pn ? pn : '')
          .set('title', title ? title : '')
          .set('managerId', this.personInfoService.id + '')
          .set('after', after);
      }
      if (before != null && after === null) {
        params = new HttpParams()
          .set('pn', pn ? pn : '')
          .set('title', title ? title : '')
          .set('managerId', this.personInfoService.id + '')
          .set('before', before);
      }
      const url = this.url + 'getNews';
      return this.httpClient.get(url, { params });
    }

}
