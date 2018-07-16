package com.cwww.nio;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/6/5  16:42
 */
public class Demo3 {

    public static void main(String[] args) throws Exception {

        RandomAccessFile aFile = new RandomAccessFile(new File("src/main/resources/data/nio-data.txt"), "rw");
        FileChannel inChannel = aFile.getChannel();
//         i = 10  & SelectionKey.OP_CONNECT;
        ByteBuffer head = ByteBuffer.allocate(21);
        ByteBuffer body = ByteBuffer.allocate(48);
        ByteBuffer[] buf = {head,body};
//        int bytesRead = inChannel.read(buf);
        long bytesRead = inChannel.read(buf);
        if (-1 != bytesRead) {
            System.out.println("read length:"+bytesRead);
            System.out.println("head:"+new String(head.array(),"UTF-8"));
            System.out.println("body:"+new String(body.array(),"UTF-8"));
        }
        aFile.close();

    }

}
