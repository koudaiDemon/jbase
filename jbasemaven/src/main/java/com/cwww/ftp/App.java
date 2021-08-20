package com.cwww.ftp;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.IOException;

/**
 * @author cWww
 * @Title App
 * @Description 测试FTP
 * @date: 2019/10/17  10:47
 */
@Slf4j
public class App {

    public static void main(String[] args) {

        FTPClient ftpClient=null;
        try
        {
            // 创建ftp连接对象
            ftpClient = new FTPClient();
            ftpClient.connect("106.12.52.79", 21);
            // 登陆ftp服务器
            ftpClient.login("test", "123456789");
            // 设置文件的传输类型，默认是ASCII，修改为二进制
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            // printWorkingDirectory是用户的工作目录
            final String basePath = ftpClient.printWorkingDirectory();
            log.info("==============basePath========" + basePath);
            // 切换到指定目录中，如果切换失败说明目录不存在
            final boolean boo = ftpClient.changeWorkingDirectory(basePath);
            // 如果切换路径失败，说明拼接的路径有问题，抛出异常
            if (!boo)
            {
                log.info("===========downloadall==========the directory does not exist ,"
                        + "or the user don't hava the enterence to this directory " + basePath);
                return;
            }

            // 这个方法的意思就是每次数据连接之前,ftp client告诉ftp server开通一个端口来传输数据
            ftpClient.enterLocalPassiveMode();
            // 遍历路径下的所有文件
//            final FTPFile[] fileList = ftpClient.listFiles();
//
//            if (null==fileList||0==fileList.length){
//                log.info("*******Cant find files in path"+basePath);
//            }
            log.info("Success:[{}]",ftpClient.doCommand("cp /home/test/app/index.txt /home/test/app1",""));


//            for (final FTPFile tempFile : fileList)
//            {
//                log.info("FileName:"+tempFile);
//            }
        }
        catch (final IOException e)
        {
            log.info("===========downloadall==========" + e.getMessage());
        }
        finally
        {
            // 关闭ftp连接
            if (null != ftpClient)
            {
                try
                {
                    ftpClient.disconnect();
                }
                catch (final IOException e)
                {
                    log.info("===========downloadall==========close Ftp connection error :" + e.getMessage());
                }
            }
        }

    }

}
