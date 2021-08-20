package com.cwww.ftp;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.sshd.client.SshClient;
import org.apache.sshd.client.channel.ChannelExec;
import org.apache.sshd.client.channel.ClientChannel;
import org.apache.sshd.client.channel.ClientChannelEvent;
import org.apache.sshd.client.future.ConnectFuture;
import org.apache.sshd.client.keyverifier.AcceptAllServerKeyVerifier;
import org.apache.sshd.client.session.ClientSession;
import org.springframework.util.StreamUtils;

import java.io.ByteArrayOutputStream;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

/**
 * @author cWww
 * @Title App2
 * @Description 测试SSH
 * @date: 2019/10/17  11:08
 */
@Slf4j
public class App2 {

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        log.info("start:[{}]", start);
        String cmd = "cp ~/app/index.txt ~/app1/ &";
//        cmd = "ifconfig";
        SshClient client = SshClient.setUpDefaultClient();
        client.setServerKeyVerifier(AcceptAllServerKeyVerifier.INSTANCE);
        client.start();
        final ClientSession session = client.connect("test", "106.12.52.79", 22).
                verify().
                getSession();
        session.addPasswordIdentity("123456789");
         //session.addPublicKeyIdentity(SecurityUtils.loadKeyPairIdentity("keyname", new FileInputStream("priKey.pem"), null));
        if(!session.auth().verify().isSuccess()) {
            log.info("auth failed");
        }
        final String result = session.executeRemoteCommand(cmd);
        log.info("result:[{}]", result);

//        try (ChannelExec channel = session.createExecChannel(cmd)) {
////            final ByteArrayOutputStream bao = new ByteArrayOutputStream(1024);
//            channel.setOut(System.out);
//            channel.open().verify();
//            channel.waitFor(EnumSet.of(ClientChannelEvent.CLOSED), 0L);
//            Integer status = channel.getExitStatus();
//            log.info("status:[{}]", status);
//        }

//Shell 用于进行对话框
//        try (ClientChannel channel = session.createShellChannel()) {
//            channel.setIn(System.in);
//            channel.setOut(System.out);
//            channel.setErr(System.err);
//            try {
//                channel.open().verify();
//                channel.waitFor(EnumSet.of(ClientChannelEvent.CLOSED), 0L);
//            } finally {
//
//            }
//        }

//        ChannelExec ec = session.createExecChannel(cmd);
//        final ByteArrayOutputStream bao = new ByteArrayOutputStream(1024);
//        ec.setOut(bao);
//        ec.open();
//        ec.waitFor(Collections.singleton(ClientChannelEvent.CLOSED), 0);
//        ec.close(false);


        client.stop();

        log.info("consume:[{}]", System.currentTimeMillis() - start);
    }

}
