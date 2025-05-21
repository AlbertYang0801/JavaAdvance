package com.albert.file;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.alibaba.excel.EasyExcel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.List;

/**
 * @author yangjunwei
 * @date 2025/5/9 17:35
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ExcelTest {

    @Test
    public void testRead() {
        try {
            String filePath = "D:\\IdeaWorkSpace\\JavaAdvance\\moudle-javase\\file-demo\\file\\余额数据222.xlsx";
            ExcelReader reader = ExcelUtil.getReader(new File(filePath));
            List<List<Object>> rows = reader.read();
            for (List<Object> row : rows) {
                for (Object cell : row) {
                    System.out.print(cell + "\t");
                }
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 或者使用日志框架：log.error("读取Excel失败", e);
        }
    }


}
