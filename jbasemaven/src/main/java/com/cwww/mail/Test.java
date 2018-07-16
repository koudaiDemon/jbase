package com.cwww.mail;

import com.sun.mail.util.MailSSLSocketFactory;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/6/28  15:27
 */
public class Test {

    public static void sendEmail() {

        // Create object of Property file
        Properties props = new Properties();

        // this will set host of server- you can change based on your requirement
        props.put("mail.smtp.host", "smtp.office365.com");
        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.debug", "true");

        // set the port of socket factory
        props.put("mail.smtp.socketFactory.port", "587");
//        props.put("mail.smtp.socketFactory.fallback","true");

        // set socket factory
//        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");

        // set the authentication to true
        props.put("mail.smtp.auth", "true");

        // set the port of SMTP server
        props.put("mail.smtp.port", "587");

//        MailSSLSocketFactory sf = null;
//        try {
//            sf = new MailSSLSocketFactory();
//        } catch (GeneralSecurityException e) {
//            e.printStackTrace();
//        }
//        sf.setTrustAllHosts(true);
//        props.put("mail.smtp.ssl.socketFactory", sf);

        // This will handle the complete authentication
        Session session = Session.getDefaultInstance(props,

                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("metepec.service@ngux.com.mx", "");
                    }
                });

        try {

            // Create object of MimeMessage class
            Message message = new MimeMessage(session);

            // Set the from address
            message.setFrom(new InternetAddress("metepec.service@ngux.com.mx"));

            // Set the recipient address
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("wei.cai@hand-china.com"));

            // Add the subject link
            message.setSubject("Testing Subject");

            // Create object to add multimedia type content
            BodyPart messageBodyPart1 = new MimeBodyPart();

            // Set the body of email
            messageBodyPart1.setText("This is message body");

            // Create object of MimeMultipart class
            Multipart multipart = new MimeMultipart();

            // add body part 2
            multipart.addBodyPart(messageBodyPart1);

            // set the content
            message.setContent(multipart);

            // finally send the email
            Transport.send(message);

            System.out.println("=====Email Sent=====");

        } catch (MessagingException e) {

            throw new RuntimeException(e);

        }

    }

    public static void apache() throws Exception {
        HtmlEmail mail = new HtmlEmail();
        // 邮件服务器域名
        mail.setHostName("smtp.163.com");
        // 邮件服务器smtp协议端口
        mail.setSmtpPort(25);
        // 邮箱账户
        mail.setAuthentication("15527165793", "");
        // 邮件的字符集
        mail.setCharset("UTF-8");
//        Properties properties = new Properties();
//        properties.put("mail.smtp.socketFactory.fallback", "true");
//        mail.setStartTLSEnabled(true);
        // 是否启用SSL
        mail.setSSLOnConnect(true);
        // 若启用，设置smtp协议的SSL端口号
        mail.setSslSmtpPort("994");

        // 发件人地址
        mail.setFrom("koudaidemon@163.com");
//        抄送
//        mail.addReplyTo("metepec.service@ngux.com.mx");
        String[] toList = {"wei.cai@hand-china.com"};
        for (String to : toList) {
            // 收件人地址，可以设置多个
            mail.addTo(to);
        }
        // 邮件主题
        mail.setSubject("test0001");
        String html = "<h1>test0628</h1>";
        // 邮件正文
        mail.setHtmlMsg(html);
        // 发送邮件
        mail.send();
    }

    public static void main(String[] args) throws Exception {

        EmailAttachment emailAttachment = new EmailAttachment();
//        File file = new File();
        FileInputStream fileInputStream = new FileInputStream(new File(""));
        
        DataSource dataSource = new FileDataSource(new File(""));

    }

}
