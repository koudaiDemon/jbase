package com.cwww.zip;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.GenericRequest;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/12/6  20:28
 */
public class Demo {

    private static final int  BUFFER_SIZE = 2 * 1024;

    /**
     23
     * 压缩成ZIP 方法1
     24
     * @param srcDir 压缩文件夹路径
    25
     * @param out    压缩文件输出流
    26
     * @param keepDirStructure  是否保留原来的目录结构,true:保留目录结构;
    27
     *                          false:所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
    28
     * @throws RuntimeException 压缩失败会抛出运行时异常
    29
     */
    public static void toZip(String srcDir, OutputStream out, boolean keepDirStructure)
        throws RuntimeException{

        long start = System.currentTimeMillis();
        ZipOutputStream zos = null ;
        try {
            zos = new ZipOutputStream(out);
            File sourceFile = new File(srcDir);
            compress(sourceFile,zos,sourceFile.getName(),keepDirStructure);
            long end = System.currentTimeMillis();
            System.out.println("压缩完成，耗时：" + (end - start) +" ms");
        } catch (Exception e) {
            throw new RuntimeException("zip error from ZipUtils",e);
        }finally{
            if(zos != null){
                try {
                    zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 压缩成ZIP 方法2
     * @param srcFiles 需要压缩的文件列表
     * @param out           压缩文件输出流
     * @throws RuntimeException 压缩失败会抛出运行时异常
     */
    public static void toZip(List<File> srcFiles , OutputStream out)throws RuntimeException {
        long start = System.currentTimeMillis();
        ZipOutputStream zos = null ;
        try {
            zos = new ZipOutputStream(out);
            for (File srcFile : srcFiles) {
                byte[] buf = new byte[BUFFER_SIZE];
                zos.putNextEntry(new ZipEntry(srcFile.getName()));
                int len;
                FileInputStream in = new FileInputStream(srcFile);
                while ((len = in.read(buf)) != -1){
                    zos.write(buf, 0, len);
                }
                zos.closeEntry();
                in.close();
            }
            long end = System.currentTimeMillis();
            System.out.println("压缩完成，耗时：" + (end - start) +" ms");
        } catch (Exception e) {
            throw new RuntimeException("zip error from ZipUtils",e);
        }finally{
            if(zos != null){
                try {
                    zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 压缩成ZIP 方法2
     * @param srcFiles 需要压缩的文件列表
     * @param out           压缩文件输出流
     * @throws RuntimeException 压缩失败会抛出运行时异常
     */
    public static void toZip(Map<String,InputStream> srcFiles, OutputStream out)throws RuntimeException {
        long start = System.currentTimeMillis();
        ZipOutputStream zos = null ;
        try {
            zos = new ZipOutputStream(out);
            Set<String> set = srcFiles.keySet();
            for (String name : set) {
                byte[] buf = new byte[BUFFER_SIZE];
                zos.putNextEntry(new ZipEntry(name));
                int len;
                InputStream in = srcFiles.get(name);
                while ((len = in.read(buf)) != -1){
                    zos.write(buf, 0, len);
                }
                zos.closeEntry();
                in.close();
            }
            long end = System.currentTimeMillis();
            System.out.println("压缩完成，耗时：" + (end - start) +" ms");
        } catch (Exception e) {
            throw new RuntimeException("zip error from ZipUtils",e);
        }finally{
            if(zos != null){
                try {
                    zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 递归压缩方法
     * @param sourceFile 源文件
     * @param zos        zip输出流
     * @param name       压缩后的名称
     * @param keepDirStructure  是否保留原来的目录结构,true:保留目录结构;
     *                          false:所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
     * @throws Exception
     */
    private static void compress(File sourceFile, ZipOutputStream zos, String name,
                                 boolean keepDirStructure) throws Exception{
        byte[] buf = new byte[BUFFER_SIZE];
        if(sourceFile.isFile()){
            // 向zip输出流中添加一个zip实体，构造器中name为zip实体的文件的名字
            zos.putNextEntry(new ZipEntry(name));
            // copy文件到zip输出流中
            int len;
            FileInputStream in = new FileInputStream(sourceFile);
            while ((len = in.read(buf)) != -1){
                zos.write(buf, 0, len);
            }
            // Complete the entry
            zos.closeEntry();
            in.close();
        } else {
            File[] listFiles = sourceFile.listFiles();
            if(listFiles == null || listFiles.length == 0){
                // 需要保留原来的文件结构时,需要对空文件夹进行处理
                if(keepDirStructure){
                    // 空文件夹的处理
                    zos.putNextEntry(new ZipEntry(name + "/"));
                    // 没有文件，不需要文件的copy
                    zos.closeEntry();
                }

            }else {
                for (File file : listFiles) {
                    // 判断是否需要保留原来的文件结构
                    if (keepDirStructure) {
                        // 注意：file.getName()前面需要带上父文件夹的名字加一斜杠,
                        // 不然最后压缩包中就不能保留原来的文件结构,即：所有文件都跑到压缩包根目录下了
                        compress(file, zos, name + "/" + file.getName(),keepDirStructure);
                    } else {
                        compress(file, zos, file.getName(),keepDirStructure);
                    }

                }
            }
        }
    }

    private String basePath;


    /**
     * 解压文件
     *
     * @param unzip
     * @throws IOException
     */
    public void unzip(String unzip) throws IOException {
        File file = new File(unzip);
        basePath = file.getParent();
        FileInputStream fis = new FileInputStream(file);
        ZipInputStream zis = new ZipInputStream(fis);
        unzip(zis);
    }

    private void unzip(ZipInputStream zis) throws IOException {
        ZipEntry entry = zis.getNextEntry();
        if (entry != null) {
            File file = new File(basePath + File.separator + entry.getName());
            if (file.isDirectory()) {
                // 可能存在空文件夹
                if (!file.exists()) {
                    file.mkdirs();
                }
                unzip(zis);
            } else {
                File parentFile = file.getParentFile();
                if (parentFile != null && !parentFile.exists()) {
                    parentFile.mkdirs();
                }
                FileOutputStream fos = new FileOutputStream(file);// 输出流创建文件时必须保证父路径存在
                int len = 0;
                byte[] buf = new byte[1024];
                while ((len = zis.read(buf)) != -1) {
                    fos.write(buf, 0, len);
                }
                fos.flush();
                fos.close();
                zis.closeEntry();
                unzip(zis);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        /** 测试压缩方法1  */
        FileOutputStream fos1 = new FileOutputStream(new File("D:/mytest01.zip"));
//        Demo.toZip("D:\\前端\\student", fos1,true);

        OSSClient ossClient = new OSSClient("https://oss-cn-beijing.aliyuncs.com", "LTAI7bDhQukclEg3", "hEGskx06RCLC6sCmDiUN07w9kF889F");

        String bucketName = "for-test1";


        GetObjectRequest getObjectRequest1 = new GetObjectRequest(bucketName, "test/111.jpg");
        GetObjectRequest getObjectRequest2 = new GetObjectRequest(bucketName, "test/mytest01.zip");
        GetObjectRequest getObjectRequest3 = new GetObjectRequest(bucketName, "test/促销.doc");

        final OSSObject object1 = ossClient.getObject(getObjectRequest1);
        final OSSObject object2 = ossClient.getObject(getObjectRequest2);
        final OSSObject object3 = ossClient.getObject(getObjectRequest3);

        final InputStream objectContent1 = object1.getObjectContent();
        final InputStream objectContent2 = object2.getObjectContent();
        final InputStream objectContent3 = object3.getObjectContent();

        Map<String,InputStream> inputStreamMap = new HashMap<>(5);
        inputStreamMap.put("111.jpg", objectContent1);
        inputStreamMap.put("mytest01.zip", objectContent2);
        inputStreamMap.put("促销.doc", objectContent3);

        Demo.toZip(inputStreamMap,fos1);

//        /** 测试压缩方法2  */
//        List<File> fileList = new ArrayList<>();
//        fileList.add(new File("D:/Java/jdk1.7.0_45_64bit/bin/jar.exe"));
//        fileList.add(new File("D:/Java/jdk1.7.0_45_64bit/bin/java.exe"));
//        FileOutputStream fos2 = new FileOutputStream(new File("D:/mytest02.zip"));
//        Demo.toZip(fileList, fos2);

        String str = null;

        try {
            str.length();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
