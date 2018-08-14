package com.cwww.nio.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author cWww
 * @Title 文件拷贝
 * @Description 通过JDK7
 * @date: 2018/8/14  14:19
 */
public class Demo {

    public static void main(String[] args) throws Exception {

        URI uri = URI.create("file:///D:/ip2/index.html");
        Files.copy(Paths.get(uri),new FileOutputStream(new File("D:/test.html")));

    }

}
