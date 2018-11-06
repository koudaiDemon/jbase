package com.cwww.nio.demo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Set;

/**
 * description:
 * nioSocket客户端
 * @author cWww
 */
public class NioSocketClient {

    public void start() {
        try (SocketChannel socketChannel = SocketChannel.open()) {
            //连接服务端socket
            SocketAddress socketAddress = new InetSocketAddress("localhost", 8888);
            socketChannel.connect(socketAddress);

            int sendCount = 0;

//            ByteBuffer buffer = ByteBuffer.allocate(1024);

            //这里最好使用selector处理   这里只是为了写的简单
            Selector selector = Selector.open();
            socketChannel.configureBlocking(false);
            socketChannel.register(selector,SelectionKey.OP_CONNECT|SelectionKey.OP_WRITE|SelectionKey.OP_READ);
            while (sendCount < 10) {

                int count = selector.select();
                System.out.println(count);
                final Set<SelectionKey> keys = selector.keys();
                for (SelectionKey key : keys) {
                    if (key.isWritable()) {
//                        buffer.clear();
                        //向服务端发送消息
//                        buffer.put(("current time : " + System.currentTimeMillis()).getBytes());
                        //读取模式
//                        buffer.flip();
                        key.attach("now time:"+System.currentTimeMillis());
//                        socketChannel.write(buffer);
//                        buffer.clear();
                    }
                    if (key.isReadable()) {
                        System.out.println(key.attachment());
                    }

                    if (key.isConnectable()) {
                        System.out.println("连接");
                        key.attach("now time:"+System.currentTimeMillis());
                    }

                }


//                //从服务端读取消息
//                int readLenth = socketChannel.read(buffer);
//                //读取模式
//                buffer.flip();
//                byte[] bytes = new byte[readLenth];
//                buffer.get(bytes);
//                System.out.println(new String(bytes, "UTF-8"));
//                buffer.clear();


                sendCount++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
