package com.albert.study.utils.file.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Albert
 * @date 2020/8/18 18:05
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ButtonInfo {

    @JsonProperty("sys_id")
    private Integer sysId;

    @JsonProperty("button_id")
    private Integer buttonId;

    @JsonProperty("button_name")
    private String buttonName;

    @JsonProperty("menu_id")
    private Integer menuId;


}
