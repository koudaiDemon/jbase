package com.something.bigdemo.event;

import com.something.bigdemo.db.DBService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/9/3  11:54
 */
@Component("persistenListener")
public class PersistenListener implements ApplicationListener<PersistentEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersistenListener.class);

    @Override
    public void onApplicationEvent(PersistentEvent persistentEvent) {
        try {
            //修改数据库
            DBService.excute(persistentEvent.getItem());
        } catch (Exception e) {
            LOGGER.error("监听器出现错误",e);
        }
        //通知缓存
        persistentEvent.onEvent();
    }

}
