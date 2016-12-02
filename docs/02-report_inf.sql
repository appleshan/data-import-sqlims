-- ������report_inf

-- ӳ�����ݱ��ֶ�
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

�ֶ�ת��˵����
'1': ID_TYPE �̶�Ϊ 1

r.chr_patiid  -- �����ط��л�����Ϣ��[report_inf��pati_data] ����֪��Ӧ��ȡ�ĸ���

r.chr_bednumb as BED,
r.chr_prinstat, -- ��Ϊ��1[Ϊ�Ѵ�ӡ]��SPECIMEN_SEQ_STATE = 103��PRINTNUM = 1
r.dat_birth, -- ��Ҫ����� AGE��AGE_UNIT��CALCULATE_AGE.
r.chr_state, -- ��Ϊ��1[�������]��SPECIMEN_SEQ_STATE = 102

��û��ȷ�����ֶΣ�
BUS_TEST_REPORT.INSTRUMENT_ID

-- num_samplenum ��ѯ���ظ���������Ϊ id_content ��
-- �� r.dat_inputdate �� r.num_samplenum �Ĺ�ϵ������������Ϊ ��ˮ��[seq]
select r.dat_inputdate, r.num_samplenum 
from report_inf r 
order by r.dat_inputdate, r.num_samplenum;

select r.num_samplenum 
from report_inf r 
group by r.num_samplenum having count(r.dat_inputdate) > 1


-- chr_applyno �ֶ�ȫ���ǿգ�������Ϊ id_content
select r.dat_inputdate, r.chr_applyno from report_inf r where r.chr_applyno <> '';

-- chr_code
select r.dat_inputdate, r.chr_code 
from report_inf r 
where r.chr_code = '2014090918'
order by r.dat_inputdate, r.chr_code;

select r.chr_code 
from report_inf r 
group by r.chr_code having count(r.dat_inputdate) > 1

-- num_typeid ��ʲô���� ������Ŀ���� ���� �������� �� �ȵ��� �������͡�
-- ������Ŀ����                         
select *
from report_inf r, item_type it
where r.chr_code = '2014090918'
and r.num_typeid = it.num_typeid

-- ��������
select *
from report_inf r, pat_type pt
where r.chr_code = '2014090918'
and r.num_typeid = pt.num_typeid

-- num_checkerid ȫ�� == 0
select * from report_inf r
where r.num_checkerid <> 0

