package com.cwww.base;

import java.io.*;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2019/3/25  19:28
 */
public class IODemo {

    public static void main(String[] args) {

        BufferedReader bufferedReader = null;
        try {
            bufferedReader  = new BufferedReader(new FileReader(new File("D:\\个人文档\\github\\jbase\\jbasemaven\\src\\main\\java\\com\\cwww\\base\\Demo.java")));
            String content = "";
            while (null != (content = bufferedReader.readLine())) {
                System.out.println(content);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != bufferedReader) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
