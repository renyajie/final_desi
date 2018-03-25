-- 和用户，管理员，系统管理员有关的SQL
use yuyue;

select * from sys_manager;
select * from manager;
select * from user;
select * from user where phone like '%5%';

-- 重置自动增长的值
ALTER TABLE sys_manager AUTO_INCREMENT = 1; 