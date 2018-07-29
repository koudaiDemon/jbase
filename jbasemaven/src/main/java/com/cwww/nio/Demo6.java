package com.cwww.nio;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author cWww
 * @Title
 * @Description ServerSocketChannel服务端编程
 * @date: 2018/6/5  18:11
 */
public class Demo6 {

    public static void main(String[] args) throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.socket().bind(new InetSocketAddress(9999));
        serverSocketChannel.configureBlocking(false);
        while(true){
            SocketChannel socketChannel = serverSocketChannel.accept();

            if(socketChannel != null){
                System.out.println(socketChannel.isConnected());
            }
        }
    }

}
