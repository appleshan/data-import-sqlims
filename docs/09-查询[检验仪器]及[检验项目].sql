-- 查询[检验仪器]及[检验项目]
select * from sys_hospital h where h.hospital_name like '%寮步%';
-- 东莞市寮步镇社区卫生服务中心  8a8a84ba5689947f01568cb4dba50887

-- instrument
select i.id, i.instrument_code, i.instrument_name
from par_instrument i 
where i.hospital_id = '8a8a84ba5689947f01568cb4dba50887'
# 8a8a84ba576bfdfa01576ee5e8ae5007  4055  生化仪AU680
# 8a8a84ba5689947f01568cbed9be0f0d  10010 生化仪-AU400

-- test
select *  
from par_test t 
where t.hospital_id = '8a8a84ba5689947f01568cb4dba50887'
and t.test_code = 'HXFG039'
-- 8a8a84ba5689947f01568cb60bf70d03

-- instrument_test
select it.instrument_id, it.test_id
from par_instrument_test it
where it.hospital_id = '8a8a84ba5689947f01568cb4dba50887'
and it.instrument_id = '8a8a84ba576bfdfa01576ee5e8ae5007'
and it.test_id = '8a8a84ba5689947f01568cb60bf70d03'

-- instrument & test
select i.instrument_name, 
it.test_id, t.test_code, t.fast_code, t.english_short_name, t.test_name, t.print_name
from par_instrument_test it, par_test t, par_instrument i
where it.hospital_id = '8a8a84ba5689947f01568cb4dba50887'
# and it.instrument_id = '8a8a84ba5689947f01568cbed9be0f0d'
and it.test_id = t.id
and i.id = it.instrument_id
order by t.english_short_name
