package com.cwww.cluster;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/8/29  17:16
 */
public class Node1 {

    public static void main(String[] args) {
        Node node = new Node();
        node.addData("hello", "world");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 使用控制台发送消息给Node2.
        Scanner scanner = new Scanner(System.in);
        while(true){
            String text = scanner.next();
            if("exit".equals(text)){
                break;
            }
            node.sendMsg(null,"hello "+text+",node2!");
        }
        Runtime.getRuntime().exit(1);
    }

}