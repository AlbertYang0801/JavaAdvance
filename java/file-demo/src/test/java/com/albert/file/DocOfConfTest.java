package com.albert.file;

import cn.hutool.json.JSONUtil;
import com.albert.file.model.ButtonInfo;
import com.albert.file.model.DoucMenuInfo;
import com.albert.file.model.MenuInfo;
import com.albert.file.utils.DocOfConfUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Albert
 * @date 2020/8/18 09:28
 */
@Slf4j
public class DocOfConfTest {

    private static Map<Integer, MenuInfo> menuInfoMap = Maps.newHashMap();

    /**
     * 读取resource下的json文件，并进行处理
     */
    @Test
    public void testReadConf() {
        //获取resource目录下的配置文件
        String contentInPath = DocOfConfUtil.getResourceFile("classpath:conf/menu.json");
        JSONObject parse = (JSONObject) JSONObject.parse(contentInPath);
        List<DoucMenuInfo> doucMenuInfoList = Lists.newArrayList();
        //获取菜单菜单
        doucMenuInfoList.addAll(getMenuInfoList(parse));
        //获取按钮信息按钮
        doucMenuInfoList.addAll(getButtonInfoList(parse));
        System.out.println(JSONUtil.toJsonStr(doucMenuInfoList));
    }

    /**
     * 获取菜单信息列表
     *
     * @param parse
     * @return
     */
    private List<DoucMenuInfo> getMenuInfoList(JSONObject parse) {
        try {
            JSONArray menuInfoArray = parse.getJSONArray("menuInfo");
            List<MenuInfo> menuInfoList = JSONObject.parseArray(menuInfoArray.toJSONString(), MenuInfo.class);
            List<MenuInfo> respList = Lists.newArrayList();
            //递归将树形结构转换为平级
            fillMenuInfoList(menuInfoList, respList);
            //根据path获取code
            return respList.stream().map(menuInfo -> {
                return DoucMenuInfo.builder()
                        .type("1")
                        .name(menuInfo.getMenuName())
                        .code(pathToCode(menuInfo.getPath()))
                        .build();
            }).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("parse menuinfo list Json is Exception,", e);
        }
        return Lists.newArrayList();
    }

    /**
     * 获取按钮信息列表
     *
     * @param parse
     * @return
     */
    private List<DoucMenuInfo> getButtonInfoList(JSONObject parse) {
        try {
            JSONArray buttonInfoArray = parse.getJSONArray("buttonInfo");
            List<ButtonInfo> buttonInfoList = JSONObject.parseArray(buttonInfoArray.toJSONString(), ButtonInfo.class);
            return buttonInfoList.stream().map(buttonInfo -> {
                //根据buttonId获取code
                return DoucMenuInfo.builder()
                        .type("4")
                        .name(buttonInfo.getButtonName())
                        .code(pathToCode(String.valueOf(buttonInfo.getButtonId())))
                        .build();
            }).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("parse buttonInfo list Json is Exception,", e);
        }
        return Lists.newArrayList();
    }

    /**
     * 将树状结构转换
     *
     * @param menuInfoList
     * @param respList
     */
    private void fillMenuInfoList(List<MenuInfo> menuInfoList, List<MenuInfo> respList) {
        menuInfoList.forEach(menuInfo -> {
            MenuInfo data = MenuInfo.builder()
                    .path(menuInfo.getPath())
                    .menuId(menuInfo.getMenuId())
                    .parentId(menuInfo.getParentId())
                    .menuName(menuInfo.getMenuName())
                    .build();
            respList.add(data);
            if (!CollectionUtils.isEmpty(menuInfo.getChildren())) {
                //若children不为空，进行递归
                fillMenuInfoList(menuInfo.getChildren(), respList);
            }
        });
    }

    /**
     * 获取code值
     *
     * @param path
     * @return
     */
    private String pathToCode(String path) {
        return "code";
    }


}
