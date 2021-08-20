package com.cwww.base;
 
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
 
public class CombinationUtil {
 
    public static Set<String> combination(List<List<String>> inputList) {
        Set<String> outSet = new HashSet<>();
        calculateCombination(inputList, 0, new String[inputList.size()], outSet);
        return outSet;
    }
 
    /**
     * 递归计算所有组合
     *
     * @param inputList  所有数组的列表，数组用List<String>存储
     * @param beginIndex 代表每一个数组的在inputList中的索引
     * @param arr        用于保存每一次递归生成的组合
     */
    public static void calculateCombination(List<List<String>> inputList, int beginIndex, String[] arr, Set<String> outSet) {
        StringBuffer bf = new StringBuffer();
        if (beginIndex == inputList.size()) {
            for (String str : arr) {
                bf.append("," + str);
            }
            outSet.add(bf.toString().replaceFirst(",", ""));
            return;
        }
 
        for (String str : inputList.get(beginIndex)) {
            arr[beginIndex] = str;
            calculateCombination(inputList, beginIndex + 1, arr, outSet);
        }
    }
 
    public static void main(String[] args) {
        List<String> list1 = new ArrayList<>();
        list1.add("0");
        list1.add("1");
        list1.add("2");
        List<String> list2 = new ArrayList<>();
        list2.add("3");
        list2.add("4");
        list2.add("5");
        List<String> list3 = new ArrayList<>();
        list3.add("6");
        list3.add("7");
        list3.add("8");
        List<List<String>> allList = new ArrayList<>();
        allList.add(list1);
        allList.add(list2);
        allList.add(list3);
        Set<String> lst = combination(allList);
        lst.forEach(str -> {
            System.out.println(str);
        });
    }
}