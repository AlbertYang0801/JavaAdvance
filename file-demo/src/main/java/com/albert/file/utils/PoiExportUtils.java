package com.albert.file.utils;

import com.google.common.collect.Lists;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.util.List;
import java.util.Map;

/**
 * @author Albert
 * @date 2020/10/13 12:07
 */
public class PoiExportUtils {

    /**
     * 创建数据表格
     *
     * @param sxssfWorkbook 指定Excel文件
     * @param sheet         指定工作表
     * @param titleArr      标题行数据
     * @param dataList      表格内容
     * @return
     */
    public static boolean drawSheetTable(SXSSFWorkbook sxssfWorkbook, SXSSFSheet sheet, List<String> titleArr, List<Map<String, Object>> dataList) {
        //获取表格样式
        List<CellStyle> cellStyleList = tableStyle(sxssfWorkbook);
        try {
            // 初始化表格样式
            // 根据数据创建excel第一行标题行
            SXSSFRow row0 = sheet.createRow(0);
            for (int i = 0; i < titleArr.size(); i++) {
                // 设置标题
                row0.createCell(i).setCellValue(titleArr.get(i));
                // 设置标题行样式
                row0.getCell(i).setCellStyle(cellStyleList.get(0));
            }
            // 填充数据
            for (int i = 0; i < dataList.size(); i++) {
                // 获取每一项的数据
                Map<String, Object> data = dataList.get(i);
                // 设置每一行的字段标题和数据
                SXSSFRow rowi = sheet.createRow(i + 1);
                for (int j = 0; j < data.size(); j++) {
                    // 判断是否是标题字段列
                    if (j == 0) {
                        rowi.createCell(j).setCellValue((String) data.get("value" + (j + 1)));
                        // 设置左边字段样式(和表头样式一致)
                        sheet.getRow(i + 1).getCell(j).setCellStyle(cellStyleList.get(0));
                    } else {
                        //数据必须是数据类型，不能是字符串
                        rowi.createCell(j).setCellValue(Double.parseDouble((String) data.get("value" + (j + 1))));
                        // 设置数据样式
                        sheet.getRow(i + 1).getCell(j).setCellStyle(cellStyleList.get(1));
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private static List<CellStyle> tableStyle(SXSSFWorkbook wb) {
        List<CellStyle> cellStyleList = Lists.newArrayList();
        // 样式准备
        // 表头和左边字段样式
        CellStyle headerStyle = wb.createCellStyle();
        //设置颜色
        headerStyle.setFillForegroundColor(IndexedColors.ROYAL_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        // 下边框
        headerStyle.setBorderBottom(BorderStyle.THIN);
        // 左边框
        headerStyle.setBorderLeft(BorderStyle.THIN);
        // 上边框
        headerStyle.setBorderTop(BorderStyle.THIN);
        // 右边框
        headerStyle.setBorderRight(BorderStyle.THIN);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyleList.add(headerStyle);

        //数据样式
        CellStyle cellStyle = wb.createCellStyle();
        // 上边框
        cellStyle.setBorderTop(BorderStyle.THIN);
        // 下边框
        cellStyle.setBorderBottom(BorderStyle.THIN);
        // 左边框
        cellStyle.setBorderLeft(BorderStyle.THIN);
        // 右边框
        cellStyle.setBorderRight(BorderStyle.THIN);
        // 水平对齐方式
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        // cellStyle.setVerticalAlignment(VerticalAlignment.TOP);//垂直对齐方式
        cellStyleList.add(cellStyle);

        return cellStyleList;
    }


}
