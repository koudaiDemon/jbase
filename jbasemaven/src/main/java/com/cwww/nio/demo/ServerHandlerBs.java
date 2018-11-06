package com.cwww.nio.demo;

import java.io.IOException;
import java.nio.channels.SelectionKey;

/**
 * description:
 * 对selectionKey事件的处理
 * @author cWww
 */
public interface ServerHandlerBs {

    /**
     * 处理接收
     * @param selectionKey
     * @throws IOException
     */
    void handleAccept(SelectionKey selectionKey) throws IOException;

    /**
     * 处理读取
     * @param selectionKey
     * @return
     * @throws IOException
     */
    String handleRead(SelectionKey selectionKey) throws IOException;
}
