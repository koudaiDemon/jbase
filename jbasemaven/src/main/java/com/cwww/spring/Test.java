package com.cwww.spring;


import com.cwww.spring.ListDemo.App;
import com.cwww.spring.ListDemo.ListDemo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/5/23  9:39
 */
public class Test {

    public static void main(String[] args) {


        ApplicationContext ac = new FileSystemXmlApplicationContext("jbasemaven/src/main/resources/spring.xml");
        ((FileSystemXmlApplicationContext) ac).start();

        final App bean = ac.getBean(App.class);
        bean.hello();

        //        List listDemo = ac.getBean("listDemo2", List.class);
//        final ListDemo bean = ac.getBean(ListDemo.class);
//        System.out.println(bean);

//        final Object demo = ac.getBean("ListDemo");
//        System.out.println(demo);
        //        List<Action> list = testList.getList();
//        list.sort(Comparator.comparing(Action::getLevel));
//        list.forEach(action -> {
//            System.out.println(action.getLevel()+"======"+action.getName());
//        });
//        JobDemo jobDemo = ac.getBean("job",JobDemo.class);
//        jobDemo.showInfo();
//        System.out.println(Item.SPRING.equals(Item.WINTER));

//        JobDemo jobDemo = new JobDemo(new Demo("hello"));
//        System.out.println(jobDemo instanceof AbstractJob);




    }

}
