package com.something.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author 易铜
 * @Title:
 * @Description:
 * @date 2018/9/20 9:21
 */
public class RequestReceiveDatas {

    private List<RequestReceiveData> requestReceiveDataList;

    @XmlElement(name = "datas")
    public List<RequestReceiveData> getRequestReceiveDataList() {
        return requestReceiveDataList;
    }

    public void setRequestReceiveDataList(List<RequestReceiveData> requestReceiveDataList) {
        this.requestReceiveDataList = requestReceiveDataList;
    }
}
