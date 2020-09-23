package com.albert.mail.utils;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Properties;

/**
 * 邮件发送的工具类
 *
 * @author Albert
 * @date 2020/9/23 09:18
 */
public class MailUtil {

    String account;
    String password;
    String host;
    String port;
    String protocol;

    /**
     * 初始化参数
     *
     * @param account  登录账号
     * @param password 登录密码
     * @param host     服务器地址
     * @param port     端口
     * @param protocol 协议
     */
    public MailUtil(String account, String password, String host, String port, String protocol) {
        this.account = account;
        this.password = password;
        this.host = host;
        this.port = port;
        this.protocol = protocol;
    }

    /**
     * 创建链接
     *
     * @return
     */
    public MimeMessage initMimeMessage() {
        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", protocol);
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.port", port);
        // 使用smtp身份验证
        properties.put("mail.smtp.auth", "true");
        // 开启安全协议,使用SSL,企业邮箱必需 start
        MailSSLSocketFactory mailSslSocketFactory = null;
        try {
            mailSslSocketFactory = new MailSSLSocketFactory();
            //是否信任所有主机
            mailSslSocketFactory.setTrustAllHosts(true);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.ssl.socketFactory", mailSslSocketFactory);
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.socketFactory.fallback", "false");
        properties.put("mail.smtp.socketFactory.port", port);
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(account, password);
            }
        });
        // 使用SSL,企业邮箱必需 end
        // TODO 显示debug信息 正式环境注释掉
        session.setDebug(true);
        return new MimeMessage(session);
    }

    /**
     * 发送邮件，带附件
     *
     * @param sender       发件人名称
     * @param subject      邮件主题
     * @param content      邮件内容
     * @param receiverList 接收者列表
     * @param fileSrc      附件地址
     */
    public void sendFile(String sender, String subject, String content, String receiverList, String fileSrc) {
        try {
            //初始化邮件发送类
            MimeMessage mimeMessage = initMimeMessage();
            // 发件人,可以设置发件人的别名
            mimeMessage.setFrom(new InternetAddress(account, sender));
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
            // 添加图片&附件
            bodyPart = new MimeBodyPart();
            bodyPart.attachFile(fileSrc);
            mimeMultipart.addBodyPart(bodyPart);
            mimeMessage.setContent(mimeMultipart);
            mimeMessage.saveChanges();
            Transport.send(mimeMessage);
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送邮件
     *
     * @param sender       发件人名称
     * @param subject      邮件主题
     * @param content      邮件内容
     * @param receiverList 接收者列表
     */
    public void sendMsg(String sender, String subject, String content, String receiverList) {
        try {
            //初始化邮件发送类
            MimeMessage mimeMessage = initMimeMessage();
            // 发件人,可以设置发件人的别名
            mimeMessage.setFrom(new InternetAddress(account, sender));
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
            mimeMessage.setContent(mimeMultipart);
            mimeMessage.saveChanges();
            Transport.send(mimeMessage);
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


}
