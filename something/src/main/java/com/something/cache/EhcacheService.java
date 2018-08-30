package com.something.cache;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/8/30  11:42
 */
public interface EhcacheService {

    // 测试失效情况，有效期为5秒
    String getTimestamp(String param);

    String getDataFromDB(String key);

    void removeDataAtDB(String key);

    String refreshData(String key);

    Dog findById(String dogId);

    boolean isReserved(String dogId);

    void removeDog(String dogId);

    public void removeAllDog();

}
