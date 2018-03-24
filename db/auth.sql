-- 和用户，管理员，系统管理员有关的SQL

select * from sys_manager;
select * from manager;

-- 重置自动增长的值
ALTER TABLE sys_manager AUTO_INCREMENT = 1; 