package com.something.size;

import com.something.test.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.util.RamUsageEstimator;

/**
 * Description
 *
 * @author wei.cai@hand-china.com 2020/8/18
 */
@Slf4j
public class ObjectSizeDemo {

    public static void main(String[] args) {
        final User string = new User("aaaaaaaa","bbbbbbbbbb");
//        final String string = new String("aaaaaa");
        final long str = RamUsageEstimator.sizeOfObject(string);
        final long str1 = RamUsageEstimator.shallowSizeOf(string);
        final String str2 = RamUsageEstimator.humanReadableUnits(str1);


        log.info("size1:[{}]", str);
        log.info("size2:[{}]", str1);
        log.info("size3:[{}]", str2);


    }


}
