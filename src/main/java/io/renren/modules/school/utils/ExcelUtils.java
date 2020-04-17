package io.renren.modules.school.utils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelUtils {
    /**
     * 解析Excel文件
     *
     * @param in
     * @param fileName
     */
    public static List<List<Object>> parseExcel(InputStream in, String fileName) throws Exception {
        List list = null;
        Workbook work = null;
        list = new ArrayList<>();
        //创建Excel工作薄
        work = getWorkbook(in, fileName);
        if (null == work) {
            throw new Exception("创建Excel工作薄为空！");
        }
        Sheet sheet = null;
        org.apache.poi.ss.usermodel.Row row = null;
        org.apache.poi.ss.usermodel.Cell cell = null;

        for (int i = 0; i < work.getNumberOfSheets(); i++) {
            sheet = work.getSheetAt(i);
            if (sheet == null) {
                continue;
            }

            //去掉第一行
            for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
                row = sheet.getRow(j);
                if (row == null || row.getFirstCellNum() == j) {
                    continue;
                }

                List<Object> li = new ArrayList<>();
                for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
                    cell = row.getCell(y);
                    //数字类型转换规则
                    if(cell.getCellType() != Cell.CELL_TYPE_STRING){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                    }
                    
                    li.add(cell);
                }
                
                list.add(li);
            }
        }
        return list;

    }

    /**
     * 判断文件格式
     * @param in
     * @param fileName
     * @return
     */
    private static Workbook getWorkbook(InputStream in, String fileName) throws Exception {

        Workbook book = null;
        String filetype = fileName.substring(fileName.lastIndexOf("."));

        if(".xls".equals(filetype)) {
            book = new HSSFWorkbook(in);
        } else if (".xlsx".equals(filetype)) {
            book = new XSSFWorkbook(in);
        } else {
            throw new Exception("请上传excel文件！");
        }

        return book;
    }


}
