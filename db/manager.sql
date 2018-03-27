use yuyue;

select * from manager;
select * from place;

-- 目的: 查看管理员具体信息
-- 方法: getManagerData(Integer id, String account, String mName, String sName)
-- 传入参数: id, account, mName, sName
-- 传出参数: manager_result

select m.id, m.phone, m.account, m.passwd, m.gender, m.m_name, m.p_id, p.s_name
from manager m, place p
where m.p_id = p.id
and m.id = 1
and m.account like '%1%'
and m.m_name like '%冰%'
and p.s_name like '%中%'
order by m.id asc;
