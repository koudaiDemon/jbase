package com.something.test;

import java.util.Calendar;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/3/21  18:03
 */
public class Test {

//    public static void main(String[] args) throws Exception {
//
//
////        String url = "http://58.40.16.125:9001/bagAddrMarkGetmark";
////        String charset = "utf8";
////        String msg_type = "GETMARK";
////        String company_id = "test";
////        String key = "testkey";
////        String data = null;
////        Map<String, String> header = new HashMap();
////        Map<String, String> body = new HashMap();
////        String response = "";
////        data = JSON.toJSONString(data);
////
////        try {
////            String data_digest = DigestUtil.digest(data, key, charset);
////            header.put("x-companyId", company_id);
////            header.put("x-dataDigest", data_digest);
////            body.put("company_id", company_id);
////            body.put("msg_type", msg_type);
////            body.put("data", data);
////            response = HttpUtil.post(url, "utf8", header, body);
////            System.out.println(response);
////        } catch (Exception var12) {
////            var12.printStackTrace();
////        }
//
////        System.out.println(DigestUtil.digest("","0a6a6e78eaed","UTF-8"));
//
//        System.out.println(System.getProperty("line.separator"));
//    }

    private final static String JMX_URL = "service:jmx:rmi:///jndi/rmi://%s:%d/jmxrmi";

    public static void main(String[] args)
    {
//        //LinkedHashMap 有序
//        Map maps = new LinkedHashMap();
//        maps.put("1", "张三");
//        maps.put("2", "李四");
//        maps.put("3", "王五");
//        maps.put("4", "赵六");
//        maps.put("0", "熊大");
//        System.out.println("LinkedHashMap(有序):");
//        Iterator it = maps.entrySet().iterator();
//        while(it.hasNext())
//        {
//            Map.Entry entity = (Map.Entry) it.next();
//            System.out.println("[ key = " + entity.getKey() +
//                    ", value = " + entity.getValue() + " ]");
//        }
//        //HashMap 无序
//        Map map = new HashMap();
//        map.put("1", "张三");
//        map.put("2", "李四");
//        map.put("5", "熊二");
//        map.put("3", "王五");
//        map.put("4", "赵六");
//        map.put("0", "熊大");
//
//        map.keySet().forEach(key -> {
//            System.out.println(key+"value:"+map.get(key));
//        });
//        Long longs = 100000000L;
//        TestLong testLong = new TestLong(longs);
//        System.out.println(JSON.toJSONString(longs));

//        Date date = new Date();
//        date.setTime(1528274735116L);
//        System.out.println(date);
//        Collection<String> collection = Collections.singletonList("12313");
//        System.out.println(collection.iterator().next());
//        final String jmxURL = String.format(JMX_URL, "10.150.20.215", 9003);
//        System.out.println(jmxURL);

//        long now = System.currentTimeMillis() / 1000L;
//        long daySecond = 60 * 60 * 24;
//        long dayTime = now - (now + 8 * 3600) % daySecond;
//        Date date = new Date();
//        date.setTime(dayTime);
//        System.out.println(date);
//        Date todayTime = new Date().withTimeAtStartOfDay().toDate();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
//        calendar.add(Calendar.DAY_OF_MONTH,1);

        System.out.println(calendar.getTime().getTime());
    }

    public static class TestLong{

        private Long longs;

        public TestLong() {
        }

        public TestLong(Long longs) {
            this.longs = longs;
        }

        public Long getLongs() {
            return longs;
        }

        public void setLongs(Long longs) {
            this.longs = longs;
        }
    }


}

