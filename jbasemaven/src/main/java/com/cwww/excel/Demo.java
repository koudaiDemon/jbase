package com.cwww.excel;

import org.apache.commons.mail.HtmlEmail;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import java.io.*;
import java.util.Properties;

/**
 * @author cWww
 * @Title
 * @Description 通过poi实现excel的创建，并发送邮件附件
 * @date: 2018/7/12  12:26
 */
public class Demo {

    public static void apache(DataSource dataSource) throws Exception {

//        EmailAttachment attachment = new EmailAttachment();
//        attachment.setPath("poi.xls");
//        attachment.setDisposition(EmailAttachment.ATTACHMENT);
//        attachment.setDescription("poi.xls报表文件");
//        attachment.setName("poi.xls");

        HtmlEmail mail = new HtmlEmail();
        // 邮件服务器域名
        mail.setHostName("smtp.163.com");
        // 邮件服务器smtp协议端口
        mail.setSmtpPort(25);
        // 邮箱账户
        mail.setAuthentication("15527165793", "caiwei1213");
        // 邮件的字符集
        mail.setCharset("UTF-8");
        // 是否启用SSL
//        mail.setSSLOnConnect(true);
        // 若启用，设置smtp协议的SSL端口号
//        mail.setSslSmtpPort("994");

        mail.setStartTLSEnabled(true);
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
        mail.setSubject("test1111");
        String html = "<h1 style='color:rgb(236,25,27)'>test0712</h1>";
        // 邮件正文
//        mail.setHtmlMsg(html);
        mail.setHtmlMsg(html);

        //添加附件
        mail.attach(dataSource,"poi.xls","报表附件");
//        mail.attach()
        // 发送邮件
        mail.send();
    }

    public static void send (DataSource dataSource) {
        // Create object of Property file
        Properties props = new Properties();

        // this will set host of server- you can change based on your requirement
        props.put("mail.smtp.host", "smtp.163.com");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        // set the port of socket factory
//        props.put("mail.smtp.socketFactory.port", "587");
//        props.put("mail.smtp.socketFactory.fallback","true");

        // set socket factory
//        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");

        // set the authentication to true
        props.put("mail.smtp.auth", "true");

        // set the port of SMTP server
        props.put("mail.smtp.port", "25");

//        MailSSLSocketFactory sf = null;
//        try {
//            sf = new MailSSLSocketFactory();
//        } catch (GeneralSecurityException e) {
//            e.printStackTrace();
//        }
//        sf.setTrustAllHosts(true);
//        props.put("mail.smtp.ssl.socketFactory", sf);

        // This will handle the complete authentication
        Session session = Session.getInstance(props,

                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("15527165793", "");
                    }
                });

        try {

            // Create object of MimeMessage class
            Message message = new MimeMessage(session);

            // Set the from address
            message.setFrom(new InternetAddress("wei.cai@123.com"));

            // Set the recipient address
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("976738029@qq.com"));

            // Add the subject link
            message.setSubject("Testing Subject");

            // Create object to add multimedia type content
            MimeBodyPart attach = new MimeBodyPart();
            MimeBodyPart htmlMsg = new MimeBodyPart();

//            MimeMultipart msgBodyMultipart  = new MimeMultipart("related");

            // Set DataHandler
            DataHandler dataHandler = new DataHandler(dataSource);
            attach.setDataHandler(dataHandler);
            attach.setFileName("poi.xls");
            // Set the body of email
            htmlMsg.setText("This is message body");
//            htmlMsg.setContent(msgBodyMultipart);
            htmlMsg.setContent("<span style='color:red'>中文呵呵</span><h1>你们好!!</h1>","text/html;charset=utf-8");
            // Create object of MimeMultipart class
            Multipart multipart = new MimeMultipart();

            // add body msg
            multipart.addBodyPart(htmlMsg);

            // add body attach part 2
            multipart.addBodyPart(attach);

            // set the content
            message.setContent(multipart);

            //保存改变
            message.saveChanges();

            // finally send the email
            Transport.send(message);

            System.out.println("=====Email Sent=====");

        } catch (MessagingException e) {

            throw new RuntimeException(e);

        }
    }

    public static void main(String[] args) throws Exception {

        // 创建工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建工作表
        HSSFSheet sheet = workbook.createSheet("sheet1");

        for (int row = 0; row < 5; row++)
        {
            HSSFRow rows = sheet.createRow(row);
            for (int col = 0; col < 5; col++)
            {
                // 向工作表中添加数据
                rows.createCell(col).setCellValue("data数据" + row + col);
            }
        }

//        File xlsFile = new File("poi.xls");
//        RandomAccessFile randomAccessFile = new RandomAccessFile(xlsFile,"rw");
//        FileChannel fileChannel = randomAccessFile.getChannel();
//        fileChannel.
//        BufferedInputStream bufferedInputStream = new BufferedInputStream(new );

//        byte[] bytes = workbook.getBytes();

//        workbook.write();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        outputStream.flush();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());

//        FileInputStream fileInputStream = new FileInputStream(xlsFile);
        DataSource dataSource = new ByteArrayDataSource(inputStream,"application/vnd.ms-excel");
//        System.out.println(dataSource.getName());
//        FileOutputStream xlsStream = new FileOutputStream(xlsFile);
//        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream();
//        workbook.write(xlsStream);
//        fileInputStream.flush();
//        fileInputStream.close();
        Demo.apache(dataSource);


//        String type = null;
//        Path path = Paths.get("poi.xls");
//        try {
//            type = Files.probeContentType(path);
//            System.out.println(type);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        System.out.println(xlsFile.delete());
    }

}
