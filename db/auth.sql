-- 和用户，管理员，系统管理员有关的SQL
use yuyue;

select * from sys_manager;
select * from manager;
select * from user where id > 1000;


select * from place;
select * from class_kind where p_id > 20;
select * from class_info where cla_k_id > 800;
select * from class_order;
select * from score where u_id > 1000;
select * from card_kind;
select * from card_info where u_id > 1000;
select * from card_order where u_id > 1000;
select * from teacher;
select * from user where id > 1000;
select * from class_tag;
select * from user_feature where u_id > 1000;

-- 清空所有数据并重新设置内容
TRUNCATE TABLE class_info;

delete from user where id = 1005;

select count(*) from score;

update place set intro = '暂无介绍' where id between 1 and 20;

select *
from score where u_id = 251 and cla_k_id between 1 and 400;