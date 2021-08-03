package com.albert.javase.esper.demo;

import com.espertech.esper.client.*;

/**
 * @author yjw
 * @date 2021/6/2 10:12
 */
public class TestApple {

    public static void main(String[] args) {
        EPServiceProvider defaultProvider = EPServiceProviderManager.getDefaultProvider();
        EPAdministrator epAdministrator = defaultProvider.getEPAdministrator();
        String product = Apple.class.getName();
//        String epl = "select max(price) as price from " + product + ".win:length_batch(3)";

        String epl = "select name,price as price from " + product;
        EPStatement epStatement = epAdministrator.createEPL(epl);
        epStatement.addListener(new AppleListener());
        epStatement.addListener(new AppleDemoListener());

        String eplA = "select max(price) as price from " + product + ".win:length_batch(3)";
        EPStatement epStatementA = epAdministrator.createEPL(eplA);
        epStatementA.addListener(new AppleListener());


        EPRuntime epRuntime = defaultProvider.getEPRuntime();

        Apple appleA = new Apple("a", 10);
        Apple appleB = new Apple("b", 20);
        Apple appleC = new Apple("c", 30);

        epRuntime.sendEvent(appleA);
        epRuntime.sendEvent(appleB);
        epRuntime.sendEvent(appleC);
    }

}
