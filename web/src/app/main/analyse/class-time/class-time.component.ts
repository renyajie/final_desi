import { Component, OnInit } from '@angular/core';

import { SimpleToken } from '../../../utility/simple_token';

@Component({
  selector: 'app-class-time',
  templateUrl: './class-time.component.html',
  styleUrls: ['./class-time.component.css']
})
export class ClassTimeComponent implements OnInit {

  //是否显示柱状图
  showColumn: boolean;
  //标题和子标题
  caption: string
  subCaption: string
  //数据
  data: SimpleToken[];

  constructor() {
    this.showColumn = true;
    this.caption = "上课时间统计图";
    this.subCaption = "最近三天";
    this.data = [
      new SimpleToken('A', 800),
      new SimpleToken('B', 800),
      new SimpleToken('C', 800)
    ]
  }

  ngOnInit() {
    this.resetCaption();
    this.dataSourceForColumn.data = this.data;
  }

  resetCaption() {
    //设置标题
    this.dataSourceForPie.chart.caption = this.caption;
    this.dataSourceForPie.chart.subcaption = this.subCaption;
    this.dataSourceForColumn.chart.caption = this.caption;
    this.dataSourceForColumn.chart.subCaption = this.subCaption;
  }

  resetData() {
    this.dataSourceForPie.data = this.data;
    this.dataSourceForColumn.data = this.data;
  }

  //最近三天
  threeDay() {
    this.subCaption = '最近三天';
    this.data = [
      new SimpleToken('A', 800),
      new SimpleToken('B', 800),
      new SimpleToken('C', 800)
    ]
    this.resetCaption();
    this.resetData();
  }

  //最近一周
  oneWeek() {
    this.subCaption = '最近一周';
    this.data = [
      new SimpleToken('A', 800),
      new SimpleToken('B', 800),
      new SimpleToken('C', 800),
      new SimpleToken('D', 800)
    ]
    this.resetCaption();
    this.resetData();
  }

  //最近一个月
  oneMonth() {
    this.subCaption = '最近一月';
    this.data = [
      new SimpleToken('A', 800),
      new SimpleToken('B', 800),
      new SimpleToken('C', 800),
      new SimpleToken('D', 800),
      new SimpleToken('E', 800),
      new SimpleToken('F', 800),
      new SimpleToken('G', 800),
    ]
    this.resetCaption();
    this.resetData();
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
      plottooltext: 'Age group : $label Total visit : $datavalue'
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
