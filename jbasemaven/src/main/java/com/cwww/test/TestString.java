package com.cwww.test;

import sun.util.locale.BaseLocale;

import java.text.MessageFormat;
import java.util.Locale;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/7/3  9:47
 */
public class TestString {

    /**
     * 测试格式化
     */
    public void test(){
        //用来测试str的format
        String str = "hello,{0},{1},{2}";
        str = String.format(str,"world",123,false);
        System.out.println(str);

        String nullStr = null;
        System.out.println(nullStr == null);
    }

    /**
     * 测试两种字符串格式化的时间
     */
    public void test1(){
        long start = System.currentTimeMillis();
        for (int i = 0 ; i < 1000 ; i++) {
            String str = "hello,{0},{1},{2}";
            //1.使用messageFormat进行格式化
//            MessageFormat.format(str,"world",123,false);
            //2.使用替换进行格式化
            str.replace("{0}","world");
            str.replace("{1}","123");
            str.replace("{2}","false");
        }
        long end = System.currentTimeMillis();
        System.out.println("times:"+(end - start));
    }

    /**
     * 测试国际化，主要测试墨西哥
     */
    public void test2(){
        Locale locale = new Locale("es","ES");
        System.out.println(locale);
        System.out.println(Locale.CHINA);
        System.out.println(Locale.CHINESE);
    }

    public static void main(String[] args) {

//        String str = "{0}1233456";
//        str.replace("{0}","666");
//        System.out.println(str);

//        System.out.println("\\\"".length());
//        System.out.println("\\\"".charAt(1));
//        System.out.println("\\\"".replace('\\','/'));
//
//        String[] str = { "bamboo_", "cactus_", "circuit_", "clouds_", "crops_","forest_","gears_","giraffe_","grass_","leafs_","leopard_","moon_","mountains_","palm_trees_","reeds_","reptile_","rockets_","scales_","space_","sun_","trees_","underwater_","volcano_","water_","waves_" };
//
//        System.out.println(str.length);
//        BaseLocale base = BaseLocale.createInstance("es","ES");

//        Locale.LocaleKey key = new Locale.LocaleKey(baseloc, extensions);

//        Locale.get

        new TestString().test2();

    }

}
