-- 和用户，管理员，系统管理员有关的SQL
use yuyue;

select * from sys_manager;
select * from manager;
select * from user;


select * from place;
select * from class_kind;
select * from class_info;
select * from class_order;
select * from card_kind;
select * from teacher;
select * from user where phone like '%5%';

-- 清空所有数据并重新设置内容
TRUNCATE TABLE class_order;

select count(*) from class_order;