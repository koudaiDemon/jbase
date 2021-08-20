package com.something.jol;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 快速测试JOL
 *
 * @author wei.cai@hand-china.com 2021/4/13
 */
@Slf4j
public class QuickTest {

    static Object generate() {
        Map<String, Object> map = new HashMap<>(32);
        map.put("a", new Integer(1));
        map.put("b", "b");
        map.put("c", new Date());

        for (int i = 0; i < 10; i++) {
            map.put(String.valueOf(i), String.valueOf(i));
        }
        return map;
    }

    public static void main(String[] args) {

        Object obj = generate();
//        ThreadLocal

        log.info("obj, ClassLayout:[{}]", ClassLayout.parseInstance(obj).toPrintable());

        synchronized (obj) {
            try {
                log.info("obj, ClassLayout:[{}]", ClassLayout.parseInstance(obj).toPrintable());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                log.error("InterruptedException", e);
            }
        }
        log.info("obj, ClassLayout:[{}]", ClassLayout.parseInstance(obj).toPrintable());
//        log.info("obj, GraphLayout:[{}]", GraphLayout.parseInstance(obj).toPrintable());
//        log.info("obj, GraphLayout totalSize:[{}]", GraphLayout.parseInstance(obj).totalSize());



    }

}
