-- 和课程有关的SQL

select * from class_kind;
select * from class_info;

-- 目的：管理员根据课程名，课程属性多条件查询所在场馆的课程信息
-- 方法：getClassKind(Integer managerId, String kName, String property)
-- 传入参数 m_id, k_name, peoperty
-- 传出参数 class_kind 集合

select ck.id, ck.p_id, p.p_name, ck.property, ck.cla_k_name, ck.difficulty, ck.intro
from class_kind ck, manager m, place p
where 
ck.p_id = p.id and m.p_id = p.id 
and m.id = 1
and ck.cla_k_name = '233'
and ck.property = 's';