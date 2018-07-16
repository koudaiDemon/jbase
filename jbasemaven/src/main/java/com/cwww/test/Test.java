package com.cwww.test;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.ArrayList;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/3/8  18:04
 */
@Deprecated
public class Test {

    /*
    public static void main(String[] args) {

//        String json = "{\n" +
//                "\"xmldata\": {\n" +
//                "\"wmsSecurityInfo\":{\"password\":\"\",\"username\":\"\"},\n" +
//                "\"header\": [\n" +
//                "          {\n" +
//                "\"WarehouseID\": \"0506\", \n" +
//                "\"OrderNO\": \"123456\", \n" +
//                "\"DestinationNO\": \"66666\", \n" +
//                "\"DeliveryNO\": \"888888\"\n" +
//                "           }\n" +
//                "        ]\n" +
//                ",\"wmsParam\":{\"customerid\":\"\",\"messageid\":\"\",\"param\":\"\",\"stdno\":\"\",\"warehouseid\":\"\"}\n" +
//                "    }\n" +
//                "}\n";
//
//        ECFluxHandleMarkReqPojo pojo = JSON.parseObject(json,ECFluxHandleMarkReqPojo.class);
//        System.out.println(pojo.getXmlData().getHeader().get(0).getDeliveryNO());

        final ECFluxHandleMarkReqPojo pojo = new ECFluxHandleMarkReqPojo();
        final ECFluxHandleMarkReqPojo.XMLData xmlData = new ECFluxHandleMarkReqPojo.XMLData();
        final List<ECFluxHandleMarkReqPojo.Header> headers = new ArrayList<ECFluxHandleMarkReqPojo.Header>();
        final ECFluxHandleMarkReqPojo.WmsParam wmsParam = new ECFluxHandleMarkReqPojo.WmsParam();
        final ECFluxHandleMarkReqPojo.WmsSecurityInfo wmsSecurityInfo = new ECFluxHandleMarkReqPojo.WmsSecurityInfo();
        xmlData.setHeader(headers);
        xmlData.setWmsParam(wmsParam);
        xmlData.setWmsSecurityInfo(wmsSecurityInfo);
        pojo.setXmlData(xmlData);


        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(pojo);
        System.out.println(json);

    }*/

}
