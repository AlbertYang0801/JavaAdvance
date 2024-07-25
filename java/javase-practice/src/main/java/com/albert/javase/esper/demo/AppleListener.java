package com.albert.javase.esper.demo;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

/**
 * @author yjw
 * @date 2021/6/2 10:09
 */
public class AppleListener implements UpdateListener {
    @Override
    public void update(EventBean[] newEvents, EventBean[] oldEvents) {
        Double avgPrice = (Double)newEvents[0].get("price");
        System.out.println(avgPrice);
    }
}
