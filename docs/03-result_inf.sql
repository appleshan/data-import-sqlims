-- ������result_inf

-- ӳ�����ݱ��ֶ�
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

�ֶ�ת��˵����
'1' as ID,
'1' as REPORT_ID,

chr_itemcode ͨ�� ��Ŀ���� ���ҳ���
TEST_ID
TEST_NAME

���� num_dataformat ��ȡֵ��
�� num_value, chr_value ѡȡһ����Ϊ RESULT ��

REF_LOW , REF_HIGH, REF_RANGE ��ȡֵ����������
item_set.num_refelow as REF_LOW
item_set.num_refehigh as REF_HIGH
[REF_LOW - REF_HIGH] as REF_RANGE

HIT_TYPE
UNIT ��ȡֵ����������
item_set.chr_unit as UNIT

-- ��ѯ num_typeid ��ȡֵ��Χ
select distinct r.num_typeid from result_inf r 
1 - ����     : 3826767
3 - ø��     : 30
7 - ��ѧ���� : 1
