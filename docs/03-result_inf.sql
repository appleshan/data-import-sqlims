-- 分析：result_inf

-- 映射数据表字段
select 
r.num_samplenum as SEQ,
r.num_typeid,
r.chr_itemcode,
r.num_dataformat,
iss.num_itemid as DISPLAY_ORDER,
r.num_value as RESULT_1,
r.chr_value as RESULT_2,

r.num_horl as TIP,

r.dat_testtime as CREATE_DATE,
r.dat_testtime as LAST_DATE,

'MS Access Data Import' as CREATE_USER_NAME,
'aabbcc' as HOSPITAL_ID
from result_inf r,  item_set iss
where r.chr_itemcode = iss.chr_itemcode
and r.dat_inputdate = #2014/9/9#
and r.num_samplenum in (18, 95)
order by iss.num_itemid asc;

字段转换说明：
'1' as ID,
'1' as REPORT_ID,

chr_itemcode 通过 项目对照 ，找出：
TEST_ID
TEST_NAME

根据 num_dataformat 的取值，
从 num_value, chr_value 选取一个作为 RESULT ；

REF_LOW , REF_HIGH, REF_RANGE 的取值从哪里来？
item_set.num_refelow as REF_LOW
item_set.num_refehigh as REF_HIGH
[REF_LOW - REF_HIGH] as REF_RANGE

HIT_TYPE
UNIT 的取值从哪里来？
item_set.chr_unit as UNIT

-- 查询 num_typeid 的取值范围
select distinct r.num_typeid from result_inf r 
1 - 生化     : 3826767
3 - 酶标     : 30
7 - 化学发光 : 1
