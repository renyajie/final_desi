-- 和教师有关的SQL

use yuyue;

select * from teacher;
select * from place;

-- 目的: 查看教师
-- 方法: getTeacher()
-- 传入参数: p_id, tea_name
-- 传出参数: teacher_result
select t.id, t.p_id, p.p_name, t.tea_name, t.phone, t.intro
from teacher t, place p
where t.p_id = p.id
and t.p_id = 1
and t.tea_name like '%冰%';
