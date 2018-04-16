-- 和用户，管理员，系统管理员有关的SQL
use yuyue;

select * from sys_manager;
select * from manager;
select * from user;


select * from place;
select * from class_kind;
select * from class_info;
select * from class_order;
select * from score where u_id = 1;
select * from card_kind;
select * from card_info;
select * from card_order;
select * from teacher;
select * from user;
select * from class_tag where id > 800;

-- 清空所有数据并重新设置内容
TRUNCATE TABLE class_tag;

select count(*) from class_tag;

update place set intro = '暂无介绍' where id between 1 and 20;

select *
from score where u_id = 251 and cla_k_id between 1 and 400;