-- 和会员卡相关的SQL

use yuyue;

select * from card_info;
select * from card_kind;
select * from user;

-- 目的: 检查用户是否已经拥有此种类会员卡
-- 方法: checkCardIsExsitOrNot(int userId)
-- 传入参数: u_id
-- 传出参数: card_k_id
select ci.card_k_id
from card_info ci, user u
where ci.u_id = u.id
and u.id = 3;

-- 目的: 查看会员卡
-- 方法: getCardInfo()
-- 传入参数: u_id, card_k_id
-- 传出参数: card_info_result
select ci.id, ci.card_k_id, ck.card_k_name, ci.u_id, u.u_name, ci.allowance
from card_info ci, card_kind ck, user u
where ci.card_k_id = ck.id and ci.u_id = u.id
and ci.card_k_id = 1
and ci.u_id = 1;

-- 目的: 查看会员卡种类
-- 方法: getCardKind()
-- 传入参数: p_id, card_k_name, capacity, expend
-- 传出参数: card_kind_result
select ck.id, ck.p_id, p.p_name, ck.card_k_name, ck.capacity, ck.expend
from card_kind ck, place p
where ck.p_id = p.id
and ck.p_id = 1
and ck.card_k_name like '%黄%'
and ck.capacity > 10
and ck.expend > 10
order by ck.id asc;