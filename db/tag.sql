-- 和课程标签有关的内容
use yuyue;

select * from class_tag;
-- 获取某些课程标签
-- getClassTag(placeId, classKId)
-- 入参: p_id, class_k_id

select * from class_tag where id in (801, 802);
select 
ct.id, ct.relaxed, ct.intense, ct.common, ct.recovery, ct.enhance, ct.nurse, ct.consume, ct.class_k_id, 
ck.difficulty, ck.property, ck.cla_k_name
from class_tag ct, class_kind ck
where ct.class_k_id = ck.id
#and ck.id = 1
#and ck.p_id = 1
limit 10;

-- 获取根据用户偏好获取课程推荐编号
-- 入参: relaxed, intense, common, recovery, enhance, nurse, consume, property
select ck.id
from class_tag ct, class_kind ck
where ct.class_k_id = ck.id
and ct.relaxed = 1
and ct.intense = 0
and ct.common = 0
and ct.recovery = 1
and ct.enhance = 0
and ct.nurse = 0
and ct.consume = 0
and ck.property = 's'
limit 5;