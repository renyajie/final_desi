create database yuyue;

use yuyue;

show tables;

/* 创建用户表 */
create table user (
	id int not null primary key,
    phone varchar(30) not null,
    passwd varchar(30) not null,
    u_name varchar(30) not null,
    gender varchar(6) not null
);

describe user;

/* 瑜伽馆管理员表 */
create table manager (
	id int not null primary key,
    phone varchar(30) not null,
    account varchar(20) not null,
    passwd varchar(30) not null,
    m_name varchar(30) not null,
    gender varchar(6) not null,
    p_id int not null
);

describe manager;

/* 系统管理员表 */
create table sys_manager (
	id int not null primary key,
    phone varchar(30) not null,
    account varchar(20) not null,
    passwd varchar(30) not null,
    s_name varchar(30) not null,
    gender varchar(6) not null
);

describe sys_manager;

/* 瑜伽馆表 */
create table place (
	id int not null primary key,
    phone varchar(30) not null,
    s_name varchar(30) not null,
    address varchar(60) not null
);

describe place;

/* 课程种类表 */
create table class_kind (
	id int not null primary key,
    p_id int not null,
    property varchar(6) not null,
    cla_k_name varchar(30),
    difficulty int,
    intro varchar(100) not null
);

describe class_kind;

/* 教师表 */
create table teacher (
	id int not null primary key,
    p_id int not null,
    tea_name varchar(30) not null,
    phone varchar(30) not null,
    intro varchar(100) not null
);

describe teacher;

/* 课程信息表 */
create table class_info (
	id int not null primary key,
    cla_k_id int not null,
    p_id int not null,
    tea_id int not null,
    c_day date not null,
    sta_time timestamp not null,
    end_time timestamp not null,
    length int,
    allowance int,
    order_num int,
    expend int
);

describe class_info;

/* 约课订单表 */
create table class_order (
	id int not null primary key,
    cla_id int not null,
    u_id int not null,
    card_id int not null,
    ord_time timestamp not null
);

describe class_order;

/* 会员卡种类表 */
create table card_kind (
	id int not null primary key,
    p_id int not null,
    card_k_name varchar(30) not null,
    capacity int not null,
    expend int not null
);

describe card_kind;

/* 会员卡信息表 */
create table card_info (
	id int not null primary key,
    card_k_id int not null,
    u_id int not null,
    allowance int not null
);

describe card_info;

/* 购卡订单表 */
create table card_order (
	id int not null primary key,
    u_id int not null,
    card_k_id int not null,
    ord_time timestamp not null
);

describe card_order;

/* 通知表 */
create table news (
	id int not null primary key,
    m_id int not null,
    p_id int not null,
    title varchar(60) not null,
    location varchar(60) not null,
    url varchar(60) not null,
    pub_time timestamp not null
);

describe news;