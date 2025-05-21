package com.albert.file.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

import static java.sql.Types.BOOLEAN;
import static java.sql.Types.NUMERIC;
import static org.apache.poi.ss.usermodel.DataValidationConstraint.ValidationType.FORMULA;
import static org.apache.xmlbeans.impl.piccolo.xml.Piccolo.STRING;

/**
 * @author yangjunwei
 * @date 2025/5/9 17:44
 */
public class ExcelToUpdateSQL {

    public static void main(String[] args) {
        String excelFilePath = "D:\\IdeaWorkSpace\\JavaAdvance\\moudle-javase\\file-demo\\file\\余额数据222.xlsx"; // 替换为你自己的路径
        String tableName = "coin_assets_log";         // 替换为实际表名
        String idColumnName = "coin_assets_log_id";   // 替换为实际ID字段名

        try {
            generateUpdateStatements(excelFilePath, tableName, idColumnName);
        } catch (IOException e) {
            System.err.println("读取Excel文件时出错: " + e.getMessage());
        }
    }

    public static void generateUpdateStatements(String filePath, String tableName, String idColumnName) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0); // 获取第一个工作表

        int rowCount = sheet.getLastRowNum();
        System.out.println("-- 共计 " + rowCount + " 条记录（忽略标题行）--");

        for (int i = 1; i <= rowCount; i++) { // 从第2行开始（i=1）
            Row row = sheet.getRow(i);

            if (row == null) continue;
            Cell idCell = row.getCell(0);     // 第1列：ID
            Cell remarkCell = row.getCell(1); // 第7列：remark

            if (idCell == null || remarkCell == null) continue;

            String remarkValue = remarkCell.getStringCellValue();

            double numericValue = idCell.getNumericCellValue();
            String idValue = String.valueOf((long) numericValue);

            // 处理 remark 中的单引号（防止SQL注入或语法错误）
            remarkValue = remarkValue.replace("'", "''");

            String sql = String.format("UPDATE %s SET remark = '%s' WHERE %s = %s;",
                    tableName, remarkValue, idColumnName, idValue);

            System.out.println(sql);
        }

        workbook.close();
        fis.close();
    }

    // 将各种类型的单元格统一转成字符串
    private static String getCellValueAsString(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    double numericValue = cell.getNumericCellValue();
                    return String.valueOf((long) numericValue); // 去除小数部分
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return cell.getStringCellValue();
        }
    }

}