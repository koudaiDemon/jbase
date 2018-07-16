package com.cwww.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.Comparator;
import java.util.List;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/5/23  9:39
 */
public class Test {

    public static void main(String[] args) {



        ApplicationContext ac = new FileSystemXmlApplicationContext("src/main/resources/spring.xml");
//        ApplicationContext ac = new FileSystemXmlApplicationContext();
//        TestList testList = (TestList)ac.getBean("testList");
//        List<Action> list = testList.getList();
//        list.sort(Comparator.comparing(Action::getLevel));
//        list.forEach(action -> {
//            System.out.println(action.getLevel()+"======"+action.getName());
//        });
        JobDemo jobDemo = ac.getBean("job",JobDemo.class);
        jobDemo.showInfo();
//        System.out.println(Item.SPRING.equals(Item.WINTER));

    }

}
