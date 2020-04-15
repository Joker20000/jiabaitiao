package com.bootdo.Utils;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.*;

import java.util.List;

/*
* 导出公共类
* */
public class ExcelUtilX {


    /*
    * 导出方法  //表头数组，数据字符串集合
    * */
    public static Workbook Derived(String[] str, List<?> list){
        if(str==null||list==null){
            return null;
        }
        //通过表头确定列数
        int length = str.length;
        //通过查询数据集合确定行数
        int size = list.size();
        // 创建工作簿
        XSSFWorkbook xssfSheets = new XSSFWorkbook();
        // 创建工作表
        XSSFSheet sheet = xssfSheets.createSheet();
        //文本居中类型
        //设置表头 第一行
        Row row = sheet.createRow(0);
        for (int i = 0; i <str.length ; i++) {
            //向行中插入数据并设计表格宽度和居中
            XSSFCellStyle cellStyle = xssfSheets.createCellStyle();
            cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            Cell cell = row.createCell(i);
            cell.setCellValue(str[i]);
            cell.setCellStyle(cellStyle);
            sheet.setColumnWidth(i,str[i].toString().length()*512);
        }

        for (int i = 1; i <=size ; i++) {
            //创建行对象
            Row row1 = sheet.createRow(i);
            //在当前行的每列中插入元素，获取元素集合
            List<String> o = (List<String>) list.get(i - 1);
            //获取属性值的集合
            for (int j = 0; j <length ; j++) {
                //向当前行的每一列中插入数据
                row1.createCell(j).setCellValue(o.get(j));
            }
        }

        //中文自动适应
        setSizeColumn(sheet,length);
        return xssfSheets;
    }

    /*
    * 自动使用宽度
    *
    * */
    private static void setSizeColumn(XSSFSheet sheet, int size) {
        for (int columnNum = 0; columnNum < size; columnNum++) {
            int columnWidth = sheet.getColumnWidth(columnNum) / 256;
            for (int rowNum = 0; rowNum <=sheet.getLastRowNum(); rowNum++) {
                XSSFRow currentRow;
                //当前行未被使用过
                if (sheet.getRow(rowNum) == null) {
                    currentRow = sheet.createRow(rowNum);
                } else {
                    currentRow = sheet.getRow(rowNum);
                }

                if (currentRow.getCell(columnNum) != null) {
                    XSSFCell currentCell = currentRow.getCell(columnNum);
                    if (currentCell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
                        int length = currentCell.getStringCellValue().getBytes().length;
                        if (columnWidth < length) {
                            columnWidth = length;
                        }
                    }
                }
            }
            sheet.setColumnWidth(columnNum, columnWidth * 256);
        }
    }


    private void setSizeColumnX(){



    }
    }