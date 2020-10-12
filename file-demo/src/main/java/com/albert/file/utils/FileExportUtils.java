package com.albert.file.utils;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.*;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * 文件导出为Excel，带图表导出
 *
 * @author Albert
 * @date 2020/10/9 18:28
 */
@Slf4j
public class FileExportUtils {

    /**
     * 生成图表的水平方向长度
     */
    private final static Integer horizontalLength = 15;

    /**
     * 生成图表的垂直方向长度
     */
    public final static Integer verticalLength = 15;

    /**
     * 创建数据表格
     *
     * @param styleList 表格样式
     * @param sheet    指定工作表
     * @param titleArr 标题行数据
     * @param dataList 表格内容
     * @return
     */
    public static boolean drawSheetTable(List<CellStyle> styleList,SXSSFSheet sheet, List<String> titleArr, List<Map<String, Object>> dataList) {
        // 测试时返回值
        boolean result = true;
        // 初始化表格样式
        // 根据数据创建excel第一行标题行
        SXSSFRow row0 = sheet.createRow(0);
        for (int i = 0; i < titleArr.size(); i++) {
            // 设置标题
            row0.createCell(i).setCellValue(titleArr.get(i));
            // 设置标题行样式
            row0.getCell(i).setCellStyle(styleList.get(0));
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
                    sheet.getRow(i + 1).getCell(j).setCellStyle(styleList.get(0));
                } else {
                    //数据必须是数据类型，不能是字符串
                    rowi.createCell(j).setCellValue(Double.parseDouble((String) data.get("value" + (j + 1))));
                    // 设置数据样式
                    sheet.getRow(i + 1).getCell(j).setCellStyle(styleList.get(2));
                }
            }
        }
        return result;
    }

    /**
     * 创建柱状图
     *
     * @param sheet      指定生成的工作表
     * @param fldNameArr 字段列表
     * @param dataList   数据列表
     * @param startLine  垂直方向，图表开始行
     * @return
     */
    public static boolean createHistogramChar(SXSSFSheet sheet, STBarGrouping.Enum group, List<String> fldNameArr,
                                              List<Map<String, Object>> dataList, Integer startLine) {
        boolean result = false;
        // 获取sheet名称
        String sheetName = sheet.getSheetName();
        // 创建一个画布
        Drawing<?> drawing = sheet.createDrawingPatriarch();
        // 画一个图区域
        // 前四个默认0，从第8行到第25行,从第0列到第6列的区域
        ClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 0, startLine, horizontalLength, startLine + verticalLength);
        // 创建一个chart对象
        Chart chart = drawing.createChart(anchor);
        CTChart ctChart = ((XSSFChart) chart).getCTChart();
        CTPlotArea ctPlotArea = ctChart.getPlotArea();
        // 创建柱状图模型
        CTBarChart ctBarChart = ctPlotArea.addNewBarChart();
        CTBoolean ctBoolean = ctBarChart.addNewVaryColors();
        ctBarChart.getVaryColors().setVal(true);
        // 设置图类型
        ctBarChart.addNewGrouping().setVal(group);
        ctBoolean.setVal(true);
        ctBarChart.addNewBarDir().setVal(STBarDir.COL);
        // 是否添加左侧坐标轴
        ctChart.addNewDispBlanksAs().setVal(STDispBlanksAs.ZERO);
        ctChart.addNewShowDLblsOverMax().setVal(true);
        // 设置这两个参数是为了在STACKED模式下生成堆积模式；(standard)标准模式时需要将这两行去掉
        if ("stacked".equals(group.toString()) || "percentStacked".equals(group.toString())) {
            ctBarChart.addNewGapWidth().setVal(150);
            ctBarChart.addNewOverlap().setVal((byte) 100);
        }
        // 创建序列,并且设置选中区域
        for (int i = 0; i < fldNameArr.size() - 1; i++) {
            CTBarSer ctBarSer = ctBarChart.addNewSer();
            CTSerTx ctSerTx = ctBarSer.addNewTx();
            // 图例区
            CTStrRef ctStrRef = ctSerTx.addNewStrRef();
            // 选定区域第0行,第1,2,3列标题作为图例 //1 2 3
            String legendDataRange = new CellRangeAddress(0, 0, i + 1, i + 1).formatAsString(sheetName, true);
            ctStrRef.setF(legendDataRange);
            ctBarSer.addNewIdx().setVal(i);
            // 横坐标区
            CTAxDataSource cttAxDataSource = ctBarSer.addNewCat();
            ctStrRef = cttAxDataSource.addNewStrRef();
            // 选第0列,第1-6行作为横坐标区域
            String axisDataRange = new CellRangeAddress(1, dataList.size(), 0, 0).formatAsString(sheetName, true);
            ctStrRef.setF(axisDataRange);
            // 数据区域
            CTNumDataSource ctNumDataSource = ctBarSer.addNewVal();
            CTNumRef ctNumRef = ctNumDataSource.addNewNumRef();
            // 选第1-6行,第1-3列作为数据区域 //1 2 3
            String numDataRange = new CellRangeAddress(1, dataList.size(), i + 1, i + 1).formatAsString(sheetName,
                    true);
//            System.out.println(numDataRange);
            ctNumRef.setF(numDataRange);
            // 添加柱状边框线
            ctBarSer.addNewSpPr().addNewLn().addNewSolidFill().addNewSrgbClr().setVal(new byte[]{0, 0, 0});
            // 设置负轴颜色不是白色
            ctBarSer.addNewInvertIfNegative().setVal(false);
            // 设置标签格式
            ctBoolean.setVal(false);
            CTDLbls newDLbls = ctBarSer.addNewDLbls();
            newDLbls.setShowLegendKey(ctBoolean);
            ctBoolean.setVal(true);
            newDLbls.setShowVal(ctBoolean);
            ctBoolean.setVal(false);
            newDLbls.setShowCatName(ctBoolean);
            newDLbls.setShowSerName(ctBoolean);
            newDLbls.setShowPercent(ctBoolean);
            newDLbls.setShowBubbleSize(ctBoolean);
            newDLbls.setShowLeaderLines(ctBoolean);
        }
        // 告诉BarChart它有坐标轴，并给它们id
        ctBarChart.addNewAxId().setVal(123456);
        ctBarChart.addNewAxId().setVal(123457);
        // 横坐标
        CTCatAx ctCatAx = ctPlotArea.addNewCatAx();
        ctCatAx.addNewAxId().setVal(123456); // id of the cat axis
        CTScaling ctScaling = ctCatAx.addNewScaling();
        ctScaling.addNewOrientation().setVal(STOrientation.MIN_MAX);
        ctCatAx.addNewAxPos().setVal(STAxPos.B);
        ctCatAx.addNewCrossAx().setVal(123457); // id of the val axis
        ctCatAx.addNewTickLblPos().setVal(STTickLblPos.NEXT_TO);
        // 纵坐标
        CTValAx ctValAx = ctPlotArea.addNewValAx();
        ctValAx.addNewAxId().setVal(123457); // id of the val axis
        ctScaling = ctValAx.addNewScaling();
        ctScaling.addNewOrientation().setVal(STOrientation.MIN_MAX);
        // 设置位置
        ctValAx.addNewAxPos().setVal(STAxPos.L);
        ctValAx.addNewCrossAx().setVal(123456); // id of the cat axis
        ctValAx.addNewTickLblPos().setVal(STTickLblPos.NEXT_TO);
        // 是否删除主左边轴
        ctValAx.addNewDelete().setVal(false);
        // 是否删除横坐标
        ctCatAx.addNewDelete().setVal(false);
        // legend图注
        // if(true){
        CTLegend ctLegend = ctChart.addNewLegend();
        ctLegend.addNewLegendPos().setVal(STLegendPos.B);
        ctLegend.addNewOverlay().setVal(false);
        // }
        return result;
    }

    /**
     * 生成折线图
     *
     * @param sheet      指定生成的工作表
     * @param type       图表类型
     * @param fldNameArr 字段列表
     * @param dataList   数据列表
     * @param startLine  垂直方向，图表开始行
     * @return
     */
    public static boolean createLineChar(SXSSFSheet sheet, String type, List<String> fldNameArr,
                                         List<Map<String, Object>> dataList, Integer startLine) {
        boolean result = true;
        // 获取sheet名称
        String sheetName = sheet.getSheetName();
        // 创建一个画布
        Drawing<?> drawing = sheet.createDrawingPatriarch();
        // 画一个图区域
        // 前四个默认0，从第8行到第25行,从第0列到第6列的区域
        ClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 0, startLine, horizontalLength, startLine + verticalLength);
        // 创建一个chart对象
        Chart chart = drawing.createChart(anchor);
        CTChart ctChart = ((XSSFChart) chart).getCTChart();
        CTPlotArea ctPlotArea = ctChart.getPlotArea();
        if (type.equals("line-bar")) {
            CTBarChart ctBarChart = ctPlotArea.addNewBarChart();
            CTBoolean ctBoolean = ctBarChart.addNewVaryColors();
            ctBarChart.getVaryColors().setVal(true);
            // 设置类型
            ctBarChart.addNewGrouping().setVal(STBarGrouping.CLUSTERED);
            ctBoolean.setVal(true);
            ctBarChart.addNewBarDir().setVal(STBarDir.COL);
            // 是否添加左侧坐标轴
            ctChart.addNewDispBlanksAs().setVal(STDispBlanksAs.ZERO);
            ctChart.addNewShowDLblsOverMax().setVal(true);
            // 创建序列,并且设置选中区域
            for (int i = 0; i < fldNameArr.size() - 1; i++) {
                CTBarSer ctBarSer = ctBarChart.addNewSer();
                CTSerTx ctSerTx = ctBarSer.addNewTx();
                // 图例区
                CTStrRef ctStrRef = ctSerTx.addNewStrRef();
                // 选定区域第0行,第1,2,3列标题作为图例 //1 2 3
                String legendDataRange = new CellRangeAddress(0, 0, i + 1, i + 1).formatAsString(sheetName, true);
                ctStrRef.setF(legendDataRange);
                ctBarSer.addNewIdx().setVal(i);
                // 横坐标区
                CTAxDataSource cttAxDataSource = ctBarSer.addNewCat();
                ctStrRef = cttAxDataSource.addNewStrRef();
                // 选第0列,第1-6行作为横坐标区域
                String axisDataRange = new CellRangeAddress(1, dataList.size(), 0, 0).formatAsString(sheetName, true);
                ctStrRef.setF(axisDataRange);
                // 数据区域
                CTNumDataSource ctNumDataSource = ctBarSer.addNewVal();
                CTNumRef ctNumRef = ctNumDataSource.addNewNumRef();
                // 选第1-6行,第1-3列作为数据区域 //1 2 3
                String numDataRange = new CellRangeAddress(1, dataList.size(), i + 1, i + 1).formatAsString(sheetName,
                        true);
//                System.out.println(numDataRange);
                ctNumRef.setF(numDataRange);
                ctBarSer.addNewSpPr().addNewLn().addNewSolidFill().addNewSrgbClr().setVal(new byte[]{0, 0, 0});
                // 设置负轴颜色不是白色
                ctBarSer.addNewInvertIfNegative().setVal(false);
                // 设置标签格式
                ctBoolean.setVal(false);
                CTDLbls newDLbls = ctBarSer.addNewDLbls();
                newDLbls.setShowLegendKey(ctBoolean);
                ctBoolean.setVal(true);
                newDLbls.setShowVal(ctBoolean);
                ctBoolean.setVal(false);
                newDLbls.setShowCatName(ctBoolean);
                newDLbls.setShowSerName(ctBoolean);
                newDLbls.setShowPercent(ctBoolean);
                newDLbls.setShowBubbleSize(ctBoolean);
                newDLbls.setShowLeaderLines(ctBoolean);
            }
            // telling the BarChart that it has axes and giving them Ids
            ctBarChart.addNewAxId().setVal(123456);
            ctBarChart.addNewAxId().setVal(123457);
            // cat axis
            CTCatAx ctCatAx = ctPlotArea.addNewCatAx();
            ctCatAx.addNewAxId().setVal(123456); // id of the cat axis
            CTScaling ctScaling = ctCatAx.addNewScaling();
            ctScaling.addNewOrientation().setVal(STOrientation.MIN_MAX);
            ctCatAx.addNewAxPos().setVal(STAxPos.B);
            ctCatAx.addNewCrossAx().setVal(123457); // id of the val axis
            ctCatAx.addNewTickLblPos().setVal(STTickLblPos.NEXT_TO);
            // val axis
            CTValAx ctValAx = ctPlotArea.addNewValAx();
            ctValAx.addNewAxId().setVal(123457); // id of the val axis
            ctScaling = ctValAx.addNewScaling();
            ctScaling.addNewOrientation().setVal(STOrientation.MIN_MAX);
            ctValAx.addNewAxPos().setVal(STAxPos.L);
            ctValAx.addNewCrossAx().setVal(123456); // id of the cat axis
            ctValAx.addNewTickLblPos().setVal(STTickLblPos.NEXT_TO);
        }
        // 折线图
        CTLineChart ctLineChart = ctPlotArea.addNewLineChart();
        CTBoolean ctBoolean = ctLineChart.addNewVaryColors();
        ctLineChart.addNewGrouping().setVal(STGrouping.STANDARD);
        // 创建序列,并且设置选中区域
        for (int i = 0; i < fldNameArr.size() - 1; i++) {
            CTLineSer ctLineSer = ctLineChart.addNewSer();
            CTSerTx ctSerTx = ctLineSer.addNewTx();
            // 图例区
            CTStrRef ctStrRef = ctSerTx.addNewStrRef();
            // 选定区域第0行,第1,2,3列标题作为图例 //1 2 3
            String legendDataRange = new CellRangeAddress(0, 0, i + 1, i + 1).formatAsString(sheetName, true);
            ctStrRef.setF(legendDataRange);
            ctLineSer.addNewIdx().setVal(i);
            // 横坐标区
            CTAxDataSource cttAxDataSource = ctLineSer.addNewCat();
            ctStrRef = cttAxDataSource.addNewStrRef();
            // 选第0列,第1-6行作为横坐标区域
            String axisDataRange = new CellRangeAddress(1, dataList.size(), 0, 0).formatAsString(sheetName, true);
            ctStrRef.setF(axisDataRange);
            // 数据区域
            CTNumDataSource ctNumDataSource = ctLineSer.addNewVal();
            CTNumRef ctNumRef = ctNumDataSource.addNewNumRef();
            // 选第1-6行,第1-3列作为数据区域 //1 2 3
            String numDataRange = new CellRangeAddress(1, dataList.size(), i + 1, i + 1).formatAsString(sheetName,
                    true);
//            System.out.println(numDataRange);
            ctNumRef.setF(numDataRange);
            // 设置标签格式
            ctBoolean.setVal(false);
            CTDLbls newDLbls = ctLineSer.addNewDLbls();
            newDLbls.setShowLegendKey(ctBoolean);
            ctBoolean.setVal(true);
            newDLbls.setShowVal(ctBoolean);
            ctBoolean.setVal(false);
            newDLbls.setShowCatName(ctBoolean);
            newDLbls.setShowSerName(ctBoolean);
            newDLbls.setShowPercent(ctBoolean);
            newDLbls.setShowBubbleSize(ctBoolean);
            newDLbls.setShowLeaderLines(ctBoolean);
            // 是否是平滑曲线
            CTBoolean addNewSmooth = ctLineSer.addNewSmooth();
            addNewSmooth.setVal(false);
            // 是否是堆积曲线
            CTMarker addNewMarker = ctLineSer.addNewMarker();
            CTMarkerStyle addNewSymbol = addNewMarker.addNewSymbol();
            addNewSymbol.setVal(STMarkerStyle.NONE);
        }
        // telling the BarChart that it has axes and giving them Ids
        ctLineChart.addNewAxId().setVal(123456);
        ctLineChart.addNewAxId().setVal(123457);
        // cat axis
        CTCatAx ctCatAx = ctPlotArea.addNewCatAx();
        ctCatAx.addNewAxId().setVal(123456); // id of the cat axis
        CTScaling ctScaling = ctCatAx.addNewScaling();
        ctScaling.addNewOrientation().setVal(STOrientation.MIN_MAX);
        ctCatAx.addNewAxPos().setVal(STAxPos.B);
        ctCatAx.addNewCrossAx().setVal(123457); // id of the val axis
        ctCatAx.addNewTickLblPos().setVal(STTickLblPos.NEXT_TO);
        // val axis
        CTValAx ctValAx = ctPlotArea.addNewValAx();
        ctValAx.addNewAxId().setVal(123457); // id of the val axis
        ctScaling = ctValAx.addNewScaling();
        ctScaling.addNewOrientation().setVal(STOrientation.MIN_MAX);
        ctValAx.addNewAxPos().setVal(STAxPos.L);
        ctValAx.addNewCrossAx().setVal(123456); // id of the cat axis
        ctValAx.addNewTickLblPos().setVal(STTickLblPos.NEXT_TO);
        // 是否删除主左边轴
        ctValAx.addNewDelete().setVal(false);
        // 是否删除横坐标
        if (type.equals("line-bar")) {
            ctCatAx.addNewDelete().setVal(true);
        }
        CTLegend ctLegend = ctChart.addNewLegend();
        ctLegend.addNewLegendPos().setVal(STLegendPos.B);
        ctLegend.addNewOverlay().setVal(false);
        return result;
    }

    /**
     * 生成表格样式
     *
     * @return
     */
    public static List<CellStyle> tableStyle(SXSSFWorkbook wb) {
        List<CellStyle> cellStyleList = Lists.newArrayList();
        // 样式准备
        // 表头和左边字段样式
        CellStyle headerStyle = wb.createCellStyle();
        //设置颜色
        headerStyle.setFillForegroundColor(IndexedColors.ROYAL_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);


        headerStyle.setBorderBottom(BorderStyle.THIN); // 下边框
        headerStyle.setBorderLeft(BorderStyle.THIN);// 左边框
        headerStyle.setBorderTop(BorderStyle.THIN);// 上边框
        headerStyle.setBorderRight(BorderStyle.THIN);// 右边框
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyleList.add(headerStyle);

        CellStyle style1 = wb.createCellStyle();
        style1.setBorderBottom(BorderStyle.THIN); // 下边框
        style1.setBorderLeft(BorderStyle.THIN);// 左边框
        style1.setBorderTop(BorderStyle.THIN);// 上边框
        style1.setBorderRight(BorderStyle.THIN);// 右边框
        style1.setAlignment(HorizontalAlignment.CENTER);
        cellStyleList.add(style1);

        //数据样式
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setBorderTop(BorderStyle.THIN);// 上边框
        cellStyle.setBorderBottom(BorderStyle.THIN); // 下边框
        cellStyle.setBorderLeft(BorderStyle.THIN);// 左边框
        cellStyle.setBorderRight(BorderStyle.THIN);// 右边框
        cellStyle.setAlignment(HorizontalAlignment.CENTER);// 水平对齐方式
        // cellStyle.setVerticalAlignment(VerticalAlignment.TOP);//垂直对齐方式
        cellStyleList.add(cellStyle);

        return cellStyleList;
    }

    /**
     * 设置文本响应流方法
     * @param response
     * @param fileName
     */
    public static void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(), "ISO8859-1");
                log.info("导出的文件名字为:{}",fileName);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS,DELETE");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Headers", "x-requested-with,Content-Type");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
