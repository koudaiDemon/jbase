package com.cwww.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.apache.commons.lang.StringEscapeUtils;

import java.util.List;
import java.util.Map;

/**
 * @author cWww
 * @Title
 * @Description json的反编译
 * @date: 2018/7/3  16:00
 */
public class Test {

    public static void main(String[] args) {
        Test test = new Test ();

        String text = "[{\\\"Content\\\":\\\"\\n<table width=\\\\\\\"740\\\\\\\" border=\\\\\\\"0\\\\\\\" cellspacing=\\\\\\\"0\\\\\\\" cellpadding=\\\\\\\"0\\\\\\\"><tr><td>\\n<p><img src=\\\\\\\"http://img03.taobaocdn.com/imgextra/i3/196993935/T2jmlnXndMXXXXXXXX_!!196993935.jpg\\\\\\\" alt=\\\\\\\"初上市价格\\\\\\\"  /></p>\\n<p>99元<br />\\n  ※初上市价格是指商品首次在优衣库官方旗舰店销售时的价格。</p>\\n<p><img src=\\\\\\\"http://img03.taobaocdn.com/imgextra/i3/196993935/T2a9t8XdFaXXXXXXXX_!!196993935.jpg\\\\\\\" alt=\\\\\\\"产品说明\\\\\\\" /></p>\\n<p>能温暖包裹肌肤的舒适HEATTECH。两翻领的设计，能保温至颈部。<br>·其保温的功能，适合在秋冬穿着。<br>·本季换成摩洛哥坚果油来提升其保湿效果。<br>·面料具有伸展性，贴合肌肤不会显得过于臃肿。<br>·能结实包裹至颈部的两翻领样式，内搭可提升其保温性。<br>·既可作为内搭亦可作为针织衫穿搭。</p>\\n<p><br />【面料组成】聚酯纤维38％•腈纶32％•粘纤21％•氨纶9％ <br />【洗涤信息】机洗</p>\\n<p style=\\\\\\\"color: #F00\\\\\\\">此商品在商品完好，符合相关退换货规则的前提下支持七天无理由退换货。</p></tr></table>\\n\\\",\\\"ModuleId\\\":119,\\\"ModuleName\\\":\\\"商家说明\\\",\\\"Type\\\":\\\"cat_mod\\\"},{\\\"Content\\\":\\\"<p><img src=\\\\\\\"http://img01.taobaocdn.com/imgextra/i1/196993935/T25hJMXl4aXXXXXXXX_!!196993935.jpg\\\\\\\" align=\\\\\\\"absmiddle\\\\\\\" /></p>\\\",\\\"ModuleId\\\":53,\\\"ModuleName\\\":\\\"细节图\\\",\\\"Type\\\":\\\"cat_mod\\\"},{\\\"Content\\\":\\\"<p><a name=\\\\\\\"sizechart\\\\\\\"><img src=\\\\\\\"http://img03.taobaocdn.com/imgextra/i3/196993935/T28SX.XdXXXXXXXXXX_!!196993935.jpg\\\\\\\" alt=\\\\\\\"产品尺寸\\\\\\\" name=\\\\\\\"sizechart\\\\\\\" id=\\\\\\\"sizechart\\\\\\\" /></a></p>\\n<table width=\\\\\\\"653\\\\\\\" border=\\\\\\\"0\\\\\\\" cellpadding=\\\\\\\"0\\\\\\\" cellspacing=\\\\\\\"0\\\\\\\" style=\\\\\\\"text-align:center; font-size:12px; font-family:微软雅黑;border-left:solid #cccccc 1px\\\\\\\" >\\n  <tr style=\\\\\\\"background-color:#8c8c8c;\\\\\\\">\\n    <td width=\\\\\\\"92\\\\\\\" rowspan=\\\\\\\"2\\\\\\\" bgcolor=\\\\\\\"#FF0000\\\\\\\"align=\\\\\\\"center\\\\\\\" style=\\\\\\\"text-align:center; font-family:微软雅黑; color: #FFF;border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\\\\\">UNIQLO尺寸<br/>(商品尺寸)</td>\\n    <td height=\\\\\\\"27\\\\\\\" colspan=\\\\\\\"5\\\\\\\" style=\\\\\\\"text-align:center; font-family:微软雅黑; color: #FFF;border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\\\\\">商品尺寸(cm)</td>\\n  </tr>\\n  <tr style=\\\\\\\"background:#dde3ff;\\\\\\\">\\n    <td width=\\\\\\\"92\\\\\\\" height=\\\\\\\"27\\\\\\\" style=\\\\\\\"text-align:center; border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\\\\\">后肩衣长</td>\\n    <td width=\\\\\\\"92\\\\\\\" style=\\\\\\\"text-align:center; border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\\\\\">肩宽</td>\\n    <td width=\\\\\\\"92\\\\\\\" style=\\\\\\\"text-align:center; border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\\\\\">身宽</td>\\n    <td width=\\\\\\\"92\\\\\\\" style=\\\\\\\"text-align:center; border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\\\\\">袖长</td>\\n    <td width=\\\\\\\"92\\\\\\\" style=\\\\\\\"text-align:center; border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\\\\\">领高</td>\\n  </tr>\\n  <tr>\\n    <td height=\\\\\\\"36\\\\\\\" bgcolor=\\\\\\\"#dde3ff\\\\\\\" style=\\\\\\\"text-align:center; border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\\\\\">155/85(S)</td>\\n    <td style=\\\\\\\"border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\\\\\">58</td>\\n    <td style=\\\\\\\"border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\\\\\">32</td>\\n    <td style=\\\\\\\"border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\\\\\">36</td>\\n    <td style=\\\\\\\"border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\\\\\">51.5</td>\\n    <td style=\\\\\\\"border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\\\\\">10</td>\\n  </tr>\\n  <tr>\\n    <td height=\\\\\\\"36\\\\\\\" bgcolor=\\\\\\\"#dde3ff\\\\\\\" style=\\\\\\\"text-align:center; border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\\\\\">160/90(M)</td>\\n    <td style=\\\\\\\"border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\\\\\">60</td>\\n    <td style=\\\\\\\"border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\\\\\">33</td>\\n    <td style=\\\\\\\"border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\\\\\">38</td>\\n    <td style=\\\\\\\"border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\\\\\">52</td>\\n    <td style=\\\\\\\"border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\\\\\">10</td>\\n  </tr>\\n  <tr>\\n    <td height=\\\\\\\"36\\\\\\\" bgcolor=\\\\\\\"#dde3ff\\\\\\\" style=\\\\\\\"text-align:center; border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\\\\\">165/95(L)</td>\\n    <td style=\\\\\\\"border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\\\\\">62</td>\\n    <td style=\\\\\\\"border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\\\\\">34.5</td>\\n    <td style=\\\\\\\"border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\\\\\">40</td>\\n    <td style=\\\\\\\"border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\\\\\">53.5</td>\\n    <td style=\\\\\\\"border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\\\\\">10</td>\\n  </tr>\\n  <tr>\\n    <td height=\\\\\\\"36\\\\\\\" bgcolor=\\\\\\\"#dde3ff\\\\\\\" style=\\\\\\\"text-align:center; border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\\\\\">170/100(XL)</td>\\n    <td style=\\\\\\\"border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\\\\\">64</td>\\n    <td style=\\\\\\\"border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\\\\\">35.5</td>\\n    <td style=\\\\\\\"border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\\\\\">42</td>\\n    <td style=\\\\\\\"border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\\\\\">53.5</td>\\n    <td style=\\\\\\\"border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\\\\\">10</td>\\n  </tr>\\n  <tr>\\n    <td height=\\\\\\\"36\\\\\\\" bgcolor=\\\\\\\"#dde3ff\\\\\\\" style=\\\\\\\"text-align:center; border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\\\\\">175/105(XXL)</td>\\n    <td style=\\\\\\\"border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\\\\\">66</td>\\n    <td style=\\\\\\\"border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\\\\\">36.5</td>\\n    <td style=\\\\\\\"border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\\\\\">44</td>\\n    <td style=\\\\\\\"border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\\\\\">53.5</td>\\n    <td style=\\\\\\\"border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\\\\\">10</td>\\n  </tr>\\n  <tr>\\n    <td height=\\\\\\\"36\\\\\\\" bgcolor=\\\\\\\"#dde3ff\\\\\\\" style=\\\\\\\"text-align:center; border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\\\\\">180/110(3XL)</td>\\n    <td style=\\\\\\\"border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\\\\\">66</td>\\n    <td style=\\\\\\\"border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\\\\\">37.5</td>\\n    <td style=\\\\\\\"border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\\\\\">46</td>\\n    <td style=\\\\\\\"border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\\\\\">53.5</td>\\n    <td style=\\\\\\\"border-bottom:solid #cccccc 1px;border-right:solid #cccccc 1px\\\\\\\">10</td>\\n  </tr>\\n</table>\\\",\\\"ModuleId\\\":117,\\\"ModuleName\\\":\\\"尺寸及试穿\\\",\\\"Type\\\":\\\"cat_mod\\\"}]";

//        System.out.println(text);

        String str2 = StringEscapeUtils.unescapeJava(text);
        System.out.println(str2);

//        List<Map> list = JSON.parseArray(str2,Map.class);
//        list.forEach(System.out::println);
//        String str3 = str2.substring(1,str2.length() - 1);
//        System.out.println(str3);

//        test.getGWHapGetDescription(str2);
    }
    private String getGWHapGetDescription(String sourceDescription) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            List<Map<String,Object>> productDescDataList = (List<Map<String,Object>>)JSONArray.parse(sourceDescription);
            for(Map<String,Object> map : productDescDataList){
                Object content = map.get("Content");
                if( content == null ){
                    content = map.get("content");
                }
                System.out.println("===============content================="+content);
                stringBuilder.append(content);
            }
            System.out.println("========descData========" + stringBuilder.toString());
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("描述中不合法");
            return "";
        }

    }

}
