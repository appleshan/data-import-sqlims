package com.daanhealth.datax.helper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.daanhealth.datax.entity.TestItemMap;

public class TestItemMapper {
	private static String filePath = PropertyHelper.readSqlimsFilePath();
	
	private static List<TestItemMap> testItemMapList = null;

    static {
    	testItemMapList = TestItemMapper.parseExcel();
    }
    
    public static List<TestItemMap> parseExcel() {
    	// 数据容器
        List<TestItemMap> testItemMapList = new ArrayList<TestItemMap>();
        
		// 解析 Excel 文档
		HSSFWorkbook workbook = null;
		try {
			InputStream in = new FileInputStream(filePath);
			workbook = new HSSFWorkbook(in);// 创建对Excel工作簿文件的引用
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		HSSFSheet sheet = workbook.getSheetAt(0); // 创建对工作表的引用
        int rows = sheet.getPhysicalNumberOfRows();//获取表格的
//        System.out.println("rows: " + rows);
        if (rows == 0) {
        	// 若 Excel 中没有 sheet，说明：这份 Excel 没有数据
        	return testItemMapList;
        }
        
    	/* excel 表格：
        从第三行开始，是数据行。
        */
        for (int rowIndex = 2; rowIndex < rows; rowIndex++) { //循环遍历表格的行
        	TestItemMap testMap = new TestItemMap();
        	
        	HSSFRow row = sheet.getRow(rowIndex); //获取单元格中指定的行对象
            int cellLength = row.getLastCellNum();
//            System.out.println("cellLength: " + cellLength);
            
            if( cellLength < 9 ) {
            	continue;
            }
            
            for (int cellIndex = 0; cellIndex < cellLength; cellIndex++) { //循环遍历单元格中的列
            	HSSFCell cell = row.getCell(cellIndex); //获取指定单元格中的列
            	if (cell != null) {
            		String value = cell.getStringCellValue();
//            		System.out.println("index: " + cellIndex + " | value: " + value);
            		
            		// 需要的数据是：HisTestCode、NclTestId、NclTestName
            		switch (cellIndex) {
					case 0:
						testMap.setHisTestCode(value);
						break;
//					case 1:
//						testMap.setHisTestName(value);
//						break;
					case 2:
						testMap.setNclTestId(value);
						break;
//					case 3:
//						testMap.setNclTestCode(value);
//						break;
//					case 4:
//						testMap.setNclFastCode(value);
//						break;
//					case 5:
//						testMap.setNclEnglishName(value);
//						break;
					case 6:
						testMap.setNclTestName(value);
						break;
					default:
						break;
					}
            	}
            }
            
            testItemMapList.add(testMap);
        }
        
        return testItemMapList;
    }
    
    public static TestItemMap findNclTestItem(String hisTestCode) {
    	for (TestItemMap testItemMap : testItemMapList) {
    		String hisTestCodeInMap = testItemMap.getHisTestCode();
			if(StringUtils.equals(hisTestCode, hisTestCodeInMap)) {
				return testItemMap;
			}
		}
    	return null;
    }

}
