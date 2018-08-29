package com.cwww.mail;

import com.sun.mail.util.MailSSLSocketFactory;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.springframework.web.bind.annotation.ModelAttribute;

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
 * @Description 两套邮件方法，一套通过jdk，一套通过apache
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
                        return new PasswordAuthentication("metepec.service@ngux.com.mx", "Rus#.H0ur!");
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
        // 邮件服务器域名smtp.office365.com
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
        mail.setStartTLSEnabled(true);
//        mail.setSSLOnConnect(true);
        // 若启用，设置smtp协议的SSL端口号
//        mail.setSslSmtpPort("994");

        // 发件人地址
        mail.setFrom("koudaidemon@163.com","koudaidemon");
//        mail.setFrom("","","");
//        抄送
//        mail.addReplyTo("metepec.service@ngux.com.mx");
        String[] toList = {"976738029@qq.com"};
        for (String to : toList) {
            // 收件人地址，可以设置多个
            mail.addTo(to);
        }
        // 邮件主题
        mail.setSubject("Éxito de las compras");
//        mail.addReplyTo("metepec.service@ngux.com.mx","servicioaexploradores@ngux.com.mx");
        String html = "<p>【Bienvenido a National Geographic Ultimate Explorer (NGUX)】Somos National Geographic Ultimate Explorer. Compraste {1} entradas para {0}.</p> <p>Tus tickets fueron procesados correctamente. Tu número de compra es: {2}</p> <p>Válido únicamente para la fecha emitida. Acude al área de ticketing del centro NGUX  y presenta el código QR o del voucher. Los nombres de los exploradores son {3}</p> <p>Código de explorador: {4}</p> <p>Nos vemos pronto.</p>";
        html = "<html><head></head><body bgcolor=\"#ffffff\"\t\t\t<table width=\"100%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"#ffffff\"<tr>\t<td>&nbsp;</td></tr><tr><td align=\"center\" valign=\"top\"><table width=\"610\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" bordercolor=\"#fff\"><tr><td align=\"center\" valign=\"top\" bgcolor=\"#FFFFFF\"><table width=\"570\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\"><tr>\t<td valign=\"middle\">&nbsp;</td></tr><tr>\t</tr><tr>\t<td height=\"30\" align=\"right\" valign=\"middle\" bgcolor=\"#000000\">\t\t\t</td></tr><tr>\t<td align=\"center\" valign=\"middle\">\t\t<font color=\"#666666\" size=\"2\" face=\"Arial, Helvetica, sans-serif\">\t\t\t<a href=\"http://localhost:9001?clear=true&site=ip2mex\" style=\"display:block; margin-top:10px;margin-bottom:10px;\"></a>\t\t</font>\t</td></tr><tr>\t<td>&nbsp;</td></tr><tr><td align=\"left\" valign=\"top\"><p style=\"margin:2px 0\"><font color=\"#666666\" size=\"2\" face=\"Arial, Helvetica, sans-serif\"><b>Estimado test001 {1}</b>,</font></p>    <p><font color=\"#666666\" size=\"2\" face=\"Arial, Helvetica, sans-serif\"><p>【Bienvenido a National Geographic Ultimate Explorer (NGUX)】Somos National Geographic Ultimate Explorer. Compraste 1 entradas para 2018-08-21.</p> <p>Tus tickets fueron procesados correctamente. Tu número de compra es: 00012003</p> <p>Válido únicamente para la fecha emitida. Acude al área de ticketing del centro NGUX  y presenta el código QR o del voucher. Los nombres de los exploradores son test001</p> <p>Código de explorador: 000000136</p> <p>Nos vemos pronto.</p></font></p>    <p><img src=\"null/qr/create?text=000000136\"/></p><p><font color=\"#666666\" size=\"2\" face=\"Arial, Helvetica, sans-serif\">Muchas gracias</font></p><p><font color=\"#666666\" size=\"2\" face=\"Arial, Helvetica, sans-serif\">Servicio al cliente</font></p></td></tr><tr>\t<td>&nbsp;</td></tr><tr>\t<td align=\"center\" valign=\"middle\">\t\t<a href=\"http://localhost:9001?clear=true&site=ip2mex\" style=\"display:block; margin-top:10px;margin-bottom:10px;\"></a>\t</td></tr><tr>\t<td height=\"30\" align=\"right\" valign=\"middle\" bgcolor=\"#000000\">\t\t\t</td></tr><tr>\t<td>&nbsp;</td></tr></table></td></tr></table></td></tr><tr>\t<td>&nbsp;</td></tr></table></body></html>";
        html = "<html><head></head><body bgcolor=\"#ffffff\"\t\t\t<table width=\"100%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"#ffffff\"<tr>\t<td>&nbsp;</td></tr><tr><td align=\"center\" valign=\"top\"><table width=\"610\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" bordercolor=\"#fff\"><tr><td align=\"center\" valign=\"top\" bgcolor=\"#FFFFFF\"><table width=\"570\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\"><tr>\t<td valign=\"middle\">&nbsp;</td></tr><tr>\t</tr><tr>\t<td height=\"30\" align=\"right\" valign=\"middle\" bgcolor=\"#000000\">\t\t\t</td></tr><tr>\t<td align=\"center\" valign=\"middle\">\t\t<font color=\"#666666\" size=\"2\" face=\"Arial, Helvetica, sans-serif\">\t\t\t<a href=\"http://localhost:9001?clear=true&site=ip2mex\" style=\"display:block; margin-top:10px;margin-bottom:10px;\"></a>\t\t</font>\t</td></tr><tr>\t<td>&nbsp;</td></tr><tr><td align=\"left\" valign=\"top\"><p style=\"margin:2px 0\"><font color=\"#666666\" size=\"2\" face=\"Arial, Helvetica, sans-serif\"><b>Estimado test001</b>,</font></p>    <p><font color=\"#666666\" size=\"2\" face=\"Arial, Helvetica, sans-serif\"><p>【Bienvenido a National Geographic Ultimate Explorer (NGUX)】Somos National Geographic Ultimate Explorer. Compraste 1 entradas para 2018-08-21.</p> <p>Tus tickets fueron procesados correctamente. Tu número de compra es: 00013001</p> <p>Válido únicamente para la fecha emitida. Acude al área de ticketing del centro NGUX  y presenta el código QR o del voucher. Los nombres de los exploradores son test001</p> <p>Código de explorador: 000011125</p> <p>Nos vemos pronto.</p></font></p>    <p><img src=\"https://ip2-web-elb-1173202444.cn-north-1.elb.amazonaws.com.cn/hepreport/qr/create?text=000011125\"/></p><p><font color=\"#666666\" size=\"2\" face=\"Arial, Helvetica, sans-serif\">Muchas gracias</font></p><p><font color=\"#666666\" size=\"2\" face=\"Arial, Helvetica, sans-serif\">Servicio al cliente</font></p></td></tr><tr>\t<td>&nbsp;</td></tr><tr>\t<td align=\"center\" valign=\"middle\">\t\t<a href=\"http://localhost:9001?clear=true&site=ip2mex\" style=\"display:block; margin-top:10px;margin-bottom:10px;\"></a>\t</td></tr><tr>\t<td height=\"30\" align=\"right\" valign=\"middle\" bgcolor=\"#000000\">\t\t\t</td></tr><tr>\t<td>&nbsp;</td></tr></table></td></tr></table></td></tr><tr>\t<td>&nbsp;</td></tr></table></body></html>";
        // 邮件正文
        mail.setHtmlMsg(html);
        // 发送邮件
        mail.send();
    }

    public static void main(String[] args) throws Exception {

//        EmailAttachment emailAttachment = new EmailAttachment();
//        File file = new File();
//        FileInputStream fileInputStream = new FileInputStream(new File(""));
        
//        DataSource dataSource = new FileDataSource(new File(""));
        apache();
    }

}
