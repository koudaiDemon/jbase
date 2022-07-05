package com.cwww.base;

import lombok.extern.slf4j.Slf4j;

/**
 * 用于测试Log的 CRLF
 *
 * @author wei.cai@hand-china.com 2022/5/24
 */
@Slf4j
public class LogDemo {
    public static void main(String[] args) {

        String info = "Firefox) was authenticated successfully\r\n[INFO] User bbb (Internet Explorer";



        log.info("({}", info);
    }

}
