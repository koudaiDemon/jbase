package com.something.java8;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.BeanUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/11/6  18:36
 */
public class UserDemo {

    public static void main(String[] args) throws IOException {

        User user = new User("a","a","a");
        User user1 = new User("a","b","b");
        User user2 = new User("a","b","a");
        User user3 = new User("b","b","b");

        final List<SampleUser> users = new ArrayList<>();
        final List<User> users1 = Arrays.asList(user, user1, user2, user3);

        List<User> collect = users1.stream().filter(e -> "c".equals(e.getUsername())).collect(Collectors.toList());

        System.out.println(collect);
//        users1.forEach(e -> {
//            final SampleUser sampleUser =  new SampleUser();
//            BeanUtils.copyProperties(e,sampleUser);
//            users.add(sampleUser);
//        });

//        users1.stream().collect(Collectors.groupingBy());

//        final Map<SampleUser, List<User>> collect1 = users1.stream().collect(Collectors.groupingBy(e -> {
//            final SampleUser sampleUser = new SampleUser();
//            BeanUtils.copyProperties(e, sampleUser);
//            return sampleUser;
//        }));
//
//        System.out.println(collect1);

//        BiPredicate<User,SampleUser> predicate = (v1,v2) -> v1.getUsername().equals(v2.getUsername()) && v1.getPassword().equals(v2.getPassword()) ;
//
//        final Map<SampleUser,User> map = new HashMap<>(10);
//
//        for (int i = 0; i < users1.size() ; i++) {
//
//        }
//
//
//        Map<String, Map<String, List<User>>> collect = Stream.of(user, user1, user2, user3).collect(Collectors.groupingBy(User::getUsername, Collectors.groupingBy(User::getPassword)));
//
//        System.out.println(collect);


        List<String> list = Arrays.asList("aaa","bbb","ccc","ddd");

//        Iterator<String> iterator = list.iterator();
//
//
//        while (iterator.hasNext()) {
//            String str = iterator.next();
//            System.out.println(str);
//        }


//        Set<String> set = Collections.EMPTY_SET;
//
//        for (String str : set) {
//
//        }
        File file = new File("README.md");
//        URL resource = UserDemo.class.getResource("com/something/java8/User.java");
//        String file = resource.getFile();
        System.out.println(file.exists());
        System.out.println(file);
//        FileUtils.readFileToString(UserDemo.class.getResourceAsStream("README.md"));

    }

}
