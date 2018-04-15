import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import 'rxjs/add/operator/switchMap';

import { FileUploader, FileUploaderOptions } from 'ng2-file-upload';

import { PlaceService } from '../../../../core/place.service';
import { PersonInfoService } from '../../../../core/person-info.service';
import { Place } from '../../../../poto/place';

@Component({
  selector: 'app-place-edit',
  templateUrl: './place-edit.component.html',
  styleUrls: ['./place-edit.component.css']
})
export class PlaceEditComponent implements OnInit {
  place$: Observable<Place>;

  // 初始化定义uploader变量,用来配置input中的uploader属性
  public uploader: FileUploader = new FileUploader({
    url: "api/setting/updatePlace",
    method: "POST",
    itemAlias: "image"
  });

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private placeService: PlaceService,
    private personInfoService: PersonInfoService
  ) { }

  ngOnInit() {
    this.placeService.getOnePlace(this.personInfoService.manager.pId).subscribe(
      data => {
        //若服务器成功返回消息
        if (data['code'] === 100) {
          this.place$ = of(data['extend']['info']);
        }
        //若发生错误，提示出错
        else {
          alert("发生错误");
        }
      }
    )
  }

  //提交更新信息
  submitData(place: Place) {
    //检查数据的完备性
    if(place.sName == null || place.sName.length == 0) {
      alert("场馆名称不能为空");
      return;
    }
    if(place.phone == null || place.phone.length == 0) {
      alert("联系方式不能为空");
      return;
    }
    if(place.address == null || place.address.length == 0) {
      alert("地址不能为空");
      return;
    }
    this.uploader.setOptions({
      url: "api/setting/updatePlace",
      method: "POST",
      itemAlias: "image",
      additionalParameter: {
        //这个不是httpClient方法，只能把字段名分开提交，而且服务器不能用requestBody接收
        'id': place.id + '',
        'phone': place.phone,
        'sName': place.sName,
        'address': place.address,
        'intro': place.intro
      }
    });
    //提交数据
    this.uploader.uploadAll();
    
  }

  /**
   * 返回查看场馆信息
   */
  goToBack() {
    this.router.navigate(['main/mine/place/info']);
  }

}
