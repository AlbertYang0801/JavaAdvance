package com.albert.file.service;

import com.csvreader.CsvReader;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yangjunwei
 * @date 2021/7/6 下午3:35
 */
public class CsvReadService {

    // 需要写入的 csv 文件路径
    public static final String WRITE_CSV_FILE_PATH = "/Users/yangjunwei/IdeaProjects/JavaAdvanced/file-demo/file/测试csv.csv";

    /**
     * 读取 csv 文件
     */
    public static void readCsvFile(Integer startLine,String readCsvFilePath) {
        // 缓存读取的数据
        List<String[]> content = new ArrayList<>();

        try {
            // 创建 CSV Reader 对象, 参数说明（读取的文件路径，分隔符，编码格式)
            CsvReader csvReader = new CsvReader(readCsvFilePath, ',', StandardCharsets.UTF_8);
            // 跳过表头
            csvReader.readHeaders();
            int index = 0;

            // 读取除表头外的内容
            while (csvReader.readRecord()) {
                index++;
                if(index<startLine){
                    continue;
                }

                // 读取一整行
                String line = csvReader.getRawRecord();
                System.out.println(line);

                content.add(csvReader.getValues());
                System.out.println("读取到第"+index);
            }
            csvReader.close();

            for (int row = 0; row < content.size(); row++) {
                // 读取第 row 行，第 0 列的数据
                String orderNum = content.get(row)[0];
                System.out.println("==> orderNum: " + orderNum);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 读取 csv 文件
     */
    public static void readCsvFile(String readCsvFilePath) {
        // 缓存读取的数据
        List<String[]> content = new ArrayList<>();

        try {
            // 创建 CSV Reader 对象, 参数说明（读取的文件路径，分隔符，编码格式)
            CsvReader csvReader = new CsvReader(readCsvFilePath, ',', StandardCharsets.UTF_8);
            // 跳过表头
            csvReader.readHeaders();


            // 读取除表头外的内容
            while (csvReader.readRecord()) {
                // 读取一整行
                String line = csvReader.getRawRecord();
                System.out.println(line);

                content.add(csvReader.getValues());
            }
            csvReader.close();

            for (int row = 0; row < content.size(); row++) {
                // 读取第 row 行，第 0 列的数据
                String orderNum = content.get(row)[0];
                System.out.println("==> orderNum: " + orderNum);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        readCsvFile(2,WRITE_CSV_FILE_PATH);
    }
}
