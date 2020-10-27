package com.albert.file.controller;

import com.albert.file.utils.FileExportUtils;
import com.albert.utils.localdatetime.LocalDateTimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.openxmlformats.schemas.drawingml.x2006.chart.STBarGrouping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 使用Poi进行Excel文件导出
 *
 * @author Albert
 * @date 2020/10/9 11:00
 */
@RestController
@Slf4j
public class FilePoiExportController {

    /**
     * 导出excel,带图表
     */
    @GetMapping("/export/excel")
    public void exportExcelChar(HttpServletResponse response) {

        // 字段名
        List<String> fldNameArr = new ArrayList<String>();
        // 标题
        List<String> titleArr = new ArrayList<String>();
        // 模拟数据
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        Map<String, Object> dataMap1 = new HashMap<String, Object>();
        dataMap1.put("value1", "股票");
        dataMap1.put("value2", Math.floor(Math.random() * 100) + "");
        dataMap1.put("value3", Math.floor(Math.random() * 100) + "");
        dataMap1.put("value4", Math.floor(Math.random() * 100) + "");
        Map<String, Object> dataMap2 = new HashMap<String, Object>();
        dataMap2.put("value1", "货币型基金");
        dataMap2.put("value2", Math.floor(Math.random() * 100) + "");
        dataMap2.put("value3", Math.floor(Math.random() * 100) + "");
        dataMap2.put("value4", Math.floor(Math.random() * 100) + "");
        Map<String, Object> dataMap3 = new HashMap<String, Object>();
        dataMap3.put("value1", "可转债");
        dataMap3.put("value2", Math.floor(Math.random() * 100) + "");
        dataMap3.put("value3", Math.floor(Math.random() * 100) + "");
        dataMap3.put("value4", Math.floor(Math.random() * 100) + "");
        Map<String, Object> dataMap4 = new HashMap<String, Object>();
        dataMap4.put("value1", "买入返售");
        dataMap4.put("value2", Math.floor(Math.random() * 100) + "");
        dataMap4.put("value3", Math.floor(Math.random() * 100) + "");
        dataMap4.put("value4", Math.floor(Math.random() * 100) + "");
        Map<String, Object> dataMap5 = new HashMap<String, Object>();
        dataMap5.put("value1", "通知存款");
        dataMap5.put("value2", Math.floor(Math.random() * 100) + "");
        dataMap5.put("value3", Math.floor(Math.random() * 100) + "");
        dataMap5.put("value4", Math.floor(Math.random() * 100) + "");
        Map<String, Object> dataMap6 = new HashMap<String, Object>();
        dataMap6.put("value1", "当月累计");
        dataMap6.put("value2", Math.floor(Math.random() * 100) + "");
        dataMap6.put("value3", Math.floor(Math.random() * 100) + "");
        dataMap6.put("value4", Math.floor(Math.random() * 100) + "");
        fldNameArr.add("value1");
        fldNameArr.add("value2");
        fldNameArr.add("value3");
        fldNameArr.add("value4");
        titleArr.add("类型");
        titleArr.add("买入");
        titleArr.add("卖出");
        titleArr.add("分红");
        dataList.add(dataMap1);
        dataList.add(dataMap2);
        dataList.add(dataMap3);
        dataList.add(dataMap4);
        dataList.add(dataMap5);
        dataList.add(dataMap6);

        //创建Excel
        SXSSFWorkbook wb = new SXSSFWorkbook();
        //创建工作表
        SXSSFSheet sheet = wb.createSheet("第一个");
        //获取表格数据样式
        List<CellStyle> cellStyleList = FileExportUtils.tableStyle(wb);

        boolean tableResult = FileExportUtils.drawSheetTable(cellStyleList, sheet, titleArr, dataList);

        //图表的间隔长度
        int length = 10;

        Integer verticalLength = FileExportUtils.verticalLength;
        FileOutputStream out = null;
        try {
            boolean histogramChar = FileExportUtils.createHistogramChar(sheet, STBarGrouping.CLUSTERED, fldNameArr, dataList, dataList.size() + length);
            boolean line = FileExportUtils.createLineChar(sheet, "line", fldNameArr, dataList, verticalLength + length * 2);

            String fileName = "1111.xls";
            FileExportUtils.setResponseHeader(response, fileName);
            OutputStream outputStream = response.getOutputStream();
            wb.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }





}

