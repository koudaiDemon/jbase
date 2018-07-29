package com.cwww.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * @author cWww
 * @Title
 * @Description Pipe管道，实现线程间的通信
 * @date: 2018/6/6  10:20
 */
public class Demo7 {

    public static void main(String[] args) throws Exception {

        final Pipe pipe = Pipe.open();

        new Thread( () -> {

            Pipe.SinkChannel sinkChannel = pipe.sink();
            String msg = "hello,world!";

            for (int i = 0 ; i < 10 ; i++) {
                ByteBuffer byteBuffer = ByteBuffer.allocate(48);
                byteBuffer.put((msg+i).getBytes());
                byteBuffer.flip();
                try {
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName()+":"+(msg+i));
                    sinkChannel.write(byteBuffer);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                byteBuffer.clear();
            }

        }).start();

        new Thread( () -> {
            Pipe.SourceChannel source = pipe.source();
            ByteBuffer buf = ByteBuffer.allocate(48);

            try {
                while (-1 != source.read(buf)){
                    buf.flip();
                    System.out.println(Thread.currentThread().getName()+":"+new String(buf.array(),"UTF-8"));
                    buf.clear();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }).start();

    }

}
