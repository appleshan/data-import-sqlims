-- ��ѯ������Ŀ����㹫ʽ
select ic.chr_item1,  ic.chr_item2, ic.chr_formula 
from item_calc ic
where ic.num_typeid = 1
and ic.chr_itemcode = 'A/G'

