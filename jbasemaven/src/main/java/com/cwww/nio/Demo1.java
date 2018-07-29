package com.cwww.nio;

import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;

/**
 * @author cWww
 * @Title
 * @Description nio的使用-filechannle+ByteBuffer
 * @date: 2018/6/5  10:43
 */
public class Demo1 {

    public static void main(String[] args) throws Exception {

        RandomAccessFile aFile = new RandomAccessFile(new File("src/main/resources/data/nio-data.txt"), "rw");
//        System.out.println(aFile.length());
        FileChannel inChannel = aFile.getChannel();
////        File
//
//        CharBuffer charBuffer = CharBuffer.allocate(1024);
        ByteBuffer buf = ByteBuffer.allocate(8);
//        inChannel.read(charBuffer);
        int bytesRead = inChannel.read(buf);
        while (bytesRead != -1) {

            System.out.println("Read " + bytesRead);
            buf.flip();

//            while(buf.hasRemaining()){
//                new String();
                System.out.print(new String(buf.array(),"UTF-8"));
//            }

            buf.clear();
            bytesRead = inChannel.read(buf);
        }
        aFile.close();
//        InputStream inputStream = Demo1.class.getClassLoader().getResourceAsStream("data/nio-data.txt");
//        File file = new File("src/main/java/nio/nio-data.txt");
//        System.out.println(inputStream.read());
    }

}
