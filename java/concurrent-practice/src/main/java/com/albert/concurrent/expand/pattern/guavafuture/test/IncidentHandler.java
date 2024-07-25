package com.albert.concurrent.expand.pattern.guavafuture.test;

/**
 * @author yangjunwei
 * @date 2024/7/3
 */
public class IncidentHandler {

    public <V> void handler(Integer value, IncidentCallback<String> incidentCallback) {
        try {
            if (value > 100) {
                incidentCallback.success(value.toString());
            } else {
                throw new RuntimeException("数据报错啦啦啦啦");
            }
        } catch (Exception e) {
            incidentCallback.failure(e);
        }
    }

    public static void main(String[] args) {
        IncidentHandler incidentHandler = new IncidentHandler();
        incidentHandler.handler(10, new IncidentCallback<String>() {
            @Override
            public void success(String s) {
                System.out.println("成功处理");
            }

            @Override
            public void failure(Throwable throwable) {
                System.out.println("异常捕获");
                throwable.printStackTrace();
            }
        });
    }



}
