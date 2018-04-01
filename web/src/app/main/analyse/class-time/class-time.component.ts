import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Observable';

import 'rxjs/add/operator/map';
import { of } from 'rxjs/observable/of';
import { SimpleToken } from '../../../utility/simple_token';
import { AnalyseService } from '../../../core/analyse.service';

@Component({
  selector: 'app-class-time',
  templateUrl: './class-time.component.html',
  styleUrls: ['./class-time.component.css']
})
export class ClassTimeComponent implements OnInit {

  //数据
  data: SimpleToken[];
  //柱状图的平均值和线的名称
  averageNumber: number;

  //是否显示柱状图
  showColumn: boolean;
  //是否显示数据
  show: boolean;

  //标题和子标题
  caption: string
  subCaption: string
  
  constructor(private analyseService: AnalyseService) {
    this.caption = "上课时间统计图";
    this.subCaption = "最近三天";
  }

  ngOnInit() {
    this.showColumn = true;
    this.getData(3);
  }

  //计算柱状图的平均值
  calculateAverage() {
    let totalNumber = 0;
    this.data.map(simpleToken => {
      totalNumber = totalNumber + (+simpleToken.value);
    })
    this.averageNumber = totalNumber / this.data.length;
  }

  //设置数据源
  resetData() {
    //设置标题和数据
    if(this.showColumn) {
      this.calculateAverage();
      this.dataSourceForColumn.trendlines = [{
        line: [
            {
              //线的高度
              startvalue: this.averageNumber + '',
              //颜色
              color: "#1aaf5d",
              //右侧显示
              valueOnRight: "1",
              //显示内容
              displayvalue: "平均值: " + this.averageNumber.toFixed(2)
            }
        ]}]
    }

    this.dataSourceForColumn.chart.caption = this.caption;
    this.dataSourceForColumn.chart.subCaption = this.subCaption;
    this.dataSourceForPie.chart.caption = this.caption;
    this.dataSourceForPie.chart.subcaption = this.subCaption;

    this.dataSourceForPie.data = this.data;
    this.dataSourceForColumn.data = this.data;
    //显示图标
    this.show = true;
  }

  //获取图表数据
  getData(timeLength) {
    this.show = false;

    if(timeLength == 3) {
      this.subCaption = '最近三天';
    }
    else if(timeLength == 7) {
      this.subCaption = '最近一周';
    }
    else {
      this.subCaption = '最近一个月';
    }

    const graphData = [];
    this.analyseService.getByClassTime(timeLength).subscribe(
      data => {
        //若服务器成功返回数据
        if(data['code'] == 100) {
          data['extend']['info'].map(simpleToken => {
            graphData.push(SimpleToken.fromJSON(simpleToken));
          })
          this.data = graphData;
          alert(graphData.length);
          this.resetData();
        }
        //若出错
        else {
          alert("服务器发生错误");
        }
      }
    )
    
  }

  //显示柱状图
  showColumnGraph() {
    this.showColumn = true;
  }

  //显示饼图
  showPieGraph() {
    this.showColumn = false;
  }

  dataSourceForPie = {
    chart: {
      //标题
      caption: this.caption,
      //子标题
      subcaption: this.subCaption,
      //颜色
      paletteColors: "#0075c2,#1aaf5d,#f2c500,#f45b00,#8e0000",
      //几位小数
      decimals: "1",
      //饼图上是否显示标签
      showlabels: '1',
      //标签是否使用和图注相同的颜色
      useDataPlotColorForLabels: "1",
      //是否显示图注
      showlegend: '1',
      //是否允许点击时多个部分分开
      enablemultislicing: '1',
      showpercentvalues: '1',
      //鼠标移动到对应部分显示出来的文字
      plottooltext: '时间段 : $label 预约数 : $datavalue'
    },
    data: []
  }

  dataSourceForColumn = {
    chart: {
      caption: this.caption,
      subCaption: this.subCaption,
      numberSuffix: '次',
      xAxisName: "Month",
      yAxisName: "Revenue",
      //柱子颜色
      paletteColors: "#0075c2",
      bgColor: "#ffffff",
      //最外侧边框的透明度，0表示完全透明，100表示完全不透明
      borderAlpha: "0",
      //x轴和y轴的透明度，值和上面相同
      canvasBorderAlpha: "0",
      //柱子的颜色一致，不用渐变色
      usePlotGradientColor: "0",
      //柱子边框的透明度
      plotBorderAlpha: "10",
      //是否把值放在柱子里面
      placevaluesInside: "1",
      //值得颜色
      valueFontColor: "#ffffff",
      //显示X轴
      showXAxisLine: "1",
      //x轴的颜色
      xAxisLineColor: "#999999",
      //分割线的颜色
      divlineColor: "#999999",
      //分割线是否为虚线
      divLineDashed: "1",
      //是否显示交替背景色
      showAlternateHGridColor: "0"
    },
    data: [],
    //一条额外的线
    trendlines: [
      {
          line: [
              {
                //线的高度
                startvalue: "700",
                //颜色
                color: "#1aaf5d",
                //右侧显示
                valueOnRight: "1",
                //显示内容
                displayvalue: "Monthly Target"
              }
          ]
      }
    ]
  };

}
