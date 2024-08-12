package com.albert.mysql.db;

import com.albert.mysql.MysqlApplication;
import com.albert.mysql.model.entity.T2Do;
import com.albert.mysql.service.IT2Service;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangjunwei
 * @date 2024/8/6
 */
@SpringBootTest(classes = MysqlApplication.class)
@RunWith(SpringRunner.class)
public class T2Test {

    @Autowired
    IT2Service it2Service;

    @Test
    public void insert() {
        for (int i = 0; i < 1000; i++) {
            List<T2Do> t2Dos = buildList(i);
            it2Service.saveBatch(t2Dos, 200);
        }

    }

    private List<T2Do> buildList(int i) {
        int length = 200;
        List<T2Do> t2Dos = new ArrayList<T2Do>();
        for (int j = i * length; j < (i + 1) * length; j++) {
            T2Do t2Do = new T2Do();
            t2Do.setA(j);
            t2Do.setB(j);
            t2Dos.add(t2Do);
        }
        return t2Dos;
    }


}
