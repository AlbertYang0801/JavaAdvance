package com.albert.mail.utils;

import com.sun.mail.util.MailSSLSocketFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * 邮件发送的工具类
 *
 * @author Albert
 * @date 2020/9/23 09:18
 */
@Slf4j
@Component
public class MailUtil {

    @Resource
    ConfUtil confUtil;

    private static String userName;
    private static String password;
    private static String host;
    private static String port;
    private static String protocol;
    private static Boolean isSsl;
    private static Boolean mailDebug;

    /**
     * 类初始化赋值
     */
    @PostConstruct
    public void init() {
        userName = confUtil.getUserName();
        password = confUtil.getPassword();
        host = confUtil.getHost();
        port = confUtil.getPort();
        protocol = confUtil.getProtocol();
        isSsl = Boolean.valueOf(confUtil.getIsSsl());
        mailDebug = Boolean.valueOf(confUtil.getMailDebug());
    }

    /**
     * 创建链接
     *
     * @return 邮件
     */
    private static MimeMessage initMimeMessage() {
        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", protocol);
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.port", port);
        // 使用smtp身份验证
        properties.put("mail.smtp.auth", "true");
        if (isSsl) {
            // 开启安全协议,使用SSL,企业邮箱必需 start
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        } else {
            properties.put("mail.smtp.ssl.enable", "flase");
        }
        MailSSLSocketFactory mailSslSocketFactory = null;
        try {
            mailSslSocketFactory = new MailSSLSocketFactory();
            //是否信任所有主机
            mailSslSocketFactory.setTrustAllHosts(true);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        properties.put("mail.smtp.ssl.socketFactory", mailSslSocketFactory);
        properties.put("mail.smtp.socketFactory.fallback", "false");
        properties.put("mail.smtp.socketFactory.port", port);
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        });
        session.setDebug(mailDebug);
        return new MimeMessage(session);
    }

    /**
     * 发送邮件，带附件，支持发送批量附件
     *
     * @param sender       发件人名称
     * @param subject      邮件主题
     * @param content      邮件内容
     * @param receiverList 接收者列表
     * @param fileSrc      附件地址列表
     */
    public static void sendMail(String sender, String subject, String content, String receiverList, List<String> fileSrc) {
        try {
            log.info("开始发送邮件");
            //初始化邮件发送类
            MimeMessage mimeMessage = initMimeMessage();
            // 发件人,可以设置发件人的别名
            mimeMessage.setFrom(new InternetAddress(userName, sender));
            // 收件人,多人接收
            InternetAddress[] internetAddressTo = InternetAddress.parse(receiverList);
            mimeMessage.setRecipients(Message.RecipientType.TO, internetAddressTo);
            // 主题
            mimeMessage.setSubject(subject);
            // 时间
            mimeMessage.setSentDate(new Date());
            // 容器类 附件
            MimeMultipart mimeMultipart = new MimeMultipart();
            // 可以包装文本,图片,附件
            MimeBodyPart bodyPart = new MimeBodyPart();
            // 设置内容
            bodyPart.setContent(content, "text/html; charset=UTF-8");
            mimeMultipart.addBodyPart(bodyPart);
            //循环添加附件
            for (String file : fileSrc) {
                // 添加图片&附件
                bodyPart = new MimeBodyPart();
                bodyPart.attachFile(file);
                mimeMultipart.addBodyPart(bodyPart);
            }
            mimeMessage.setContent(mimeMultipart);
            mimeMessage.saveChanges();
            Transport.send(mimeMessage);
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }
    }


}
