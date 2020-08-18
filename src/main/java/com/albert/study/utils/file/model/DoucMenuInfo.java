package com.albert.study.utils.file.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 对应douc的实体类
 * @author Albert
 * @date 2020/8/18 11:42
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DoucMenuInfo {

    private String name;

    private String code;

    private String parentCode;

    private String type;

    private String authId;

    private String description;


}
