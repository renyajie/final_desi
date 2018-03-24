-- 和订单有关的SQL

use yuyue;

select * from class_order;
select * from card_order;
select * from user;
select * from class_info;
select * from card_info;
select * from card_kind;

-- 目的: 查看约课订单
-- 方法: getClassOrder(Integer placeId, Integer classId, Integer userId,
    		-- Integer cardId, Date before, Date after)
-- 传入参数: u_id 
-- 传出参数: class_order_result

select co.id, p.id as p_id, p.p_name, co.cla_id, cla_k.cla_k_name, co.u_id, u.u_name, co.card_id, card_k.card_k_name, co.ord_time, co.num
from class_order co, user u, class_info cla_i, card_info card_i, class_kind cla_k, card_kind card_k, place p
where 
co.cla_id = cla_i.id and cla_i.cla_k_id = cla_k.id 
and cla_i.p_id = p.id
and co.u_id = u.id 
and co.card_id = card_i.id and card_i.card_k_id = card_k.id
and co.u_id = 1
and p.id = 1
and co.ord_time >= '2018-3-1'
and co.ord_time <= '2018-3-26'
and card_i.id = 1
and cla_i.id = 1
order by co.ord_time desc;

-- 目的: 查看购卡订单
-- 方法: gteCardOrder()
-- 传入参数: u_id, card_k_id, card_id
-- 传出参数: card_order_result
select co.id, co.u_id, u.u_name, co.card_k_id, ck.card_k_name, co.card_id, co.ord_time
from card_order co, user u, card_kind ck
where co.u_id = u.id and co.card_k_id = ck.id
and co.u_id = 1
and co.card_k_id = 1
and co.ord_time >= '2018-3-1'
and co.ord_time <= '2018-3-16'
order by co.ord_time desc;