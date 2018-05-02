package com.mmall.util;

import com.mmall.beans.Mail;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MailUtil {

    private static Logger log = LoggerFactory.getLogger(IpUtil.class);

    private static Properties properties = new Properties();

    public static boolean send(Mail mail) {
        HtmlEmail email = new HtmlEmail();
        InputStream inputStream = MailUtil.class.getResourceAsStream("/mail.properties");
        String from = "";
        int port;
        String host;
        String password;
        String nickname;
        try {
            properties.load(inputStream);
            from = properties.getProperty("from");
            port = Integer.parseInt(properties.getProperty("port"));
            host = properties.getProperty("host");
            password = properties.getProperty("password");
            nickname = properties.getProperty("nickname");

            email.setHostName(host);
            email.setCharset("UTF-8");
            email.addTo(mail.getReceiver());
            email.setFrom(from, nickname);
            email.setSmtpPort(port);
            email.setAuthentication(from, password);
            email.setSubject(mail.getSubject());
            email.setMsg(mail.getMessage());
            email.send();
            log.info("{} 发送邮件到 {}", from, StringUtils.join(mail.getReceiver(), ","));
            return true;
        } catch (EmailException e) {
            log.error(from + "发送邮件到" + StringUtils.join(mail.getReceiver(), ",") + "失败", e);
            return false;
        } catch (IOException e) {
            log.error("获取邮箱配置文件失败" + e);
            return false;
        }
    }

    public static void main(String[] args) {
        Mail mail = new Mail("测试", "测试邮件发送", "847135055@qq.com");
        send(mail);
    }

}

