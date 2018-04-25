use yuyue;

select * from score;
select * from class_order;

-- 目的：查看某个用户已经评价过的订单
-- 方法：getUserOrder(userId)
-- 入参：u_id
-- 输出: score, comment, score_time, p_name, class_k_name, ord_time, u_name

select 
s.id, s.u_id, s.cla_id, s.cla_k_id, ck.cla_k_name, s.p_id, p.s_name, 
s.score, s.comment,  s.score_time, s.order_id, co.ord_time
from score s, class_order co, place p, class_kind ck, user u
where s.p_id = p.id and s.cla_k_id = ck.id and s.order_id = co.id and s.u_id = u.id
and u.id = 1
order by co.ord_time desc;

-- 目的：查看某个用户评价某种属性的订单数量accessible
select count(*)
from score s, class_kind ck
where s.cla_k_id = ck.id
and ck.property = 'g'
and s.u_id = 1;


