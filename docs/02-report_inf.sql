-- 分析：report_inf

-- 映射数据表字段
select 
r.dat_inputdate as TEST_DATE, 
r.num_samplenum as SEQ,
r.num_typeid,
r.num_departid as DEPARTAMENT,

'1' as ID_TYPE,
r.chr_code as ID_CONTENT,
r.num_sampleid as SPECIMEN_TYPE_ID,

r.dat_getdate as COLLECT_DATE,
r.dat_testdate as RECEIVE_DATE,
r.num_sdoctorid as DOCTOR,
r.num_tdoctorid as TEST_USER,
r.num_sickid as CLINICAL_REMARK,
r.chr_memo as REPORT_COMMET,
r.chr_prinstat,

r.chr_name as PATIENT_NAME,
r.chr_sexname as SEX,
date_format(r.dat_birth, '%Y-%m-%d') as BIRTHDAY,

r.dat_newdate as CREATE_DATE,
r.dat_printdate as LAST_DATE,

r.chr_state, 
r.num_checkerid as CHECK_USER,

'MS Access Data Import' as CREATE_USER_NAME,
'aabbcc' as HOSPITAL_ID
from report_inf r 
where r.dat_inputdate = #2014/9/9#
and r.chr_code = '2014090918';

字段转换说明：
'1': ID_TYPE 固定为 1

r.chr_patiid  -- 两个地方有患者信息：[report_inf、pati_data] ，不知道应该取哪个？

r.chr_bednumb as BED,
r.chr_prinstat, -- 当为：1[为已打印]，SPECIMEN_SEQ_STATE = 103，PRINTNUM = 1
r.dat_birth, -- 需要计算出 AGE、AGE_UNIT、CALCULATE_AGE.
r.chr_state, -- 当为：1[经过检查]，SPECIMEN_SEQ_STATE = 102

还没有确定的字段：
BUS_TEST_REPORT.INSTRUMENT_ID

-- num_samplenum 查询有重复，不能作为 id_content ，
-- 从 r.dat_inputdate 与 r.num_samplenum 的关系来看，可以作为 流水号[seq]
select r.dat_inputdate, r.num_samplenum 
from report_inf r 
order by r.dat_inputdate, r.num_samplenum;

select r.num_samplenum 
from report_inf r 
group by r.num_samplenum having count(r.dat_inputdate) > 1


-- chr_applyno 字段全部是空，不能作为 id_content
select r.dat_inputdate, r.chr_applyno from report_inf r where r.chr_applyno <> '';

-- chr_code
select r.dat_inputdate, r.chr_code 
from report_inf r 
where r.chr_code = '2014090918'
order by r.dat_inputdate, r.chr_code;

select r.chr_code 
from report_inf r 
group by r.chr_code having count(r.dat_inputdate) > 1

-- num_typeid 是什么？是 检验项目类型 还是 患者类型 ？ 先当作 患者类型。
-- 检验项目类型                         
select *
from report_inf r, item_type it
where r.chr_code = '2014090918'
and r.num_typeid = it.num_typeid

-- 患者类型
select *
from report_inf r, pat_type pt
where r.chr_code = '2014090918'
and r.num_typeid = pt.num_typeid

-- num_checkerid 全部 == 0
select * from report_inf r
where r.num_checkerid <> 0

