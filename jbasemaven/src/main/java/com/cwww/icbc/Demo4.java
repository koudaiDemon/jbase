package com.cwww.icbc;

import com.alibaba.fastjson.JSONObject;
import com.icbc.api.BizContent;
import com.icbc.api.IcbcConstants;
import com.icbc.api.UiIcbcClient;
import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.B2cPcPayTransferRequestV1;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/12/6  15:37
 */
public class Demo4 {

    protected static final String MY_PRIVATE_KEY = "BKiCCIc3wYRTIozUR76oYguDToWX+jc4Aak7i5LWkw+lq9rl41fKmZrdWLsSO9n5VnaSgjVAqo7a\n" +
            "8qRJGaf3552RV22ZzRyCt2GQzi4yVX1zqoAlQCAhTMiPK4ytS8MBP5dMnXAORnnUTZqESeOvAfW3\n" +
            "kMDt03rSE+q1ikVDTXYRlO+rBBqb6ipNuKkrbxQUYcDRF9pp6wHnP/KNqdWgmkCc3ltHxwYw1q2o\n" +
            "LJ5qGGrjXU+VttIQY+0/FmS7yTgjL8UvgGStOJdqURm3ZHo7AcNtKDM4iLySTSkmn5imrh0z2sOF\n" +
            "kMWifaTjcje9WAytUsQMNpRqOmUoqW0glIr9jXiQoxyXfd+uKbUVt9+PXla7GK7KDtv4cPI6TMx4\n" +
            "peZwPnUGVT3y43f0ahoBnY5a72k1ewuNGcx7Ll8sftbKJRsNa+0axXBuHXhKuKkcD+Hj2YTfakMM\n" +
            "nqjAQuGaZ320jVQYUfkN05Q6QpfiO1eWyVbMdhqisLD+V0PMEvNY7gSwGaqqZy6Y/oW9MqELUWsE\n" +
            "Lu7JDXaa7KgRrAZdxE9/2R8rmHQGhQwbqcWrYWQDsKbTetSWU5vJPZAqfswHCeku15KRPEoQL5w1\n" +
            "XC+WI1EyN4viD8soF4mFlVwALK56YZ43IwpTka50hrTrR1w+/bybzLCqPrv/ZXam1ylookQbq9Iu\n" +
            "IIsBHEa3E7z04ehCBfM530WRQZHKOIO8ugUuaZbMlMbTlDTKJIocC6c9DWCMwwAWGWj0UwAs9+VC\n" +
            "/NjBoornxT3/O5IQh3txBT/T+wo6VvTtuoXSp3I7Vd+HK2EObKFTHUTOrxQczLuAX2ieNQA8CuYJ\n" +
            "17vAX18R8VH5k7XxgebuT7ek4t+0m4MXJR4EuJOW1Yvpv6d+wbf01UDOuUn5/yw3KTvFTp/9/8jl\n" +
            "nqld7lAePYd9uQzuuWFlgmEa0qYKhlmnCiSylZV0JsnIRIYIYfiYoZBPx3mBRRlSnzW6mCBzD09Q\n" +
            "CN5hB6z5RwcfE6Q76CTGsiHhCYN0xFAb26bp4L1JgGHI48E4eCHLYBa8s/vyxhKdv3KqIlvyleMo\n" +
            "wQRKAVzbQRTiqtPzEXJwLt3ku2B09VbUPntohU5fWAEGkcFTVQ4RpMXvxHMM5VJdoJ6PE8E6tnVX\n" +
            "ezjyE6ZHEFsCuVfT3YsAbip51aU2nQjMV76VLkwhJdI/C9wO7uoWPDl80ihum8JwsuHHdZnv/Oak\n" +
            "25GWllJktDeC5oJBioLzpm+hh7BMJsApTiVESU9O30c2A9qVBG1P4ydoTliRxQWjj67ax9HCwK+/\n" +
            "ZbQsj4ypXrjz1gOc6uuKdnbr+kNxmfy9kxG/IaIn0cRuDgWxGi7sBddpYW0ZjvZDAIQF5IZzznnd\n" +
            "XTN8ptT+3AS8OzbES9Hll/cO6WnH9agzkyCXR3P49+fT77AKpSuptxwBxoPHC7ODjoLJG8nhOEO7\n" +
            "rHld7XSoAFkFSCMS2JxWD8McyevfqA5jm2NnAXsgmmHo2yWiYPfSzGabPQmmgYhjNUWDDxHz7WcL\n" +
            "kG8Sfa74cfatJRftiXho/ftxmRYnGeXxOsWt1UxQJ8KQxrMWhJfE7qf7MrvtONWTgo6yFpwo";

    protected static final String APIGW_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCwFgHD4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

    protected static final String APP_ID = "1104EH0012001";

    protected static final String PASSWORD = "95588";

    protected static final String MY_PUB_KEY = "MIIDmDCCAoCgAwIBAgIKG5LKECVWAAK/OTANBgkqhkiG9w0BAQsFADA7MR8wHQYDVQQDExZJQ0JD\n" +
            "IFRlc3QgQ29ycG9yYXRlIENBMRgwFgYDVQQKEw90ZXN0aWNiYy5jb20uY24wHhcNMTgxMTI2MDQz\n" +
            "NjU4WhcNMTkxMTI2MDQzNjU4WjBIMR0wGwYDVQQDDBQxMTA0RUgwMDEyMDAxLmUuMTEwNDENMAsG\n" +
            "A1UECwwEMTEwNDEYMBYGA1UECgwPdGVzdGljYmMuY29tLmNuMIIBIjANBgkqhkiG9w0BAQEFAAOC\n" +
            "AQ8AMIIBCgKCAQEAoQb+ty4E+RBr1sy5r8jv3/1uHzRsOlBLUtj277u5p2AvOM+WASeUtmUsJOYs\n" +
            "IIqG1+zoFjMr6MfuYq98fPz5Zlb7t1xZeXHpJjKUE9qeuyeuzrFU+j0zwtTZhlacZCW75V0UjhZY\n" +
            "rabC3e1j0lb59qWCn5NeSbLS1fqWq9PhIOOXh/FSV0E4idxGosxKcbh1iZuvK1hRRzuT2jZqV2jc\n" +
            "Vyye5vZ8Ij/5VDuOG0qitlTFzu7gPNAaHbuwi/+Rnm9dVdL6ZDrfyVjHUtoDrdNDECDQZuOVF9uV\n" +
            "Dqh32+lGcOYg/XAWSDLwbhoPto9arZO3dxRnEi8J0tQLt1DIk11l8QIDAQABo4GQMIGNMB8GA1Ud\n" +
            "IwQYMBaAFER9t5AsN6TZ7WzipIdXZwq18E0UMEsGA1UdHwREMEIwQKA+oDykOjA4MQ4wDAYDVQQD\n" +
            "DAVjcmwzNzEMMAoGA1UECwwDY3JsMRgwFgYDVQQKDA90ZXN0aWNiYy5jb20uY24wHQYDVR0OBBYE\n" +
            "FFn30zYBJFkNmFzVuy8lCtKdeVk7MA0GCSqGSIb3DQEBCwUAA4IBAQAVXTh5E/78huol1+kThMOr\n" +
            "wW4sOiSNhho7SZtQHOkzTftz2zATdS4LMcUxKy0xvWl9ODzifo5SE/jgHAZN0+xZKCivdsmhAOAu\n" +
            "YhGgZnglZneuPNN6mwOrMgEYXWZmv24Uy7eju1FQIOiGoYUN/ch2/d6qbB1hxySlE2puG4GjfPpQ\n" +
            "/ZcRlfX4Bx9yyjwi0zKbcebPHVNowvzeL6h4d+/3EeKbbW0tNIniCA02eYUbKo7ir3Bq8lio+KBv\n" +
            "MFZHoWv6uEaWiOwEBsresuVpz3Eq3R8iuakVX49QGmE/z85L8JgByw0qh7xyZVZ7Aef9d8zcO89/\n" +
            "P5qehKbE09K1HBO9";

    public static void main(String[] args) throws Exception {

        UiIcbcClient client = new UiIcbcClient(APP_ID, MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8, MY_PUB_KEY, PASSWORD);


        B2cPcPayTransferRequestV1 requestApi = new B2cPcPayTransferRequestV1();

        String json = "{\n" +
                "    \"order_info\":{\n" +
                "    \"order_date\": \"2018\",\n" +
                "    \"order_id\":\"888810222\",\n" +
                "    \"amount\":\"110\",\n" +
                "    \"installment_times\":\"1\",\n" +
                "    \"cur_type\":\"001\",\n" +
                "    \"mer_id\":\"0200EG0000602\",\n" +
                "    \"mer_acct\":\"6212880200000038618\"\n" +
                "    },\n" +
                "    \"custom\":{\n" +
                "        \"verify_join_flag\":\"0\", \n" +
                "        \"language\":\"zh_CN\",\n" +
                "    },\n" +
                "    \"message\":{\n" +
                "        \"goods_id\": \"428767198408147542\",\n" +
                "        \"goods_name\":\"超市\",\n" +
                "        \"goods_num\":\"1\",\n" +
                "        \"carriage_amt\":\"0\",\n" +
                "        \"mer_hint\":\"小心轻放\",\n" +
                "        \"remark1\":\"20180917231712\",\n" +
                "        \"remark2\":\"\",\n" +
                "        \"credit_type\":\"2\",\n" +
                "        \"mer_reference\":\"www.scgsj.com\",\n" +
                "        \"mer_custom_ip\":\"\",\n" +
                "        \"goods_type\":\"\",\n" +
                "        \"mer_custom_id\":\"\",\n" +
                "        \"mer_custom_phone\":\"\",\n" +
                "        \"goods_address\":\"\",\n" +
                "        \"mer_order_remark\":\"\",\n" +
                "        \"mer_var\":\"附言\",\n" +
                "        \"notify_type\":\"HS\",\n" +
                "        \"result_type\":\"0\",\n" +
                "        \"is_real\":\"\",\n" +
                "        \"subidno\":\"\",\n" +
                "        \"prompt_text\":\"\",\n" +
                "        \"backup1\":\"\",\n" +
                "        \"backup2\":\"\",\n" +
                "        \"backup3\":\"\",\n" +
                "        \"backup4\":\"\"        \n" +
                "        }\n" +
                "}";

        BizContent bizContent = JSON.parseObject(json,
                requestApi.getBizContentClass());
        requestApi.setBizContent(bizContent);
        requestApi.setServiceUrl("https://apipcs3.dccnet.com.cn/ui/b2c/pay/transfer/V1");
        requestApi.setNotifyUrl("https://www.scgsj.com/notify.do");
        requestApi.setInterfaceName("ICBC_WAPB_B2C");
        requestApi.setInterfaceVersion("1.0.0.6");

        String str = client.buildPostForm(requestApi);
        System.out.println(str);

//        Map<String, Object> bean = new HashMap<String, Object>();
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("","");
//        ApiJsonUtils.setValueAt(bean, "order_info.order_date", order_date);
//        ApiJsonUtils.setValueAt(bean, "order_info.order_id", order_id);
//        ApiJsonUtils.setValueAt(bean, "order_info.amount", amount);
//        ApiJsonUtils.setValueAt(bean, "order_info.installment_times", installment_times);
//        ApiJsonUtils.setValueAt(bean, "order_info.cur_type", cur_type);
//        ApiJsonUtils.setValueAt(bean, "order_info.mer_id", mer_id);
//        ApiJsonUtils.setValueAt(bean, "order_info.mer_acct", mer_acct);
//
//        ApiJsonUtils.setValueAt(bean, "custom.verify_join_flag", verify_join_flag);
//        ApiJsonUtils.setValueAt(bean, "custom.language", language);
//
//        ApiJsonUtils.setValueAt(bean, "message.goods_id", goods_id);
//        ApiJsonUtils.setValueAt(bean, "message.goods_name", goods_name);
//        ApiJsonUtils.setValueAt(bean, "message.goods_num", goods_num);
//        ApiJsonUtils.setValueAt(bean, "message.carriage_amt", carriage_amt);
//        ApiJsonUtils.setValueAt(bean, "message.mer_hint", mer_hint);
//        ApiJsonUtils.setValueAt(bean, "message.remark1", remark1);
//        ApiJsonUtils.setValueAt(bean, "message.remark2", remark2);
//
//        ApiJsonUtils.setValueAt(bean, "message.credit_type", credit_type);
//        ApiJsonUtils.setValueAt(bean, "message.mer_reference", mer_reference);
//        ApiJsonUtils.setValueAt(bean, "message.mer_custom_ip", mer_custom_ip);
//        ApiJsonUtils.setValueAt(bean, "message.goods_type", goods_type);
//        ApiJsonUtils.setValueAt(bean, "message.mer_custom_id", mer_custom_id);
//        ApiJsonUtils.setValueAt(bean, "message.mer_custom_phone", mer_custom_phone);
//        ApiJsonUtils.setValueAt(bean, "message.goods_address", goods_address);
//        ApiJsonUtils.setValueAt(bean, "message.mer_order_remark", mer_order_remark);
//
//        ApiJsonUtils.setValueAt(bean, "message.orderFlag_ztb", orderFlag_ztb);
//        ApiJsonUtils.setValueAt(bean, "message.o2o_mer_id", o2o_mer_id);
//        ApiJsonUtils.setValueAt(bean, "message.elife_mer_id", elife_mer_id);
//        ApiJsonUtils.setValueAt(bean, "message.pay_expire", pay_expire);
//        ApiJsonUtils.setValueAt(bean, "message.return_url", return_url);
//
//        ApiJsonUtils.setValueAt(bean, "message.mer_var", mer_var);
//        ApiJsonUtils.setValueAt(bean, "message.notify_type", notify_type);
//        ApiJsonUtils.setValueAt(bean, "message.result_type", result_type);
//        ApiJsonUtils.setValueAt(bean, "message.backup1", backup1);
//        ApiJsonUtils.setValueAt(bean, "message.backup2", backup2);
//        ApiJsonUtils.setValueAt(bean, "message.backup3", backup3);
//        ApiJsonUtils.setValueAt(bean, "message.backup4", backup4);
//        ApiJsonUtils.setValueAt(bean, "message.auto_refer_sec", auto_refer_sec);
//
//        String input = "";
//        input = ApiJsonUtils.beanToJson(bean);


    }

}
