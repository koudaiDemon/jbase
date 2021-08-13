package com.something.undertow;

import io.undertow.Undertow;
import io.undertow.UndertowOptions;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.protocol.http.HttpRequestParser;
import io.undertow.util.Headers;
import lombok.extern.slf4j.Slf4j;

/**
 * Description
 *
 * @author wei.cai@hand-china.com 2021/5/11
 */
@Slf4j
public class QuickStart {

    public static void main(String[] args) throws Exception {

//        ClassLoader.getSystemClassLoader().loadClass()
        Class.forName("io.undertow.io.AsyncSenderImpl");
        Class.forName("io.undertow.server.protocol.http.HttpServerConnection");
        Class.forName("io.undertow.server.protocol.http.HttpReadListener");
        Class.forName("org.xnio.nio.NioSocketConduit");
        Class.forName("org.xnio.conduits.ConduitStreamSinkChannel");
        Class.forName(HttpRequestParser.class.getName() + "$$generated", false, HttpRequestParser.class.getClassLoader());

        final Class<?> aClass = Class.forName("org.xnio.nio.WorkerThread");

        final Object o = aClass.newInstance();


        Undertow server = Undertow.builder()
                .setServerOption(UndertowOptions.ALWAYS_SET_DATE, false)
                .addHttpListener(8080, "localhost")
                .setHandler(new HttpHandler() {
                    @Override
                    public void handleRequest(final HttpServerExchange exchange) throws Exception {
                        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
                        exchange.getResponseSender().send("Hello World");
                    }
                }).build();
        server.start();

    }

}
