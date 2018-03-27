import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { NewsService } from '../../../core/news.service';
import { News } from '../../../poto/news';
import { PersonInfoService } from '../../../core/person-info.service';

@Component({
  selector: 'app-news-insert',
  templateUrl: './news-insert.component.html',
  styleUrls: ['./news-insert.component.css']
})
export class NewsInsertComponent implements OnInit {

  news: News;

  constructor(
    private router: Router,
    private newsService: NewsService,
    private personInfoService: PersonInfoService
  ) { 
    this.news = new News();
    this.news.mId = this.personInfoService.manager.id;
    this.news.mName = this.personInfoService.manager.mName;
    this.news.pId = this.personInfoService.manager.pId;
  }

  ngOnInit() {
  }

  get diagnostic() { return JSON.stringify(this.news); }

  submitData(){
    //检查数据的完备性
    if(this.news.title == null || this.news.title.length == 0) {
      alert("通知标题不能为空");
      return;
    }
    if(this.news.context == null || this.news.context.length == 0) {
      alert("通知内容不能为空");
      return;
    }
    //提交数据
    this.newsService.addOneNews(this.news).subscribe(
      data => {
        if(data['code'] === 100) {
          alert("提交成功");
          this.goToList();
        }
        else{
          //提示服务器返回的错误信息
          let notices: string[] = ['title', 'context', 'pId', 'mId'];
          const errorFields = data['extend']['errorFields'];
          for (let notice of notices) {
            if(errorFields[notice] != null) {
              alert(errorFields[notice]);
            }
          }
        }
      }
    )
  }

  
  goToList(){
    this.router.navigate(['main/news']);
  }

}
