import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import 'rxjs/add/operator/switchMap';

import { NewsService } from '../../../core/news.service';

import { News } from '../../../poto/news';

@Component({
  selector: 'app-news-detail',
  templateUrl: './news-detail.component.html',
  styleUrls: ['./news-detail.component.css']
})
export class NewsDetailComponent implements OnInit {

  news$: Observable<News>;

  constructor(
    private newsService: NewsService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    this.route.paramMap.switchMap((params: ParamMap) =>
      this.newsService.getOneNews(params.get('id'))).subscribe(
        data => {
          //若服务器成功返回消息
          if (data['code'] === 100) {
            this.news$ = of(data['extend']['info']);
          }
          //若发生错误，提示出错
          else {
            alert("发生错误");
          }
        }
      );
  }

  //返回通知列表
  goBack() {
    this.router.navigate(['../'], { relativeTo: this.route });
  }

  //删除新闻
  deleteNews(id) {
    this.newsService.deleteNews(id).subscribe(
      data => {
        //若服务器成功返回消息
        if (data['code'] === 100) {
          alert("删除成功");
          this.goBack();
        }
        //若发生错误，提示出错
        else {
          alert("发生错误");
        }
      }
    );
  }

}
