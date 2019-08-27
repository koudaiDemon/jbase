package com.cwww.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2019/4/19  13:51
 */
public class CopyOnWriteDemo {

    public static void main(String[] args) {
        final List<String> list = new CopyOnWriteArrayList<>();

        new Thread(()->{
                String uuid = UUID.randomUUID().toString();
                System.out.println("=======start=========="+uuid);
                for (String str : list) {
                    System.out.println("处理"+str);
                }
                System.out.println("=======end=========="+uuid);
        }).start();

//        new Thread(()->{
//            while (true) {
//                try {
//                    Thread.sleep(300);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                list.add("CopyOnWriteDemo2:"+System.currentTimeMillis());
//            }
//        }).start();

        new Thread(()->{
                String msg = "CopyOnWriteDemo1:"+System.currentTimeMillis();
                System.out.println("添加数据"+msg);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                list.add(msg);
            System.out.println("结束");
//                if (Thread.activeCount() == 3) {
//                    break;
//                }
        }).start();

    }

}
