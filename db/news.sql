-- 有关新闻的SQL

use yuyue;

select * from manager;
select * from place;
select * from news;

-- 目的: 查看新闻，不包含具体内容
-- 方法: getNews()
-- 传入参数: m_id, p_id, title, pub_time
-- 传出参数: news_result
select n.id, n.m_id, m.m_name, n.p_id, p.p_name, n.title, n.context, n.brow_time, n.pub_time
from manager m, place p, news n
where n.m_id = m.id and n.p_id = p.id
and n.m_id = 1
and n.p_id = 1
and n.title like '%放%'
and n.pub_time >= '2018-3-1'
order by n.pub_time desc;