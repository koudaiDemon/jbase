package com.cwww.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.util.*;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/7/3  9:47
 */
public class TestString {

    public static final String MEDICALTYPEFIRST = "Ⅰ";
    public static final String MEDICALTYPESECOND = "Ⅱ";
    public static final String MEDICALTYPETHRID = "Ⅲ";

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

//        new TestString().test2();

//        String str = "https://yuwell.local:9002/yuwellb2bstorefront";
//
//        System.out.println(str.substring(0,str.lastIndexOf("/")));


//        System.out.println("中文".getBytes(Charset.forName("GBK")).length);
//        System.out.println("abcd".getBytes().length);
//        final String abc = StringUtils.reverse("abc");
//        System.out.println(abc);
//        final String html = "<div style='font-size:20px;'>{0}</div>";
//        String real = MessageFormat.format(html,"abc");
//        System.out.println(real);
//        new TestString().test();
//        System.out.println("printDiv('"+"printPage"+"')");

//        Basic UElTVVBFUjpJbml0MTIzNA==
//        Basic UElTVVBFUjpJbml0MTIzNA==

//        StringBuilder stringBuilder = new StringBuilder("hello,");
//        stringBuilder.deleteCharAt(stringBuilder.length()-1);
//
//        System.out.println(stringBuilder);
//        System.out.println(stringBuilder1);

//        Map<String,String> map = new HashMap<>();
//
//        map.put(TestString.MEDICALTYPEFIRST,"1");
//        map.put(TestString.MEDICALTYPESECOND,"2");
//        map.put(TestString.MEDICALTYPETHRID,"3");
//
//        System.out.println(map);

//        String str = "[{\"Content\":\"<table width=\\\"100%\\\" border=\\\"0\\\" cellspacing=\\\"0\\\" cellpadding=\\\"0\\\"><tr><td>\n" +
//                "<p><img src=\\\"https://img.alicdn.com/imgextra/i1/196993935/TB2DaldXRYxQeBjSszeXXa0spXa_!!196993935.jpg\\\" alt=\\\"初上市價格\\\" width=\\\"100%\\\"  /></p>\n" +
//                "<p>79元<br />\n" +
//                "  ※初上市價格是指商品首次在優衣庫官方旗艦店銷售時的價格。</p>\n" +
//                "<p><img src=\\\"https://img.alicdn.com/imgextra/i3/196993935/TB20HVcXPzyQeBjy0FjXXc5CVXa_!!196993935.jpg\\\" alt=\\\"產品說明\\\" width=\\\"100%\\\" /></p>\n" +
//                "<p>頗具速幹性的面料，不易產生粘膩感！內外的同色系顏色，洋溢著休閒氣息。<br>·採用具有功能性的DRY EX面料製成。<br>·流汗後也有助於保持舒爽的肌膚觸感，洗後亦有助於快速晾乾。<br>·運動度假兩相宜。<br>·腰部左側附帶便於調節尺寸的橡筋孔設計，陪伴孩童成長。</p>\n" +
//                "<p><br />【面料組成】聚酯纖維100％。<br />【洗滌資訊】機洗</p>\n" +
//                "   <p style=\\\"color: #F00\\\">此商品在商品完好，符合相關退換貨規則的前提下支持七天無理由退換貨。</p></tr></table>\",\"ModuleId\":5,\"ModuleName\":\"商品參數\",\"Type\":\"cat_mod\"},{\"Content\":\"<p><img src=\\\"http://img01.taobaocdn.com/imgextra/i1/196993935/T25hJMXl4aXXXXXXXX_!!196993935.jpg\\\" align=\\\"absmiddle\\\" /></p>\",\"ModuleId\":24,\"ModuleName\":\"商品實拍\",\"Type\":\"cat_mod\"},{\"Content\":\"<p><a name=\\\"sizechart\\\"><img src=\\\"https://img.alicdn.com/imgextra/i2/196993935/TB2WTlaXNvxQeBjy0FiXXXioXXa_!!196993935.jpg\\\" alt=\\\"產品尺寸\\\" name=\\\"sizechart\\\" width=\\\"100%\\\" id=\\\"sizechart\\\" /></a></p>\n" +
//                "<table width=\\\"653\\\" border=\\\"0\\\" cellpadding=\\\"0\\\" cellspacing=\\\"0\\\" style=\\\"text-align:center; font-size:12px; font-family:微軟雅黑;border-left:solid #cccccc 1px\\\">\n" +
//                "  <tr style=\\\"background-color:#8c8c8c;\\\">\n" +
//                "    <td width=\\\"86\\\" rowspan=\\\"2\\\" bgcolor=\\\"#FF0000\\\"align=\\\"center\\\" style=\\\"text-align:center; font-family:微軟雅黑; color: #FFF;border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\">UNIQLO尺碼<br/>\n" +
//                "    (商品尺碼)</td>\n" +
//                "    <td height=\\\"36\\\" colspan=\\\"4\\\" style=\\\"text-align:center; font-family:微軟雅黑; color:#000;border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\" bgcolor=\\\"#dde3ff\\\">商品尺寸(cm)</td>\n" +
//                " <td height=\\\"36\\\" colspan=\\\"3\\\" bgcolor=\\\"#999999\\\" style=\\\"text-align:center; font-family:微軟雅黑; color: #FFF;border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\">建議身材(cm)</td>\n" +
//                "  </tr>\n" +
//                "  <tr style=\\\"background:#dde3ff;\\\">\n" +
//                "    <td width=\\\"68\\\" style=\\\"text-align:center; border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\">臀圍</td>\n" +
//                "    <td width=\\\"75\\\" style=\\\"text-align:center; border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\">底襠寬</td>\n" +
//                "    <td width=\\\"68\\\" style=\\\"text-align:center; border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\">上襠</td>\n" +
//                "    <td width=\\\"65\\\" style=\\\"text-align:center; border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\">褲內襠長</td>\n" +
//                " <td width=\\\"69\\\" height=\\\"36\\\" bgcolor=\\\"#999999\\\" style=\\\"text-align:center; font-family:微軟雅黑; color: #FFF;border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\">腰圍</td>\n" +
//                "  </tr>\n" +
//                "  <tr>\n" +
//                "    <td height=\\\"36\\\" bgcolor=\\\"#dde3ff\\\" style=\\\"text-align:center; border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\">110(110/50)</td>\n" +
//                "    <td style=\\\"border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\">72</td>\n" +
//                "    <td style=\\\"border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\">23</td>\n" +
//                "    <td style=\\\"border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\">20</td>\n" +
//                "    <td style=\\\"border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\">16</td>\n" +
//                " <td style=\\\"border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\">49～55</td>\n" +
//                "  </tr>\n" +
//                "  <tr>\n" +
//                "    <td height=\\\"36\\\" bgcolor=\\\"#dde3ff\\\" style=\\\"text-align:center; border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\">120(120/53)</td>\n" +
//                "    <td style=\\\"border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\">77</td>\n" +
//                "    <td style=\\\"border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\">25</td>\n" +
//                "    <td style=\\\"border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\">21</td>\n" +
//                "    <td style=\\\"border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\">19</td>\n" +
//                "    <td style=\\\"border-bottom: solid #cccccc 1.0px;border-right: solid #cccccc 1.0px;\\\">51～57</td>\n" +
//                "  </tr>\n" +
//                "  <tr>\n" +
//                "    <td height=\\\"36\\\" bgcolor=\\\"#dde3ff\\\" style=\\\"text-align:center; border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\">130(130/56)</td>\n" +
//                "    <td style=\\\"border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\">82</td>\n" +
//                "    <td style=\\\"border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\">26</td>\n" +
//                "    <td style=\\\"border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\">22</td>\n" +
//                "    <td style=\\\"border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\">22</td>\n" +
//                "    <td style=\\\"border-bottom: solid #cccccc 1.0px;border-right: solid #cccccc 1.0px;\\\">53～59</td>\n" +
//                "  </tr>\n" +
//                "  <tr>\n" +
//                "    <td height=\\\"36\\\" bgcolor=\\\"#dde3ff\\\" style=\\\"text-align:center; border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\">140(140/59)</td>\n" +
//                "    <td style=\\\"border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\">87</td>\n" +
//                "    <td style=\\\"border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\">28</td>\n" +
//                "    <td style=\\\"border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\">23</td>\n" +
//                "    <td style=\\\"border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\">25</td>\n" +
//                "    <td style=\\\"border-bottom: solid #cccccc 1.0px;border-right: solid #cccccc 1.0px;\\\">56～62</td>\n" +
//                "  </tr>\n" +
//                "  <tr>\n" +
//                "    <td height=\\\"36\\\" bgcolor=\\\"#dde3ff\\\" style=\\\"text-align:center; border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\">150(150/62)</td>\n" +
//                "    <td style=\\\"border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\">92</td>\n" +
//                "    <td style=\\\"border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\">29.5</td>\n" +
//                "    <td style=\\\"border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\">24</td>\n" +
//                "    <td style=\\\"border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\">28</td>\n" +
//                "    <td style=\\\"border-bottom: solid #cccccc 1.0px;border-right: solid #cccccc 1.0px;\\\">59～65</td>\n" +
//                "  </tr>\n" +
//                "  <tr>\n" +
//                "    <td height=\\\"36\\\" bgcolor=\\\"#dde3ff\\\" style=\\\"text-align:center; border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\">160(160/65)</td>\n" +
//                "    <td style=\\\"border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\">96</td>\n" +
//                "    <td style=\\\"border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\">31</td>\n" +
//                "    <td style=\\\"border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\">25</td>\n" +
//                "    <td style=\\\"border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\">29.5</td>\n" +
//                "    <td style=\\\"border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\">62～68</td>\n" +
//                "  </tr>\n" +
//                "</table>\",\"ModuleId\":41,\"ModuleName\":\"商品尺碼表\",\"Type\":\"cat_mod\"}]";
//
//        System.out.println(str.replaceAll("\n",""));
//
//        System.out.println("===============================================");
//        System.out.println(str);
//        System.out.println("solrserver.instances.default.autostart=true".indexOf(46));
//
//        System.out.println((char)46);
//        System.out.println("solrserver.instances.default.autostart=true".indexOf(46));

//        final CreditCheckRequestData requestData = new CreditCheckRequestData();
//        final CreditCheckRequestData.CreditCheckRequestBodyData requestBodyData = new CreditCheckRequestData.CreditCheckRequestBodyData();
//        requestBodyData.setOrgUid("1001");
//        requestBodyData.setUnit("20000009");
//        requestBodyData.setUnitUid("1001");
//        requestBodyData.setCurrency("CNY");
//        requestBodyData.setTotalPrice(10000+"");
//        String payment = "1";
//        switch (orderModel.getPaymentMode().getCode()) {
//            case PAYMENT_CREDIT:
//                payment = "1";
//                break;
//            case PAYMENT_SHORT:
//                payment = "2";
//                break;
//            case PAYMENT_CASH:
//                payment = "3";
//                break;
//            default:
//                break;
//        }
//        requestBodyData.setPaymentMode(payment);
//        requestData.setCreditCheckRequestBodyData(requestBodyData);
//
//
//        final String url = "http://10.10.0.140:50000/RESTAdapter/CUSTOMER/";
//        final CloseableHttpClient client = HttpClients.createDefault();
//        final HttpPost post = new HttpPost(url);
//        final RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(1000 * 60 * 2).setConnectTimeout(1000 * 60 * 2).build();
//        post.setConfig(requestConfig);
////        if (null != interFaceEndPointData
////                && StringUtils.isNotEmpty(interFaceEndPointData.getUserName())
////                && StringUtils.isNotEmpty(interFaceEndPointData.getUserPassword())) {
//        final String encoding = java.util.Base64.getEncoder().encodeToString(
//                ("PODHBS" + ":" + "Init1234").getBytes());
//        post.addHeader("Authorization", "Basic " + encoding);
////        }
//
//        post.addHeader("Content-type","application/json; charset=utf-8");
//        post.setHeader("Accept", "application/json");
//
//        // 构造请求数据
//        final StringEntity myEntity = new StringEntity(JSON.toJSONString(requestData), ContentType.APPLICATION_JSON);
//        // 设置请求体
//        post.setEntity(myEntity);
//        // 响应内容
//        String responseContent = "";
//
//
//        try (CloseableHttpResponse response = client.execute(post)) {
//            HttpEntity entity = response.getEntity();
//            responseContent = EntityUtils.toString(entity, "UTF-8");
//            final CreditCheckResponseData creditCheckResponseData = JSON.parseObject(responseContent,CreditCheckResponseData.class);
//            System.out.println(responseContent);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                client.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        final String str = "core,testweb,scripting,paymentstandard,mediaweb,maintenanceweb,deliveryzone,commons,processing,impex,validation,catalog,europe1,platformservices,workflow,oauth2,hac,comments,advancedsavedquery,ruleengine,ordercalculation,embeddedserver,tomcatembeddedserver,virtualjdbc,springintegrationlibs,adaptivesearch,basecommerce,payment,ruleengineservices,droolsruleengineservices,customerreview,ruledefinitions,permissionsfacades,npmancillary,solrfacetsearch,auditreportservices,cms2,cms2lib,webservicescommons,smartedit,voucher,groovynature,personalizationsearchsmartedit,mediaconversion,wishlist,promotions,commerceservices,textfieldconfiguratortemplateservices,textfieldconfiguratortemplatefacades,personalizationservices,personalizationsearch,personalizationfacades,personalizationwebservices,commercefacades,ordermanagementfacades,cmsfacades,cmswebservices,ticketsystem,assistedserviceservices,assistedservicefacades,customerticketingfacades,acceleratorservices,acceleratorcms,acceleratorfacades,acceleratorstorefrontcommons,addonsupport,customerticketingaddon,personalizationsearchsamplesaddon,orderselfserviceaddon,assistedservicestorefront,textfieldconfiguratortemplateaddon,adaptivesearchsamplesaddon,commerceorgsamplesaddon,yuwellaliyunossclient,yuwellfulfilmentprocess,promotionengineservices,couponservices,timedaccesspromotionengineservices,promotionenginesamplesaddon,personalizationpromotions,personalizationpromotionsweb,chineseaddressservices,chineseaddressfacades,b2bcommerce,b2bcommercefacades,b2bapprovalprocess,b2bapprovalprocessfacades,commerceorgaddon,b2bacceleratorservices,b2bacceleratorfacades,b2bacceleratoraddon,cockpit,reportcockpit,cscockpit,admincockpit,b2badmincockpit,productcockpit,cmscockpit,yuwellcockpits,previewwebservices,cmssmartedit,smarteditaddon,permissionswebservices,cmssmarteditwebservices,smarteditwebservices,personalizationsmartedit,mcc,backoffice,platformbackoffice,ruleenginebackoffice,rulebuilderbackoffice,voucherbackoffice,basecommercebackoffice,timedaccesspromotionenginebackoffice,promotionsbackoffice,promotionenginebackoffice,cmsbackoffice,solrserver,yuwellcore,yuwellinitialdata,yuwellfacades,yuwellwebservice,yuwellbackoffice,solrfacetsearchbackoffice,backofficesolrsearch,pcmbackoffice,pcmbackofficesamplesaddon,yuwellb2bstorefront,commerceservicesbackoffice,personalizationservicesbackoffice,personalizationpromotionsbackoffice,b2bcommercebackoffice";

        System.out.println(Arrays.stream(str.split(",")).count());
        System.out.println(Arrays.stream(str.split(",")).distinct().count());


    }

}
