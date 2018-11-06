package com.cwww.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

/**
 * @author cWww
 * @Title
 * @Description ServerSocketChannel服务端编程
 * @date: 2018/6/5  18:11
 */
public class Demo6 {

    public static void main(String[] args) throws Exception {
        final ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        final ServerSocket socket = serverSocketChannel.socket();
        socket.bind(new InetSocketAddress(9999));
        serverSocketChannel.configureBlocking(false);
        //选择器
        final Selector selector = Selector.open();
        serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);
        final ByteBuffer msg = ByteBuffer.allocate(256);
        msg.put("Hi!\r\n".getBytes());
        while(true){
            try {
                selector.select();
            } catch (IOException e){
                e.printStackTrace();
                break;
            }
            final Set<SelectionKey> selectionKeys = selector.selectedKeys();
            for (SelectionKey key : selectionKeys) {
                try {
                    if (key.isAcceptable()) {
                        ServerSocketChannel server =
                                (ServerSocketChannel)key.channel();
                        SocketChannel client = server.accept();
                        client.configureBlocking(false);
                        client.register(selector, SelectionKey.OP_WRITE |
                                SelectionKey.OP_READ, msg.duplicate());
                        System.out.println(
                                "Accepted connection from " + client);
                    }
                    if (key.isWritable()) {
                        SocketChannel client =
                                (SocketChannel)key.channel();
                        ByteBuffer buffer =
                                (ByteBuffer)key.attachment();
                        while (buffer.hasRemaining()) {
                            if (client.write(buffer) == 0) {
                                break;
                            }
                        }
                        client.close();
                    }
                } catch (IOException ex) {
                    key.cancel();
                    try {
                        key.channel().close();
                    } catch (IOException cex) {
                        // 在关闭时忽略
                    }
                }
            }
        }
    }

}
