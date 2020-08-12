package com.albert.study.work.aws;


import com.albert.study.utils.http.utils.HttpClientUtil;
import com.albert.study.utils.localdatetime.utils.LocalDateTimeUtils;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;


/**
 * 测试AWS的接口调用
 * 获取签名
 * @author Albert
 * @date 2020/8/12 11:43
 */
@Component
@Slf4j
public class Signature {

    //官网提供的签名加密方法
    static byte[] HmacSHA256(String data, byte[] key) throws Exception {
        String algorithm = "HmacSHA256";
        Mac mac = Mac.getInstance(algorithm);
        mac.init(new SecretKeySpec(key, algorithm));
        return mac.doFinal(data.getBytes("UTF-8"));
    }

    /**
     * @param key
     * @param dateStamp   日期格式    YYYYMMDD格式
     * @param regionName
     * @param serviceName
     * @return
     * @throws Exception
     */
    //官网提供的签名加密方法
    static byte[] getSignatureKey(String key, String dateStamp, String regionName, String serviceName) throws Exception {
        byte[] kSecret = ("AWS4" + key).getBytes("UTF-8");
        byte[] kDate = HmacSHA256(dateStamp, kSecret);
        byte[] kRegion = HmacSHA256(regionName, kDate);
        byte[] kService = HmacSHA256(serviceName, kRegion);
        byte[] kSigning = HmacSHA256("aws4_request", kService);
        return kSigning;
    }

    // action要执行的动作    version：使用的api版本
    private String request_parameters = "Action=DescribeRegions&Version=2013-10-15";
    private String method = "GET";
    private String service = "ec2";
    private String host = "ec2.amazonaws.com";
    //区域
    private String region = "us-east-1";
    private String endpoint = "https://ec2.amazonaws.com";


    private String access_key = "AWS_ACCESS_KEY_ID";
    private String secret_key = "AWS_SECRET_ACCESS_KEY";

    /**
     * 获取签名，发送请求
     */
    public void testAws() {

        if (StringUtils.isEmpty(access_key) || StringUtils.isEmpty(secret_key)) {
            System.out.println("access is empty");
        }

        //请求头的时间戳
        String amzdate = LocalDateTimeUtils.formatNow("yyMMdd") + "T" +
                LocalDateTimeUtils.formatNow("HHmmss") + "Z";
        String datestamp = LocalDateTimeUtils.formatNow("yyMMdd");
        String canonical_uri = "/";
        String canonical_querystring = request_parameters;

        String canonical_headers = "host:" + host + "\n" + "x-amz-date:" + amzdate + "\n";
        String signed_headers = "content-type;host;x-amz-date";


        //16进制字符
//        payload_hash = hashlib.sha256(("").encode("utf-8")).hexdigest()
        String payload_hash = Signature.getSHA256("");

        String canonical_request = method + "\n" + canonical_uri + "\n" +
                canonical_querystring + "\n" + canonical_headers + "\n" + signed_headers + "\n" + payload_hash;

        String algorithm = "AWS4-HMAC-SHA256";
        String credential_scope = datestamp + "/" + region + "/" + service + "/" + "aws4_request";

        //string_to_sign = algorithm + "\n" + amzdate + "\n" + credential_scope + "\n" + hashlib.sha256(canonical_request.encode("utf-8")).hexdigest()
        String string_to_sign = algorithm + "\n" + amzdate + "\n" + credential_scope + "\n"
                +Signature.getSHA256(canonical_request);

        byte[] signing_key = new byte[0];
        try {
           signing_key = Signature.getSignatureKey(secret_key, datestamp, region, service);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //签名
//        signature = hmac.new(signing_key, (string_to_sign).encode("utf-8"), hashlib.sha256).hexdigest()

       String signature = Signature.getSHA256(Signature.byte2Hex(signing_key));

        //身份验证参数
        String authorization_header = algorithm + " " + "Credential=" + access_key + "/" + credential_scope + ", " +  "SignedHeaders="
         + signed_headers + ", " + "Signature=" + signature;
        log.info(authorization_header);

//        headers = {'x-amz-date':amzdate, 'Authorization':authorization_header}

        Map<String,String> headers = Maps.newHashMap();
        headers.put("x-amz-date",amzdate);
        headers.put("Authorization",authorization_header);

//        request_url = endpoint + '?' + canonical_querystring

        String request_url = endpoint+"?"+canonical_querystring;

        try {
            //发送请求
            String result = HttpClientUtil.get(HttpClientUtil.getClient(), request_url, headers);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public static void main(String[] args) {
        Signature signature = new Signature();
        signature.testAws();
//        try {
//            String s = Signature.getSHA256("");
//            System.out.println(s);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }


    /**
     * 利用java原生的类实现SHA256加密
     * @param str 加密后的报文
     * @return
     */
    public static String getSHA256(String str){
        MessageDigest messageDigest;
        String encodestr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes("UTF-8"));
            encodestr = byte2Hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodestr;
    }

    /**
     * 将byte转为16进制
     * @param bytes
     * @return
     */
    private static String byte2Hex(byte[] bytes){
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i=0;i<bytes.length;i++){
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length()==1){
                //1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }




}



