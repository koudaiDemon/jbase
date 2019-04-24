package com.cwww.base;

import com.alibaba.fastjson.JSON;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/8/16  12:13
 */
public class Demo1 {

    private static final String REGEX ="<p.*?>(.*?)</p>";

    public static void main(String[] args) {

//        Map<String,String> map = new HashMap<>(10);

//        List<Product> list = new ArrayList<>();
//        Product product = new Product("123",null);
//        for (int i = 0 ; i < 3 ; i++) {
//            product.setCode("code-"+i);
//            list.add(product);
//        }
//        System.out.println(JSON.toJSONString(list));
//        list.add(product);
//        map.put("123","123");
//        Locale locale = new Locale("es");
//
//        Locale locale1 = Locale.forLanguageTag("es");
//        System.out.println(locale1);
//        System.out.println(locale);
//        for (int i = 0 ; i <= 3 ;i++){
//            Product product = new Product();
//            map.put("local_"+product.hashCode(),"value_"+product.hashCode());
//            product.setCode("code-"+i);
//            product.setLocalName(map);
//            list.add(product);
//        }

//        System.out.println(product);

//        List<String> list = Collections.singletonList("hello");
//        String next = list.iterator().next();
//        System.out.println(next);

//        System.out.println(addressResolution("湖北省武汉市洪山区"));
//        System.out.println(addressResolution("北京北京市海淀区西土城路十号北京邮电大学"));
//        Boolean flag = null;
//        System.out.println((true
//                && !flag));
//        System.out.println(String.format("%.2f",10.0022));

//        List<String> list = Arrays.asList("1002","1003");

//        System.out.println(list.stream().anyMatch(e -> "1002".equals(e)));

//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

//        System.out.println(sdf.format(null));
//        BigDecimal bigDecimal = BigDecimal.valueOf(100);
//        System.out.println(bigDecimal.multiply(BigDecimal.TEN));
//        System.out.println(bigDecimal);
//        String regex = "(?i)^https?://dms.yuyue.com.cn(:[\\d]+)?.*";
//        System.out.println(regex);
//        final Pattern compile = Pattern.compile(regex);
//        final Matcher matcher = compile.matcher("https://dms.yuyue.com.cn/login");
//        while () {
//
//        }
//        System.out.println(matcher.groupCount());
//        System.out.println(matcher.find());
//        System.out.println(matcher.group(0));
//        System.out.println(matcher.groupCount());

        System.out.println(StringUtils.join(new ArrayList(),","));
    }

    /**
     * 解析地址
     * @author lin
     * @param address
     * @return
     */
    public static List<Map<String,String>> addressResolution(String address){
        String regex="((?<province>[^省]+省|.+自治区)|上海|北京|天津|重庆)(?<city>[^市]+市|.+自治州)(?<county>[^县]+县|.+区|.+镇|.+局)?(?<town>[^区]+区|.+镇)?(?<village>.*)";
//        String regex = "(?<province>[^省]+自治区|.*?省|.*?行政区|.*?市)(?<city>[^市]+自治州|.*?地区|.*?行政单位|.+盟|市辖区|.*?市|.*?县)(?<county>[^县]+县|.+区|.+市|.+旗|.+海域|.+岛)?(?<town>[^区]+区|.+镇)?(?<village>.*)";
//        regex = "(?<province>[^省]+自治区|.*?省|.*?行政区|.*?市)(?<city>[^市]+自治州|.*?区|.*?行政单位|.+盟|市辖区|.*?市|.*?县)(?<county>[^县]+县|.+区|.+市|.+旗|.+海域|.+岛)?(?<town>[^区]+区|.+镇)?(?<village>.*)";
        Matcher m = Pattern.compile(regex).matcher(address);
        String province = null,city = null,county = null,town = null,village = null;
        List<Map<String,String>> table = new ArrayList<Map<String,String>>();
        Map<String,String> row = null;
        while(m.find()){
            row = new LinkedHashMap<String,String>();
            province = m.group("province");
            row.put("province", province==null?"":province.trim());
            city = m.group("city");
            row.put("city", city==null?"":city.trim());
            county = m.group("county");
            row.put("county", county==null?"":county.trim());
            town = m.group("town");
            row.put("town", town==null?"":town.trim());
            village = m.group("village");
            row.put("village", village==null?"":village.trim());
            table.add(row);
        }
        return table;
    }


    public void test(){
        System.out.println("<p>【Bienvenido a National Geographic Ultimate Explorer (NGUX)】Somos National Geographic Ultimate Explorer. Compraste {1} entradas para {0}.</p><p>Tus tickets fueron cancelados. Tu número de compra es: {3},</p><p>Los exploradores son {4},Numero de cancelación : {5}.</p><p>e esperamos pronto.</p>".length());

        System.out.println("<p>【国家地理探险家中心】您购买用于{0}日门票{1}张，</p> <p>已出票成功。订单号为：{2}，</p> <p>游客姓名{3}。仅限当天使用，请到游客中心凭二维码或入园凭证换票入园。</p> <p>入园凭证码:{4}</p>".length());

        String str = "<table width=\\\"100%\\\" border=\\\"0\\\" cellspacing=\\\"0\\\" cellpadding=\\\"0\\\"><tr><td><p><img src=\\\"https://img.alicdn.com/imgextra/i1/196993935/TB2DaldXRYxQeBjSszeXXa0spXa_!!196993935.jpg\\\" alt=\\\"初上市价格\\\" width=\\\"100%\\\"  /></p><p>99元<br />  ※初上市价格是指商品首次在优衣库官方旗舰店销售时的价格。</p><p><img src=\\\"https://img.alicdn.com/imgextra/i3/196993935/TB20HVcXPzyQeBjy0FjXXc5CVXa_!!196993935.jpg\\\" alt=\\\"产品说明\\\" width=\\\"100%\\\" /></p><p>质地略显厚实的棉质T恤，适合单穿。条纹图案，颇具季节感。<br>·采用质地结实的棉质平纹汗布制成，即使单穿也不会过于通透。<br>·船领的设计，有助于展现修长的颈部线条。<br>·略显宽松的直筒剪裁是其魅力所在。<br>·设计简约，搭配性出众。</p><p><br />【面料组成】棉100％。<br />【洗涤信息】机洗</p>   <p style=\\\"color: #F00\\\">此商品在商品完好，符合相关退换货规则的前提下支持七天无理由退换货。</p></tr></table>";
        System.out.println(str);

        System.out.println(StringEscapeUtils.unescapeJava(str));
    }

    public static List<String> getDescTest (String sourceDescription) {
        //1、共3个Context，取第一个Context内容
        //2、用正则表达式取出<p>标签的内容
        //3、如果不是婴儿类型的：数组下标为1的赋值给初上市价格、下标为3到最后赋值给产品说明   (业务说模板是固定的，后期有修改的话再修改这里吧。)
        //     如果是婴儿类型的: 也就是包含<a href>的,数组下标为1的赋值给自定义defined、下标为3的赋值给初上市价格、下标为5到最后的赋值给产品说明
        List<Map<String,String>> productDescDataList = null;
//        List<String> list = JSON.parseArray(sourceDescription, );
        sourceDescription = productDescDataList.get(0).get("Content");

        List<String> source = new ArrayList<>();
        List<String> description = new ArrayList<>();

        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(sourceDescription);
        StringBuilder defined = new StringBuilder();
        StringBuilder priceTag = new StringBuilder();
        StringBuilder descContain = new StringBuilder();

        while(matcher.find()){
            source.add(matcher.group(1));
        }

        if (sourceDescription.contains("<a href")) {
            defined.append(source.get(1));
            priceTag.append(source.get(3));
            for (int i=5;i<source.size();i++) {
                descContain.append(source.get(i));
            }
        } else {
            defined.append("");
            priceTag.append(source.get(1));
            for (int i=3;i<source.size();i++) {
                descContain.append(source.get(i));
            }
        }

        description.add(defined.toString());
        description.add(priceTag.toString());
        description.add(descContain.toString());
        return description;
    }

    public static class Product{

        private String code;
        private Map<String,String> localName;


        public Product() {
        }

        public Product(String code, Map<String, String> localName) {
            this.code = code;
            this.localName = localName;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public Map<String, String> getLocalName() {
            return localName;
        }

        public void setLocalName(Map<String, String> localName) {
            this.localName = localName;
        }

        @Override
        public String toString() {
            return "Product{" +
                    "code='" + code + '\'' +
                    ", localName=" + localName +
                    '}';
        }
    }


}
