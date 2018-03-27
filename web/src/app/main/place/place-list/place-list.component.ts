import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Observable';

import 'rxjs/add/operator/map';
import { of } from 'rxjs/observable/of';

import { PlaceService } from '../../../core/place.service';

import { Place } from '../../../poto/place';

@Component({
  selector: 'app-place-list',
  templateUrl: './place-list.component.html',
  styleUrls: ['./place-list.component.css']
})
export class PlaceListComponent implements OnInit {

  places$: Observable<Place[]>;
  pageInfo$: Observable<any>;

  id: string;
  sName: string;
  address: string;
  phone: string;

  constructor(
    private placeService: PlaceService
  ) { }

  ngOnInit() {
    this.getAllPlace(1)
  }

  /**
   * 清空选择输入框样式，重置字段
   */
  clear() {
    this.id = '';
    this.sName = '';
    this.address = '';
    this.phone = '';
  }

  getAllPlace(pn?, id?, sName?, address?, phone?) {
    const places = [];
    this.placeService.getAllPlace(1, id, pn, sName, address, phone).subscribe(
      data => {
        //若成功返回数据，为元素赋值
        if (data['code'] === 100) {
          data['extend']['pageInfo']['list'].map(place => {
            places.push(Place.fromJSON(place));
          });
          this.places$ = of(places);
          this.pageInfo$ = of(data['extend']['pageInfo']);
        }
        //若发生错误
        else {
          alert("服务器响应错误")
        }
      }
    )
  }

  deletePlace(id) {
    this.placeService.deletePlace(id).subscribe(
      data => {
        if(data['code'] == 100) {
          alert('删除成功');
          this.getAllPlace();
        }
        else {
          alert('发生错误')
        }
      }
    )
  }

}
