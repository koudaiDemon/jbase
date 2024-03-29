package com.cwww.base;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cWww
 * @Title 位运算
 * @Description 计算机组成原理中的位运算
 * @date: 2018/8/10  14:30
 */
public class Demo {


    public static void main(String[] args) {
        //将一个2进制字符串变为10进制
        Integer.parseInt("",2);
        //将一个10进制数变为2进制
        Integer.toBinaryString(123);

        Long.toBinaryString(8796093087748L);

        //二进制1000
        int i1 = 8;
        //二进制1011
        int i2 = 11;
        //异或运算,如果相同则为0,不相同为1,可用于计算绝对值差,结果为3
        System.out.println(i1 ^ i2);

        //二进制1000 0001
        int j1 = 129;
        //二进制1000 0000
        int j2 = 128;
        //与运算,如果两个都为1,则为1,结果为128
        System.out.println(j1 & j2);

        //或运算,如果按位两者其中一个为1，则为1,结果为129
        System.out.println(j1 | j2);

        //二进制0010 0101
        int a = 37;
        //非运算，如果按位为0则为1,如果按位为1,则为0
        //java中int类型为8位,1位为4个字节
        //补码后为： 00000000 00000000 00000000 00100101
        //取反为：   11111111 11111111 11111111 11011010
        //因为高位是1，所以原码为负数，负数的补码是其绝对值的原码取反，末尾再加1。
        //因此，我们可将这个二进制数的补码进行还原： 首先，末尾减1得反码：11111111 11111111 11111111 11011001 其次，将各位取反得原码：
        //00000000 00000000 00000000 00100110，此时二进制转原码为38,结果为-38
        //主要是因为0算作正数
        System.out.println(~a);
    }

    public int[] twoSum(int[] nums, int target) {
        final Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            final Integer index = map.get(target - nums[i]);
            if (null != index) {
                return new int[]{index, i};
            } else {
                map.put(nums[i], i);
            }
        }
        return new int[]{-1, -1};
    }

}
