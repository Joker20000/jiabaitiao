package com.bootdo.Utils;

import com.xuxueli.poi.excel.ExcelExportUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ExcelExportUtil4DIY extends ExcelExportUtil{
    private static Logger logger = LoggerFactory.getLogger(ExcelExportUtil.class);
    /**
     * 导出Excel文件到磁盘
     * @param dataList
     * @param outputStream
     */
    public static void exportToFile(String sheetName,ArrayList<HashMap<String, Object>> dataList,String[] titles,String[] fieldNames,OutputStream outputStream){
        // workbook
        //Workbook workbook = exportWorkbook(dataList);
        SXSSFWorkbook workbook = ExcelUtil.exportExcel(sheetName,dataList,titles,fieldNames);
        try {
            // workbook 2 FileOutputStream
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
            // flush
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        } finally {
            try {
                if (outputStream!=null) {
                    outputStream.close();
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                throw new RuntimeException(e);
            }
        }
    }
}
