-- report_inf �� result_inf �Ĺ�����ϵ
Select * from report_inf a where a.chr_code = '2014090918';  
18 95

select * from result_inf b
where b.num_samplenum in (18, 95)
and b.dat_inputdate = #2014/9/9#
