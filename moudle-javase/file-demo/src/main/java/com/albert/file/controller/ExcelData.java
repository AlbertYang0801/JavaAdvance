package com.albert.file.controller;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yangjunwei
 * @date 2021/6/4 上午9:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExcelData {

    @ExcelProperty("名字")
    private String name;

}
