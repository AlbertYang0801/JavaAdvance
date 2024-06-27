package com.albert.practice.spi;

import com.albert.javase.spi.ISearch;
import lombok.SneakyThrows;
import org.junit.Test;

import java.util.Iterator;
import java.util.ServiceLoader;

public class SpiTest {

    @SneakyThrows
    @Test
    public void test() {
        ServiceLoader<ISearch> load = ServiceLoader.load(ISearch.class);
        Iterator<ISearch> iterator = load.iterator();
        while (iterator.hasNext()) {
            ISearch next = iterator.next();
            next.search("AAA");
        }
    }


}
