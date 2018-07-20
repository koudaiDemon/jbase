package com.cwww.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/6/5  14:07
 */
public class Demo2 {

    public static void test(){

        new Thread( () -> {
            System.out.println("test Thread["+Thread.currentThread().getName()+"]");
            File file = new File("src/main/resources/data/nio-data.txt");
            BufferedInputStream inputStream = null;
            FileInputStream fileInputStream = null;
            try {
                fileInputStream = new FileInputStream(file);
                inputStream = new BufferedInputStream(fileInputStream);
                byte[] bytes = new byte[1024];
                while (-1 != inputStream.read(bytes)) {
                    System.out.println(new String(bytes,"UTF-8"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
//                System.out.println("test不关闭");
                try {
                    if (inputStream != null && fileInputStream != null) {
                        inputStream.close();
                        fileInputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("test结束");
        }).start();

    }

    public static void test1(){

        new Thread( () -> {
            System.out.println("test1 Thread["+Thread.currentThread().getName()+"]");
            File file = new File("src/main/resources/data/nio-data.txt");
            BufferedInputStream inputStream = null;
            FileInputStream fileInputStream = null;
            try {
                fileInputStream = new FileInputStream(file);
                inputStream = new BufferedInputStream(fileInputStream);
                byte[] bytes = new byte[1024];
                while (-1 != inputStream.read(bytes)) {
                    System.out.println(new String(bytes,"UTF-8"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println("test1不关闭");
//                try {
//                    if (inputStream != null && fileInputStream != null) {
//                        inputStream.close();
//                        fileInputStream.close();
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }
            System.out.println("test1结束");
        }).start();

    }

    public static void test2() {
        new Thread( () -> {
            try {
                System.out.println("test2 Thread["+Thread.currentThread().getName()+"]");
                RandomAccessFile aFile = new RandomAccessFile(new File("src/main/resources/data/nio-data.txt"), "rw");
//        System.out.println(aFile.length());
                FileChannel inChannel = aFile.getChannel();
////        File
//
//        CharBuffer charBuffer = CharBuffer.allocate(1024);
                ByteBuffer buf = ByteBuffer.allocate(48);
//        inChannel.read(charBuffer);
                int bytesRead = inChannel.read(buf);
                while (bytesRead != -1) {

                    System.out.println("Read " + bytesRead);
                    buf.flip();

//            while(buf.hasRemaining()){
//                new String();
                    System.out.print("test2"+new String(buf.array(), "UTF-8"));
//            }

                    buf.clear();
                    bytesRead = inChannel.read(buf);
                }
                aFile.close();
            } catch (Exception e){

            }
            System.out.println("test2结束");
        }).start();
    }

    public static void test3() {
        new Thread( () -> {
            try {
                System.out.println("test3 Thread["+Thread.currentThread().getName()+"]");
                RandomAccessFile aFile = new RandomAccessFile(new File("src/main/resources/data/nio-data.txt"), "rw");
//        System.out.println(aFile.length());
                FileChannel inChannel = aFile.getChannel();
////        File
//
//        CharBuffer charBuffer = CharBuffer.allocate(1024);
                ByteBuffer buf = ByteBuffer.allocate(48);
//        inChannel.read(charBuffer);
                int bytesRead = inChannel.read(buf);
                while (bytesRead != -1) {

                    System.out.println("Read " + bytesRead);
                    buf.flip();

//            while(buf.hasRemaining()){
//                new String();
                    System.out.print("test3"+new String(buf.array(), "UTF-8"));
//            }

                    buf.clear();
                    bytesRead = inChannel.read(buf);
                }
                aFile.close();
            } catch (Exception e){

            }
        }).start();
    }



    public static void main(String[] args) throws Exception {
//        test();
        test2();//nio
//        test();//io
        test3();//nio
    }

}
