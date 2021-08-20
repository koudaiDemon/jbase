package com.cwww.icbc;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by ssl on 2017/9/5.
 */
public class PFXUtil {

    /**
     * 获取RSA算法的keyFactory
     *
     * @return
     */
    private static KeyFactory getKeyFactory() throws Exception {
        return getKeyFactory("RSA");
    }

    /**
     * 获取指定算法的keyFactory
     *
     * @param algorithm
     * @return
     */
    private static KeyFactory getKeyFactory(String algorithm) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        return keyFactory;
    }

    /**
     * 根据pfx证书获取keyStore
     *
     * @param pfxData
     * @param password
     * @return
     * @throws Exception
     */
    private static KeyStore getKeyStore(byte[] pfxData, String password) throws Exception {
        KeyStore keystore = KeyStore.getInstance("PKCS12");
        keystore.load(new ByteArrayInputStream(pfxData), password.toCharArray());
        return keystore;
    }

    /**
     * 根据pfx证书得到私钥
     *
     * @param pfxData
     * @param password
     * @throws Exception
     */
    public static PrivateKey getPrivateKeyByPfx(byte[] pfxData, String password) throws Exception {
        PrivateKey privateKey = null;
        KeyStore keystore = getKeyStore(pfxData, password);
        Enumeration<String> enums = keystore.aliases();
        String keyAlias = "";
        while (enums.hasMoreElements()) {
            keyAlias = enums.nextElement();
            if (keystore.isKeyEntry(keyAlias)) {
                privateKey = (PrivateKey) keystore.getKey(keyAlias, password.toCharArray());
            }
        }
        return privateKey;
    }

    /**
     * 根据pfx证书得到私钥
     *
     * @param pfxPath
     * @param password
     * @return
     * @throws Exception
     */
    public static PrivateKey getPrivateKeyByPfx(String pfxPath, String password) throws Exception {
        File pfxFile = new File(pfxPath);
        return getPrivateKeyByPfx(FileUtils.readFileToByteArray(pfxFile), password);
    }

    /**
     * 根据私钥字节数组获取私钥对象
     *
     * @param privateKeyByte
     * @return
     * @throws Exception
     */
    public static PrivateKey getPrivateKey(byte[] privateKeyByte) throws Exception {
        PrivateKey privateKey = null;
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyByte);
        KeyFactory keyFactory = getKeyFactory();
        privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }

    /**
     * 根据私钥Base64字符串获取私钥对象
     *
     * @param privateKeyStr
     * @return
     * @throws Exception
     */
    public static PrivateKey getPrivateKey(String privateKeyStr) throws Exception {
        byte[] privateKeyByte = Base64.decodeBase64(privateKeyStr.getBytes());
        return getPrivateKey(privateKeyByte);
    }

    /**
     * 根据公钥字节数组获取公钥
     *
     * @param publicKeyByte 公钥字节数组
     * @return
     * @throws Exception
     */
    public static PublicKey getPublicKey(byte[] publicKeyByte) throws Exception {
        PublicKey publicKey = null;
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyByte);
        KeyFactory keyFactory = getKeyFactory();
        publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }

    /**
     * 根据公钥base64字符串获取公钥
     *
     * @param publicKeyStr Base64编码后的公钥字节数组
     * @return
     * @throws Exception
     */
    public static PublicKey getPublicKey(String publicKeyStr) throws Exception {
        byte[] publicKeyByte = Base64.decodeBase64(publicKeyStr.getBytes());
        return getPublicKey(publicKeyByte);
    }

    /**
     * 根据pfx证书获取证书对象
     *
     * @param pfxData  pfx的字节数组
     * @param password pfx证书密码
     * @return
     * @throws Exception
     */
    public static X509Certificate getX509Certificate(byte[] pfxData, String password) throws Exception {
        X509Certificate x509Certificate = null;
        KeyStore keystore = getKeyStore(pfxData, password);
        Enumeration<String> enums = keystore.aliases();
        String keyAlias = "";
        while (enums.hasMoreElements()) {
            keyAlias = enums.nextElement();
            if (keystore.isKeyEntry(keyAlias)) {
                x509Certificate = (X509Certificate) keystore.getCertificate(keyAlias);
            }
        }
        return x509Certificate;
    }

    /**
     * 根据pfx证书获取证书对象
     *
     * @param pfxPath  pfx证书路径
     * @param password pfx证书密码
     * @return
     * @throws Exception
     */
    public static X509Certificate getX509Certificate(String pfxPath, String password) throws Exception {
        File pfxFile = new File(pfxPath);
        return getX509Certificate(FileUtils.readFileToByteArray(pfxFile), password);
    }

    //生成pkcs12

    /**
     * 根据私钥、公钥证书、密码生成pkcs12
     *
     * @param privateKey      私钥
     * @param x509Certificate 公钥证书
     * @param password        需要设置的密钥
     * @return
     * @throws Exception
     */
    public static byte[] generatorPkcx12(PrivateKey privateKey, X509Certificate x509Certificate, String password)
            throws Exception {
        Certificate[] chain = {x509Certificate};
        KeyStore keystore = KeyStore.getInstance("PKCS12");
        keystore.load(null, password.toCharArray());
        keystore.setKeyEntry(x509Certificate.getSerialNumber().toString(), privateKey, password.toCharArray(), chain);
        ByteArrayOutputStream bytesos = new ByteArrayOutputStream();
        keystore.store(bytesos, password.toCharArray());
        byte[] bytes = bytesos.toByteArray();
        return bytes;
    }

    //合成pfx

    /**
     * 根据私钥、公钥证书、密钥，保存为pfx文件
     *
     * @param privateKey      私钥
     * @param x509Certificate 公钥证书
     * @param password        打开pfx的密钥
     * @param saveFile        保存的文件
     * @return
     * @throws Exception
     */
    public static String generatorPFX(PrivateKey privateKey, X509Certificate x509Certificate, String password, File
            saveFile) throws Exception {
        //判断文件是否存在
        if (!saveFile.exists()) {
            //判断文件的目录是否存在
            if (!saveFile.getParentFile().exists()) {
                saveFile.getParentFile().mkdirs();
            }
            saveFile.createNewFile();
        }
        byte[] pkcs12Byte = generatorPkcx12(privateKey, x509Certificate, password);
        FileUtils.writeByteArrayToFile(saveFile, pkcs12Byte);
        return saveFile.getPath();
    }

    public static void main(String[] args) throws Exception {
//        String pfxPath = "D:\\yuwell\\yyyl.pfx";
//        String password = "95588";
//        //私钥：pfx文件中获取私钥对象
//        PrivateKey privateKey = getPrivateKeyByPfx(pfxPath, password);
//        byte[] privateKeyByte = privateKey.getEncoded();
//        String privateKeyStr = new String(Base64.encodeBase64(privateKeyByte));
//        System.out.println("私钥Base64字符串：" + privateKeyStr);
//        //=====私钥Base64字符串转私钥对象
//        PrivateKey privateKey2 = getPrivateKey(privateKeyStr);
//        System.out.println("私钥Base64字符串2：" + new String(Base64.encodeBase64(privateKey2.getEncoded())));
//        //证书：从pfx文件中获取证书对象
//        X509Certificate certificate = getX509Certificate(pfxPath, password);
//
//
//
//        System.out.println("证书:"+certificate.getPublicKey().getAlgorithm());
//        System.out.println("==============="+certificate.getSigAlgName());
//        System.out.println("证书主题：" + certificate.getSubjectDN().getName());
//        String publicKeyStr = new String(Base64.encodeBase64(certificate.getPublicKey().getEncoded()));
//        System.out.println("公钥Base64字符串：" + publicKeyStr);
//        //=====根据公钥Base64字符串获取公钥对象
//        System.out.println("公钥Base64字符串2：" + new String(Base64.encodeBase64(getPublicKey(publicKeyStr).getEncoded())));
//        //PFX：合成pfx（需要私钥、公钥证书）
//        String savePath = generatorPFX(privateKey, certificate, "1", new File
//                ("C:\\Users\\49383\\Desktop\\文件\\009\\009.pfx"));
//        System.out.println(savePath);
//        System.out.println(BigDecimal.valueOf(Double.valueOf(179000000000.0)).toPlainString());
//
//        //   16位整数位，两小数位
//        DecimalFormat df   =     new   DecimalFormat( "#####0.00 ");
//        String   temp     =   df.format(Double.valueOf(179000000000.0));
//        System.out.println(temp);
//
//
//        NumberFormat nf = NumberFormat.getInstance();
//
//        nf.setGroupingUsed(false);
//        // 结果未做任何处理
//        System.out.println(nf.format(Double.valueOf(179000000000.0)));
//
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//
//        Date parse = sdf.parse("2018-11-05");
//
//        System.out.println(new Date().before(parse));
//        boolean hasobj = true;
//        int maxKeys = 1000;
//
//        String bucketName = "for-hybrisqas";
//
//        OSSClient ossClient = new OSSClient("https://oss-cn-hangzhou.aliyuncs.com", "", "");
//
//        ListObjectsRequest listObjectsRequest = new ListObjectsRequest(bucketName,null, null,null,maxKeys);
//        ObjectListing objectlisting = ossClient.listObjects(listObjectsRequest);
//
//        int size = objectlisting.getObjectSummaries().size();
//        if (size == 0){
//            hasobj = false;
//        }
//
        OSSClient ossClient2 = new OSSClient("https://oss-cn-beijing.aliyuncs.com", "", "");
        String bucketName2 = "for-test1";
        final GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName2, "test/111.jpg");
        final OSSObject object = ossClient2.getObject(getObjectRequest);
        System.out.println(object.getObjectMetadata().getContentType());

//
//
//        String markers = null;
//
//        ExecutorService executorService = Executors.newFixedThreadPool(5);
//
//        while (hasobj) {
//            final List<OSSObjectSummary> objectSummaries = objectlisting.getObjectSummaries();
//            for (OSSObjectSummary summary : objectSummaries) {
//                String name = "";
//                name = summary.getKey();
//                System.out.println(name + "...........");
//                // 下载OSS文件到本地文件。如果指定的本地文件存在会覆盖，不存在则新建。
//                OSSObject object = ossClient.getObject(new GetObjectRequest(bucketName, name));
//                ossClient2.putObject(bucketName2, object.getKey(), object.getObjectContent());
//            }
//
//            markers = objectlisting.getNextMarker();
//            hasobj = objectlisting.isTruncated();
//            System.out.println("hasobj..........."+hasobj+"..........makers..........."+markers);
//            listObjectsRequest = new ListObjectsRequest(bucketName,null,markers,null,maxKeys);
//            objectlisting = ossClient.listObjects(listObjectsRequest);
//
//            size = objectlisting.getObjectSummaries().size();
//
//            if (size == 0){
//                hasobj = false;
//            }
//        }
//
//
//        executorService.shutdown();
//        // 关闭OSSClient。
//        ossClient.shutdown();
//        ossClient2.shutdown();

    }
}
