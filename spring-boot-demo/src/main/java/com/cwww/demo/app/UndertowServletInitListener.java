package com.cwww.demo.app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.context.ServletWebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * 初始化容器
 *
 * @author wei.cai@hand-china.com 2021/5/10
 */
@Component
@Slf4j
public class UndertowServletInitListener implements ApplicationListener<ServletWebServerInitializedEvent> {

    @Override
    public void onApplicationEvent(ServletWebServerInitializedEvent event) {
        final int port = event.getWebServer().getPort();
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new ResponseErrorHandler() {
            @Override
            public boolean hasError(ClientHttpResponse response) throws IOException {
                return false;
            }

            @Override
            public void handleError(ClientHttpResponse response) throws IOException {

            }
        });

        try {
            restTemplate.getForEntity("http://localhost:" + port + "/error", Void.class);
        } catch (Exception e) {
            log.error("UndertowServletInitListener error", e);
        }
    }

}
