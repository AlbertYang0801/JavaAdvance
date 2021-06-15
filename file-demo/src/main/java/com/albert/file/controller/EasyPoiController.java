package com.albert.file.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.google.common.collect.Maps;
import org.apache.poi.ss.usermodel.Workbook;
import org.assertj.core.util.Lists;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author yangjunwei
 * @date 2021/6/3 下午8:53
 */
@RestController
public class EasyPoiController {


    @RequestMapping("/exportExcel")
    public String exportExcel(HttpServletResponse response) throws IOException {

        List<List<String>> lists = Lists.newArrayList();
        List<String> strings = Lists.newArrayList();
        strings.add("表头");
        lists.add(strings);

        List<String> strings2 = Lists.newArrayList();
        strings2.add("表头2");
        lists.add(strings2);

        List<String> strings3 = Lists.newArrayList();
        strings3.add("表头3");
        lists.add(strings3);

        List<List<String>> list1 = Lists.newArrayList();
        List<String> excelData =  Lists.newArrayList();
        excelData.add("a");
        excelData.add("a");
        excelData.add("a");

        List<String> excelDataB =  Lists.newArrayList();
        excelDataB.add("b");
        excelDataB.add("b");
        excelDataB.add("b");


        list1.add(excelData);
        list1.add(excelDataB);

        response.setContentType("application/octet-stream; charset=utf-8");
        response.setHeader("Content-disposition","attachment;filename="+"default"+ ExcelTypeEnum.XLS.getValue());
        ServletOutputStream outputStream = response.getOutputStream();
        EasyExcelFactory.write(outputStream).sheet("123").head(lists)
                .doWrite(list1);
        return "aaa";

    }



}
