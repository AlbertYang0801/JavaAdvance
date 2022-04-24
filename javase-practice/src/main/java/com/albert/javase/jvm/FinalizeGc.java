package com.albert.javase.jvm;

import lombok.SneakyThrows;

/**
 * @author yjw
 * @date 2022/4/17 18:04
 */
public class FinalizeGc {

    private static FinalizeGc save = null;

    public void isAlive(){
        System.out.println("yes");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed!");
        //自我拯救
        FinalizeGc.save=this;
    }

    @SneakyThrows
    public static void main(String[] args) {
        save = new FinalizeGc();
        save = null;
        System.gc();
        Thread.sleep(500);
        if(save!=null){
            save.isAlive();
        }else{
            System.out.println("no");
        }

        //只能执行一次，第二次无效
        save = null;
        System.gc();
        Thread.sleep(500);
        if(save!=null){
            save.isAlive();
        }else{
            System.out.println("no");
        }
    }


}
