use yuyue;

select * from score;

-- 目的: 根据性别和年龄统计热门的课程种类并按照数量降序排序accessible

select s.cla_k_id, count(cla_k_id) as number#, ck.cla_k_name, ck.property 
from score s, class_kind ck
where s.cla_k_id = ck.id 
and age between 24 and 26 
and gender = '男'
and ck.property = 'g'
group by cla_k_id
order by number desc;