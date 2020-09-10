package com.albert.utils.file.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 对应联通接口返回的实体类
 * @author Albert
 * @date 2020/8/18 11:42
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuInfo {

    private String path;

    @JsonProperty("sys_id")
    private Integer sysId;

    private List<MenuInfo> children;

    @JsonProperty("parent_id")
    private Integer parentId;

    @JsonProperty("menu_name")
    private String menuName;


    @JsonProperty("menu_id")
    private Integer menuId;

    @JsonProperty("is_show")
    private String isShow;


}
