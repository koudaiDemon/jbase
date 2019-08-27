package com.something.netty.util;

import io.netty.handler.codec.marshalling.*;
import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.Marshalling;
import org.jboss.marshalling.MarshallingConfiguration;

/**
 * @author cWww
 * @Title ServerDemo
 * @Description 编码工具
 * @date: 2018/9/29  19:02
 */
public class MarshallingCodeCFactory {
  
    public static MarshallingDecoder buildMarshallingDecoder() {
        final MarshallerFactory factory = Marshalling.getProvidedMarshallerFactory("serial");
        final MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);  
        UnmarshallerProvider provider = new DefaultUnmarshallerProvider(factory, configuration);
        MarshallingDecoder decoder = new MarshallingDecoder(provider, 1024*1024);  
        return decoder;  
    }  
  
    public static MarshallingEncoder buildMarshallingEncoder() {
        final MarshallerFactory factory = Marshalling.getProvidedMarshallerFactory("serial");  
        final MarshallingConfiguration configuration = new MarshallingConfiguration();  
        configuration.setVersion(5);  
        MarshallerProvider provider = new DefaultMarshallerProvider(factory, configuration);
        MarshallingEncoder encoder = new MarshallingEncoder(provider);  
        return encoder;  
    }  
}
