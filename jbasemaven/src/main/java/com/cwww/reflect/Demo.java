package com.cwww.reflect;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/10/9  16:07
 */
public class Demo {

    public static void main(String[] args) {

//        final List<String> list = new ArrayList<>();
//        list.add("order.address.code");
//        list.add("order.code");
//
//        final Address address = new Address("123","xiangxi");
//        final Order order = new Order("c-123","o-detail",address);
//        final Map<String,Object> map = new HashMap<>(3);
//        map.put("order",order);
//        final Map<String,String> info = new HashMap<>(20);
//
//
//        for (String str : list) {
//            String[] split = str.split("\\.");
//            if (split.length <= 1) {
//                if (null != map.get(str)) {
//                    info.put(str,map.get(str)+"");
//                }
//                break;
//            }
//            Object obj = null;
//            for (int i = 0 ; i < split.length ; i++) {
//                if (i == 0) {
//                    obj = map.get(split[i]);
//                } else {
//                    if (null == obj) {
//                        info.put(str,"");
//                        break;
//                    }
//                    Class aClass = obj.getClass();
//                    String method = "get"+split[i].substring(0,1).toUpperCase()+split[i].substring(1);
//
//                    Method declaredMethod = null;
//                    try {
//                        declaredMethod = aClass.getDeclaredMethod(method);
//                        obj = declaredMethod.invoke(obj);
//                    } catch (NoSuchMethodException e) {
//                        e.printStackTrace();
//                    } catch (IllegalAccessException e) {
//                        e.printStackTrace();
//                    } catch (InvocationTargetException e) {
//                        e.printStackTrace();
//                    }
//                }
//                if (i == split.length-1) {
//                    if (null == obj) {
//                        info.put(str,"");
//                    } else {
//                        info.put(str,obj+"");
//                    }
//                }
//            }
//
//        }
//
//        System.out.println(info);

//        String str = "\"abc\"";

//        System.out.println(StringEscapeUtils.escapeJava(str));

//        String str = null;
//
//        try {
//
//            if (StringUtils.isEmpty(str)) {
//                throw new NullPointerException("aabbbbbbb");
//            }
//            System.out.println("asdsadasdad");
//        } finally {
//            System.out.println("finally");
//        }

        Map<String,List<String>> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        list.add("list1");
        list.add("list2");
        list.add("list3");
        map.put("key",list);


        List<String> list1 = new ArrayList<>(map.get("key"));
        list1.remove(1);


        System.out.println(list1);

        System.out.println(map);
//
//        Class<? extends Order> orderClass = order.getClass();

//        Method declaredMethod = orderClass.getDeclaredMethod("");


    }


    private static Map<String,String> getDetail(List<String> list,Map<String,Object> map){

        final Map<String,String> info = new HashMap<>(20);

        for (String str : list) {
            String[] split = str.split("\\.");
            if (split.length <= 1) {
                if (null != map.get(str)) {
                    info.put(str,map.get(str)+"");
                }
                break;
            }
            Object obj = null;
            for (int i = 0 ; i < split.length ; i++) {
                if (i == 0) {
                    obj = map.get(split[i]);
                } else {
                    if (null == obj) {
                        info.put(str,"");
                        break;
                    }
                    Class aClass = obj.getClass();
                    String method = "get"+split[i].substring(0,1).toUpperCase()+split[i].substring(1);

                    Method declaredMethod = null;
                    try {
                        declaredMethod = aClass.getDeclaredMethod(method);
                        obj = declaredMethod.invoke(obj);

                    } catch (NoSuchMethodException e) {
                        throw new RuntimeException("No Such Method["+method+"] For "+aClass.getName()+",Please Check It");
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException("Con't Access For Method["+method+"]");
                    } catch (InvocationTargetException e) {
                        throw new RuntimeException("Con't Invocation Target Method["+method+"]");
                    }
                }
                if (i == split.length-1) {
                    if (null == obj) {
                        info.put(str,"");
                    } else {
                        info.put(str,obj+"");
                    }
                }
            }
        }
        return info;
    }


}
