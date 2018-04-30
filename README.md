# 基于推荐算法的多应用场景预约系统
目前所有会的技术(多图预警)

## 项目概述
基于推荐算法的多应用场景预约系统，以瑜伽馆预约为实际应用场景
系统组成：
- Android客户端
- 基于Spring+SpringMVC+Mybatis的Java Web服务器
- 基于Angular的后台管理系统组成
- Nginx完成跨域请求(Nginx内容只在README.md中)

项目结构：
- ./app : Android客户端
- ./backend : Java Web服务器
- ./db : 数据库文件，整合Mybatis时编写SQL脚本
- ./py : 推荐算法文件夹，核心文件是recommand.py，推荐算法的需求数据存储在./py/data中
- ./web : 基于Angular编写的后台管理系统(无UI)
- ./raw : 系统运行截屏
- ./README.md : 介绍文件

## 推荐算法
系统中涉及到推荐算法分为三种，它们各自的适用情况如下：
- 基于用户的协同过滤算法：适用于系统中有大量用户和用户评论，且当前用户有评论数据
- 人口特征法：适用于系统有大量用户，但当前用户没有评论数据
- 标签匹配法：适用于系统刚开始运行，只有少量用户

起初只想用基于用户的协同过滤算法，但后期考虑到冷启动问题，又添加了人口特征法和基于专业知识和静态数据的标签匹配法。其中基于用户的协同过滤算法由Python开发，而人口特征法和标签匹配法整合在服务器中。

### 基于用户的协同过滤算法
核心思想是**寻找与目标用户偏好相似的其他用户作为邻居用户，然后从邻居用户所评分过的项目中计算出推荐项目，最后将推荐项目推荐给目标用户**。./py/data/recommand.py包含了所有的核心代码，每行都包含注释，只要基本的Python基础就应该能看懂。<br>

算法实现的核心步骤：
- 读取用户评论文件，获取当前系统所有用户的评论信息
- 将评论信息格式化成评论数据集
- 采用Pearson相似性系数计算出目标用户与其他用户的相似性系数，降序排序后截取若干项作为邻居用户
- 将所有邻居相似性系数相加得到距离总和，并将每个邻居用户对应的相似性系数除以距离总和得到每个邻居用户的权重，其中权重的取值范围为(0，1]
- 新建一个推荐课程集合。历遍所有邻居用户，查看每个邻居用户评论过的课程。这里分为三种情况：第一种情况，若目标用户没有评论过该课程，且该课程不在推荐集合中，将邻居用户对该课程的评价乘以邻居用户的权重作为分数，将该课程和分数作为一个整体加入推荐集合中；第二种情况，若目标用户没有评论过该课程，但该课程已经在推荐集合中，则将计算出来的分数进行累加；第三种情况，若目标用户已经评论过该课程，则直接跳过

### 人口特征法
思路是**寻找和当前用户具有相似人口特征的其他用户，并统计预约数量最高的课程种类编号**。考虑的人口特征很简单，只有年龄和性别。

### 标签匹配法
新用户的健康体征来匹配课程属性，得到课程种类编号列表。每个用户都有健康体征数据，每个课程也都有标签。通过匹配的方式找到新用户适合的课程。

## Nginx
Nginx在项目中的作用是将基于Angular开发的后台管理系统和Java Web服务器代理到统一端口上，解决跨域请求问题。<br>
项目中没有Nginx的配置部分，这里补充上:


    #use websocket
    map $http_upgrade $connection_upgrade {

      default upgrade;
      '' close;

    }

    server {

        listen  8000;
        #angular frontend configuration
        location / {
            proxy_pass http://127.0.0.1:4200;
            proxy_http_version 1.1;
            proxy_set_header Upgrade $http_upgrade;
            proxy_set_header Connection $connection_upgrade;
        }

        #ssm backend configuration
        location /api {
            proxy_pass http://127.0.0.1:8080/yuyue/api;
        }
    }
  

## 具体展示

### Android移动端
#### 登录
<div>
  <img src="https://github.com/renyajie/final_desi/blob/master/raw/1.png" width="270" height="480"/>
  <img src="https://github.com/renyajie/final_desi/blob/master/raw/2.png" width="270" height="480"/>
</div>

#### 注册和收集健康体征信息
<div>
  <img src="https://github.com/renyajie/final_desi/blob/master/raw/3.png" width="270" height="480"/>
  <img src="https://github.com/renyajie/final_desi/blob/master/raw/4.png" width="270" height="480"/>
</div>

#### 课程推荐
<div>
  <img src="https://github.com/renyajie/final_desi/blob/master/raw/5.png" width="270" height="480"/>
  <img src="https://github.com/renyajie/final_desi/blob/master/raw/6.png" width="270" height="480"/>
  <img src="https://github.com/renyajie/final_desi/blob/master/raw/7.png" width="270" height="480"/>
</div>

#### 课程预约
<div>
  <img src="https://github.com/renyajie/final_desi/blob/master/raw/8.png" width="270" height="480"/>
  <img src="https://github.com/renyajie/final_desi/blob/master/raw/9.png" width="270" height="480"/>
  <img src="https://github.com/renyajie/final_desi/blob/master/raw/10.png" width="270" height="480"/>
</div>
<div>
  <img src="https://github.com/renyajie/final_desi/blob/master/raw/11.png" width="270" height="480"/>
  <img src="https://github.com/renyajie/final_desi/blob/master/raw/12.png" width="270" height="480"/>
  <img src="https://github.com/renyajie/final_desi/blob/master/raw/13.png" width="270" height="480"/>
</div>
<div>
  <img src="https://github.com/renyajie/final_desi/blob/master/raw/14.png" width="270" height="480"/>
  <img src="https://github.com/renyajie/final_desi/blob/master/raw/15.png" width="270" height="480"/>
  <img src="https://github.com/renyajie/final_desi/blob/master/raw/16.png" width="270" height="480"/>
</div>

#### 查看课程
<div>
  <img src="https://github.com/renyajie/final_desi/blob/master/raw/17.png" width="270" height="480"/>
  <img src="https://github.com/renyajie/final_desi/blob/master/raw/18.png" width="270" height="480"/>
</div>

#### 查看教师
<div>
  <img src="https://github.com/renyajie/final_desi/blob/master/raw/19.png" width="270" height="480"/>
</div>

#### 查看通知
<div>
  <img src="https://github.com/renyajie/final_desi/blob/master/raw/20.png" width="270" height="480"/>
  <img src="https://github.com/renyajie/final_desi/blob/master/raw/21.png" width="270" height="480"/>
</div>

#### 约课订单
<div>
  <img src="https://github.com/renyajie/final_desi/blob/master/raw/22.png" width="270" height="480"/>
  <img src="https://github.com/renyajie/final_desi/blob/master/raw/23.png" width="270" height="480"/>
  <img src="https://github.com/renyajie/final_desi/blob/master/raw/24.png" width="270" height="480"/>
</div>

#### 评价订单
<div>
  <img src="https://github.com/renyajie/final_desi/blob/master/raw/25.png" width="270" height="480"/>
  <img src="https://github.com/renyajie/final_desi/blob/master/raw/26.png" width="270" height="480"/>
  <img src="https://github.com/renyajie/final_desi/blob/master/raw/27.png" width="270" height="480"/>
</div>

#### 我的会员卡
<div>
  <img src="https://github.com/renyajie/final_desi/blob/master/raw/28.png" width="270" height="480"/>
  <img src="https://github.com/renyajie/final_desi/blob/master/raw/29.png" width="270" height="480"/>
</div>

#### 购买会员卡
<div>
  <img src="https://github.com/renyajie/final_desi/blob/master/raw/30.png" width="270" height="480"/>
  <img src="https://github.com/renyajie/final_desi/blob/master/raw/31.png" width="270" height="480"/>
</div>

### Angular后台管理系统--系统管理员篇
#### 登录
<img src="https://github.com/renyajie/final_desi/blob/master/raw/32.PNG" width="640" height="360"/>
<img src="https://github.com/renyajie/final_desi/blob/master/raw/33.PNG" width="640" height="360"/>

#### 用户管理
<img src="https://github.com/renyajie/final_desi/blob/master/raw/34.PNG" width="640" height="360"/>
<img src="https://github.com/renyajie/final_desi/blob/master/raw/35.PNG" width="640" height="360"/>

#### 场馆管理
<img src="https://github.com/renyajie/final_desi/blob/master/raw/36.PNG" width="640" height="360"/>
<img src="https://github.com/renyajie/final_desi/blob/master/raw/37.PNG" width="640" height="360"/>
<img src="https://github.com/renyajie/final_desi/blob/master/raw/38.PNG" width="640" height="360"/>

#### 管理员管理
<img src="https://github.com/renyajie/final_desi/blob/master/raw/39.PNG" width="640" height="360"/>
<img src="https://github.com/renyajie/final_desi/blob/master/raw/40.PNG" width="640" height="360"/>

#### 通知管理
<img src="https://github.com/renyajie/final_desi/blob/master/raw/41.PNG" width="640" height="360"/>
<img src="https://github.com/renyajie/final_desi/blob/master/raw/42.PNG" width="640" height="360"/>

#### 个人中心
<img src="https://github.com/renyajie/final_desi/blob/master/raw/43.PNG" width="640" height="360"/>

### Angular后台管理系统--场馆管理员篇
#### 登录注册
<img src="https://github.com/renyajie/final_desi/blob/master/raw/44.PNG" width="640" height="360"/>
<img src="https://github.com/renyajie/final_desi/blob/master/raw/45.PNG" width="640" height="360"/>

#### 订单管理
<img src="https://github.com/renyajie/final_desi/blob/master/raw/46.PNG" width="640" height="360"/>
<img src="https://github.com/renyajie/final_desi/blob/master/raw/47.PNG" width="640" height="360"/>

#### 会员卡管理
<img src="https://github.com/renyajie/final_desi/blob/master/raw/48.PNG" width="640" height="360"/>
<img src="https://github.com/renyajie/final_desi/blob/master/raw/49.PNG" width="640" height="360"/>
<img src="https://github.com/renyajie/final_desi/blob/master/raw/50.PNG" width="640" height="360"/>

#### 课程管理
<img src="https://github.com/renyajie/final_desi/blob/master/raw/51.PNG" width="640" height="360"/>
<img src="https://github.com/renyajie/final_desi/blob/master/raw/52.PNG" width="640" height="360"/>
<img src="https://github.com/renyajie/final_desi/blob/master/raw/53.PNG" width="640" height="360"/>
<img src="https://github.com/renyajie/final_desi/blob/master/raw/54.PNG" width="640" height="360"/>
<img src="https://github.com/renyajie/final_desi/blob/master/raw/55.PNG" width="640" height="360"/>
<img src="https://github.com/renyajie/final_desi/blob/master/raw/56.PNG" width="640" height="360"/>

#### 评论管理
<img src="https://github.com/renyajie/final_desi/blob/master/raw/57.PNG" width="640" height="360"/>

#### 标签管理
<img src="https://github.com/renyajie/final_desi/blob/master/raw/58.PNG" width="640" height="360"/>
<img src="https://github.com/renyajie/final_desi/blob/master/raw/59.PNG" width="640" height="360"/>

#### 通知管理
<img src="https://github.com/renyajie/final_desi/blob/master/raw/60.PNG" width="640" height="360"/>

#### 数据分析
<img src="https://github.com/renyajie/final_desi/blob/master/raw/61.PNG" width="640" height="360"/>
<img src="https://github.com/renyajie/final_desi/blob/master/raw/62.PNG" width="640" height="360"/>

#### 个人中心
<img src="https://github.com/renyajie/final_desi/blob/master/raw/63.PNG" width="640" height="360"/>
