package com.cwww.nio;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Set;

/**
 * @author cWww
 * @Title
 * @Description nio-Selector,网络编程，可以用于将一个selector注册到多个管道(并指定监控的类型)，实现对多个管道的监控
 * @date: 2018/6/5  18:00
 */
public class Demo5 {

    public static void main(String[] args) throws Exception {

        Selector selector = Selector.open();
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress(9999));
        socketChannel.configureBlocking(false);
        SelectionKey key = socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);

//        socketChannel.read(ByteBuffer.allocate(48));

        while (true) {
            if (0 == selector.select()) {
                continue;
            }
            Set<SelectionKey> set = selector.keys();
            set.forEach(e -> {
                if (e.isAcceptable()) {
                    System.out.println("接收");
                }
                if (e.isConnectable()) {
                    System.out.println("链接");
                }
                if (e.isReadable()) {
                    System.out.println("可读");
                }
                if (e.isWritable()) {
                    System.out.println("可写");
                }
            });
        }

//        RandomAccessFile aFile = new RandomAccessFile(new File("src/main/resources/data/nio-data.txt"), "rw");
//        FileChannel inChannel = aFile.getChannel();
//        Selector selector = Selector.open();
//        ServerSocket serverSocket = new ServerSocket(12345);
//        ServerSocketChannel serverSocketChannel = serverSocket.getChannel();
//        SelectionKey key = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT | SelectionKey.OP_CONNECT);
//
//        while (true) {
//            if (0 == selector.select()) {
//                continue;
//            }
//            Set<SelectionKey> set = selector.keys();
//            set.forEach(e -> {
//                if (e.isAcceptable()) {
//                    System.out.println("接收");
//                }
//                if (e.isConnectable()) {
//                    System.out.println("链接");
//                }
//            });
//        }

//        inChannel.
//         i = 10  & SelectionKey.OP_CONNECT;
//        ByteBuffer head = ByteBuffer.allocate(21);
//        ByteBuffer body = ByteBuffer.allocate(48);
//        ByteBuffer[] buf = {head,body};
////        int bytesRead = inChannel.read(buf);
//        long bytesRead = inChannel.read(buf);
//        if (-1 != bytesRead) {
//            System.out.println("read length:"+bytesRead);
//            System.out.println("head:"+new String(head.array(),"UTF-8"));
//            System.out.println("body:"+new String(body.array(),"UTF-8"));
//        }
//        aFile.close();

    }

}
