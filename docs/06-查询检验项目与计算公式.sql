-- 查询检验项目与计算公式
select ic.chr_item1,  ic.chr_item2, ic.chr_formula 
from item_calc ic
where ic.num_typeid = 1
and ic.chr_itemcode = 'A/G'

