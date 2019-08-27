package com.cwww.nio.demo;

/**
 * description:
 * 对selectionKey事件的处理
 * @author cWww
 */
public class ServerMain {
    public static void main(String[] args) {
        NioSocketServer server = new NioSocketServer();
        new Thread(() -> {
            try {
                Thread.sleep(10*60*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                server.setFlag(false);
            }
        }).start();
        server.start();
    }
}
