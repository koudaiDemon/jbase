package com.cwww.base;

/**
 * Description
 *
 * @author wei.cai@hand-china.com 2021/2/23
 */
public class TryCatchDemo {

    public static void main(String[] args) {
        try {
            System.out.println("cccc");

            if (true) {

                return;
            }
            System.out.println("bbbb");

        } catch (Exception e) {

        } finally {
            System.out.println("aaaaaa");
        }
    }

}
