# 基于推荐算法的多应用场景预约系统

## 项目概述
基于推荐算法的多应用场景预约系统，以瑜伽馆预约为实际应用场景。
系统组成：
- Android客户端
- 基于Spring+SpringMVC+Mybatis的Java Web服务器
- 基于Angular的后台管理系统组成。

项目结构：
- ./app : Android客户端
- ./backend : Java Web服务器
- ./db : 数据库文件，整合Mybatis时编写SQL脚本
- ./py : 推荐算法文件夹，核心文件是recommand.py，推荐算法的需求数据存储在./py/data中
- ./web : 基于Angular编写的后台管理系统(无UI)
- ./README.md : 介绍文件

## 推荐算法
系统中涉及到推荐算法分为三种：
- 基于用户的协同过滤算法
- 人口特征法
- 标签匹配法

## 具体展示
