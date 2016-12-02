-- 从 report_inf 查询 仪器id [instrmnt_item.num_instid]
-- 仪器id 不需要查询出来，只有一个仪器，是生化类的仪器，可以写成常量
-- report_inf 与 result_inf 是一对多的关系

select 
rp.dat_inputdate as TEST_DATE, 
rp.num_samplenum as SEQ,
rp.chr_code as ID_CONTENT,
rs.chr_itemcode as chr_itemcode,
(select ti.chr_itemname from item_set ti where ti.chr_itemcode = rs.chr_itemcode) as chr_itemname,
(select i.num_instid from tube_item i where i.chr_itemcode = rs.chr_itemcode) as INSTRUMENT_ID,
'aabbcc' as HOSPITAL_ID
from report_inf rp,  result_inf rs
where rp.num_samplenum = rs.num_samplenum
and rp.dat_inputdate = rs.dat_inputdate
and rp.dat_inputdate = #2014/9/9#
and rp.chr_code = '2014090918'
and rp.num_samplenum in (18, 95);

select i.num_instid, i.chr_itemcode from tube_item i

