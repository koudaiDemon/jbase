package com.cwww.icbc;

import com.alibaba.fastjson.JSONObject;
import com.icbc.api.BizContent;
import com.icbc.api.UiIcbcClient;
import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.request.B2cPcPayTransferRequestV1;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/10/22  15:47
 */
public class Demo1 {

    public static void main(String[] args) {

//        String service_url = request.getParameter("service_url");//API测试环境 交易对应api url
//        service_url = "";
//
//        //公共请求通讯区
//        String app_id = request.getParameter("app_id");//APPID
//        app_id = "";
//
//        String charset = request.getParameter("charset");//字符集
//
//
//        String ca = request.getParameter("ca");//ca证书
//        String private_key = request.getParameter("private_key");//私钥
//        String password = request.getParameter("password");//密码
//
//        String notify_url = request.getParameter("notify_url");//通知地址
//
//        String interfaceName = request.getParameter("interfaceName");//接口名
//        String interfaceVersion = request.getParameter("interfaceVersion");//接口版本号

        // 处理前台提交的数据
        /*请注意，对于可能出现中文的goods_name,mer_hint,remark1,remark2,mer_var,goods_address,mer_order_remark,backup1,backup2,backup3,backup4
         * 字段，需要进行base64编码再传过来，否则会出现乱码*/
//        String ret = dealPostData(request);
//
//        ca = ca.replaceAll(" ", "+");
//        private_key = private_key.replaceAll(" ", "+");
//
//        //String api_public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCwFgHD4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";
//        UiIcbcClient client = new UiIcbcClient(app_id, private_key, charset, ca, password);
//
//        response.setHeader("Content-Type", "text/html; charset=" + charset);
//        PrintWriter out = response.getWriter();
//        try {
//            B2cPcPayTransferRequestV1 requestApi = new B2cPcPayTransferRequestV1();
//
//            BizContent bizContent = JSON.parseObject(ret,
//                    requestApi.getBizContentClass());
//            requestApi.setBizContent(bizContent);
//            requestApi.setServiceUrl(service_url);
//            requestApi.setNotifyUrl(notify_url);
//            requestApi.setInterfaceName(interfaceName);
//            requestApi.setInterfaceVersion(interfaceVersion);
//
//            String str = client.buildPostForm(requestApi);
//            System.out.println(str);
//
//            out.write("<html>");
//            out.write("<head>");
//            out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=" + charset + "\">");
//            out.write("</head>");
//            out.write("<body>");
//            out.write(str);
//            out.write("</body>");
//            out.write("</html>");
			/*B2CPayTransferResponseV1 applePayRes = client.execute(requestApi);
			String res = JSONObject.toJSONString(applePayRes);
			Map<?, ?> resJson = null;
			Map<?, ?> bank = null;
			try {
				resJson = ApiJsonUtils.jsonToBean(res, Map.class);
				bank = (Map<?, ?>) resJson.get("bank");
			} catch (Exception e) {
				e.printStackTrace();
			}
			Object retCodeObj = null;
			String return_code = "";
			int retCodeInt = 0;
			Double retCodeDou = 0.0;
			try {
				retCodeObj = resJson.get("return_code");
				if (retCodeObj instanceof String) {
					return_code = (String) retCodeObj;
				} else if (retCodeObj instanceof Integer) {
					retCodeInt = (Integer) retCodeObj;
					return_code = retCodeInt + "";
				} else if (retCodeObj instanceof Double) {
					retCodeDou = (Double) retCodeObj;
					return_code = retCodeDou + "";
					if (return_code.indexOf('.') != -1) {
						return_code = return_code.substring(0,
								return_code.indexOf('.'));
					}
				} else {
					return_code = "";
				}
			} catch (Exception e) {
				return_code = "";
			}
			String return_msg = "";
			try {
				return_msg = (String) resJson.get("return_msg");
			} catch (Exception e) {
				return_msg = "";
			}
			String tran_error_code = "";
			try {
				tran_error_code = (String) bank.get("tran_error_code");
			} catch (Exception e) {
				tran_error_code = "";
			}
			String resp_code = "";
			try {
				resp_code = (String) bank.get("resp_code");
			} catch (Exception e) {
				resp_code = "";
			}
			return_msg = URLDecoder.decode(return_msg, "UTF-8");
			out.write("返回代码：" + return_code);
			out.write("<br/>");
			out.write("返回信息：" + return_msg);
			out.write("<br/>");
			out.write("错误代码：" + tran_error_code);
			out.write("<br/>");
			out.write("银联应答码：" + resp_code);
			out.write("<br/>");
			out.write("<br/>");
			out.write("返回报文：" + res);*/
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            out.flush();
//            out.close();
//        }

    }


    public static String dealPostData(Map<String,String> request)
            throws UnsupportedEncodingException {

        String order_date = request.get("order_date");
        String order_id = request.get("order_id");
        String amount = request.get("amount");
        String installment_times = request.get("installment_times");
        String cur_type = request.get("cur_type");
        String mer_id = request.get("mer_id");
        String mer_acct = request.get("mer_acct");

        String verify_join_flag = request.get("verify_join_flag");
        String language = request.get("language");

        String goods_id = request.get("goods_id");
        String goods_name = request.get("goods_name");
        String goods_num = request.get("goods_num");
        String carriage_amt = request.get("carriage_amt");
        String mer_hint = request.get("mer_hint");
        String remark1 = request.get("remark1");
        String remark2 = request.get("remark2");

        String credit_type = request.get("credit_type");
        String mer_reference = request.get("mer_reference");
        String mer_custom_ip = request.get("mer_custom_ip");
        String goods_type = request.get("goods_type");
        String mer_custom_id = request.get("mer_custom_id");
        String mer_custom_phone = request.get("mer_custom_phone");
        String goods_address = request.get("goods_address");
        String mer_order_remark = request.get("mer_order_remark");

        String orderFlag_ztb = request.get("orderFlag_ztb");
        String o2o_mer_id = request.get("o2o_mer_id");
        String elife_mer_id = request.get("elife_mer_id");
        String pay_expire = request.get("pay_expire");
        String return_url = request.get("return_url");

        String mer_var = request.get("mer_var");
        String notify_type = request.get("notify_type");
        String result_type = request.get("result_type");
        String backup1 = request.get("backup1");
        String backup2 = request.get("backup2");
        String backup3 = request.get("backup3");
        String backup4 = request.get("backup4");
        String auto_refer_sec = request.get("auto_refer_sec");

        Map<String, Object> bean = new HashMap<String, Object>();
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("order_info.order_date", order_date);
        jsonObject.put("order_info.order_id", order_id);
        jsonObject.put("order_info.amount", amount);
        jsonObject.put("order_info.installment_times", installment_times);
        jsonObject.put("order_info.cur_type", cur_type);
        jsonObject.put("order_info.mer_id", mer_id);
        jsonObject.put("order_info.mer_acct", mer_acct);

        jsonObject.put("custom.verify_join_flag", verify_join_flag);
        jsonObject.put("custom.language", language);

        jsonObject.put("message.goods_id", goods_id);
        jsonObject.put("message.goods_name", goods_name);
        jsonObject.put("message.goods_num", goods_num);
        jsonObject.put("message.carriage_amt", carriage_amt);
        jsonObject.put("message.mer_hint", mer_hint);
        jsonObject.put("message.remark1", remark1);
        jsonObject.put("message.remark2", remark2);

        jsonObject.put("message.credit_type", credit_type);
        jsonObject.put("message.mer_reference", mer_reference);
        jsonObject.put("message.mer_custom_ip", mer_custom_ip);
        jsonObject.put("message.goods_type", goods_type);
        jsonObject.put("message.mer_custom_id", mer_custom_id);
        jsonObject.put("message.mer_custom_phone", mer_custom_phone);
        jsonObject.put("message.goods_address", goods_address);
        jsonObject.put("message.mer_order_remark", mer_order_remark);

        jsonObject.put("message.orderFlag_ztb", orderFlag_ztb);
        jsonObject.put("message.o2o_mer_id", o2o_mer_id);
        jsonObject.put("message.elife_mer_id", elife_mer_id);
        jsonObject.put("message.pay_expire", pay_expire);
        jsonObject.put("message.return_url", return_url);

        jsonObject.put("message.mer_var", mer_var);
        jsonObject.put("message.notify_type", notify_type);
        jsonObject.put("message.result_type", result_type);
        jsonObject.put("message.backup1", backup1);
        jsonObject.put("message.backup2", backup2);
        jsonObject.put("message.backup3", backup3);
        jsonObject.put("message.backup4", backup4);
        jsonObject.put("message.auto_refer_sec", auto_refer_sec);

        String input = "";
        input = jsonObject.toJSONString();
        return input;
    }

}
