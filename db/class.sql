-- 和课程有关的SQL
use yuyue;
select * from class_kind;
select * from class_info;
select * from manager;
select * from place;
select * from teacher;

-- 目的：根据课程编号，课程种类，场地，教师，上课时间，课程属性查询课程信息
-- 方法：getClassInfo()
-- 传入参数: id, cla_k_id, p_id, tea_id, c_day
-- 传出参数: class_info_result 集合
select ci.id, ci.cla_k_id, ck.cla_k_name, ci.p_id, p.p_name, ci.tea_id, t.tea_name, 
ci.c_day, ci.sta_time, ci.end_time, ci.length, ci.allowance, ci.order_num, ci.expend, ck.property
from class_info ci, class_kind ck, place p, teacher t
where ci.cla_k_id = ck.id and ci.p_id = p.id and ci.tea_id = t.id
and ci.id = 1
and ci.cla_k_id = 1
and ci.p_id = 1
and ci.tea_id = 1
and ci.c_day >= '2018-3-1'
and ci.c_day <= '2018-3-31'
and ck.property = 's'
order by ci.sta_time asc;



-- 目的：管理员根据课程名，课程属性多条件查询所在场馆的课程种类
-- 方法：getClassKind(Integer managerId, String kName, String property)
-- 传入参数: m_id, k_name, peoperty
-- 传出参数: class_kind_result 集合

select ck.id, ck.p_id, p.p_name, ck.property, ck.cla_k_name, ck.difficulty, ck.intro
from class_kind ck, manager m, place p
where 
ck.p_id = p.id and m.p_id = p.id 
and m.id = 1
and ck.cla_k_name = '基础瑜伽'
and ck.property = 's'
order by ck.id asc;

-- 目的：管理员查看单个课程种类信息
-- 方法: getClassKindById(Integer id)
-- 传入参数: id
-- 传出参数: class_kind_result
select ck.id, ck.p_id, p.p_name, ck.property, ck.cla_k_name, ck.difficulty, ck.intro
from class_kind ck, place p
where ck.p_id = p.id
and ck.id = 1;

-- 目的: 用户查看课程安排
-- 方法: getClassForUser(Integer placeId, Date classDay)
-- 传入参数: p_id cDay
-- 传出参数: class_info_result

select ci.id, ci.cla_k_id, ck.cla_k_name, ci.p_id, p.p_name, ci.tea_id, t.tea_name, 
ci.c_day, ci.sta_time, ci.end_time, ci.length, ci.allowance, ci.order_num, ci.expend
from class_info ci, place p, teacher t, class_kind ck
where ci.cla_k_id = ck.id and ci.p_id = p.id and ci.tea_id = t.id
and ci.p_id = 1
and ci.c_day = '2018-3-13'
order by ci.sta_time asc;