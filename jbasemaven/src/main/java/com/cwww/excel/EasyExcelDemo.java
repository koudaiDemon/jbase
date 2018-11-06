package com.cwww.excel;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.read.context.AnalysisContext;
import com.alibaba.excel.read.event.AnalysisEventListener;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.cwww.excel.pojo.ExcelPropertyIndexModel;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/9/26  19:41
 */
public class EasyExcelDemo {

    public static void main(String[] args) throws Exception {

        new EasyExcelDemo().test1();
        new EasyExcelDemo().noModelMultipleSheet();

    }

    public void noModelMultipleSheet() throws IOException {
        InputStream inputStream = new FileInputStream(new File("D:\\个人文档\\github\\jbase\\jbasemaven\\src\\main\\resources\\data\\easyexcel.xlsx"));
        try {
            ExcelReader reader = new ExcelReader(inputStream, ExcelTypeEnum.XLSX, null,
                    new AnalysisEventListener<List<String>>() {
                        @Override
                        public void invoke(List<String> object, AnalysisContext context) {
                            System.out.println(
                                    "当前sheet:" + context.getCurrentSheet().getSheetNo() + " 当前行：" + context.getCurrentRowNum()
                                            + " data:" + object);
                        }
                        @Override
                        public void doAfterAllAnalysed(AnalysisContext context) {

                        }
                    });

            reader.read();
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void test1() throws Exception {
        File file = new File("D:\\个人文档\\github\\jbase\\jbasemaven\\src\\main\\resources\\data\\easyexcel.xlsx");
//        file.createNewFile();
//        System.out.println(file);
        OutputStream out = new FileOutputStream(file);
        try {
            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);
            //写第一个sheet, sheet1  数据全是List<String> 无模型映射关系
            Sheet sheet1 = new Sheet(1, 0,ExcelPropertyIndexModel.class);
//            Sheet sheet1 = new Sheet(1, 0);
            sheet1.setSheetName("第一个sheet页");
//            List<List<String>> list = new ArrayList<>();
//            List<String> list1 = new ArrayList<>();
//            list1.add("abc");
//            list1.add("abc1");
//            list1.add("abc2");
//            List<String> list2 = new ArrayList<>();
//            list2.add("list1");
//            list2.add("list2");
//            list2.add("list3");
//            list.add(list1);
//            list.add(list2);
            writer.write(Collections.singletonList(new ExcelPropertyIndexModel("test","123456")),sheet1);
            writer.finish();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
