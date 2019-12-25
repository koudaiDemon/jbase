package com.cwww.icbc;

import cn.com.infosec.icbc.ReturnValue;

import java.io.FileInputStream;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2019/1/14  15:57
 */
public class GetString {

    public static void main(String[] args) throws Exception {
        FileInputStream f = new FileInputStream("D:\\yuwell\\工商银行接口\\鱼跃易企付测试证书\\1104EH0064001.key");
        byte[] bs = new byte[f.available()];
        f.read(bs);
        f.close();
        bs = ReturnValue.base64enc(bs);

        System.out.println(new String(bs));
    }

}
