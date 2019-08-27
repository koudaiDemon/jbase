package com.cwww.regex;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/10/9  14:42
 */
public class Demo {

    public static void main(String[] args) {

        Map map = new HashMap(2);
        map.put("name", "Jame Gosling");
        map.put("alias", "Rod Johnson");
        String line = "${name1} did a great job, so ${alias} did.";
        String regex = "\\$\\{[^\\}]+\\}";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(line);
        String g;
        while (m.find()) {
            g = m.group();
            g = g.substring(2,g.length()-1);
            line = m.replaceFirst(map.get(g) + "");
            m = p.matcher(line);
        }
        System.out.println(line);

    }

}
