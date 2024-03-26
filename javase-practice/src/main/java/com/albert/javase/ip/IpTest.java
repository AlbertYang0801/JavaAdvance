package com.albert.javase.ip;

/**
 * @author yangjunwei
 * @date 2022/12/12 5:55 下午
 */
public class IpTest {

    /**
     * 判断ip是否在目标网段
     * @param network ip地址
     * @param mask 目标网段
     * @return
     */
    public static boolean isInRange(String network, String mask) {
        String[] networkips = network.split("\\.");
        int ipAddr = (Integer.parseInt(networkips[0]) << 24)
                | (Integer.parseInt(networkips[1]) << 16)
                | (Integer.parseInt(networkips[2]) << 8)
                | Integer.parseInt(networkips[3]);
        int type = Integer.parseInt(mask.replaceAll(".*/", ""));
        int mask1 = 0xFFFFFFFF << (32 - type);
        String maskIp = mask.replaceAll("/.*", "");
        String[] maskIps = maskIp.split("\\.");
        int cidrIpAddr = (Integer.parseInt(maskIps[0]) << 24)
                | (Integer.parseInt(maskIps[1]) << 16)
                | (Integer.parseInt(maskIps[2]) << 8)
                | Integer.parseInt(maskIps[3]);

        return (ipAddr & mask1) == (cidrIpAddr & mask1);
    }

    public static void main(String[] args) {
        System.out.println(isInRange("10.3.68.61", "10.3.68.0/24"));
        System.out.println(isInRange("172.15.9.223", "172.15.9.0/24"));
    }


}
