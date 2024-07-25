package com.albert.javase.jvm.stringtable;

import java.util.ArrayList;
import java.util.List;

/**
 * 1.7
 * -Xmx10m：限制堆内存大小
 * -XX:-UseGCOverheadLimit 取消堆内存垃圾回收的限制
 * 默认：若垃圾回收占用百分之98时间，但是回收的堆内存2%左右，则报错OOM。
 * @author yjw
 * @date 2022/4/5 21:44
 */
public class StringTableOom {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        int i=0;
        try {
            for(int j=0;j<260000;j++){
                list.add(String.valueOf(i).intern());
                i++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println(i);
        }

    }


}
