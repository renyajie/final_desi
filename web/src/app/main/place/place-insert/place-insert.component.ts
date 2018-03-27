import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';

import { Place } from '../../../poto/place';
import { PlaceService } from '../../../core/place.service';

@Component({
  selector: 'app-place-insert',
  templateUrl: './place-insert.component.html',
  styleUrls: ['./place-insert.component.css']
})
export class PlaceInsertComponent implements OnInit {

  place: Place;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private placeService: PlaceService
  ) { 
    this.place = new Place();
  }

  ngOnInit() {
  }

  //添加场馆
  addPlace(place: Place) {
    //检查数据的完备性
    if(place.sName == null || place.sName.length == 0) {
      alert("场馆名称不能为空");
      return;
    }
    if(place.address == null || place.address.length == 0) {
      alert("场馆地址不能为空");
      return;
    }
    if(place.phone == null || place.phone.length == 0) {
      alert("联系电话不能为空");
      return;
    }
    //提交服务器
    this.placeService.addPlace(place).subscribe(
      data => {
        if (data['code'] === 100) {
          alert("信息更新成功");
        }
        //若发生错误，提示出错
        else {
          //提示字段错误信息
          let notices: string[] = ['phone', 'sName', 'address'];
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

  //返回场馆列表
  goBack() {
    this.router.navigate(['main/place']);
  }

}
