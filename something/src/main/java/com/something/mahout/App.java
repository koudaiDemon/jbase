package com.something.mahout;

import com.something.mahout.model.Rate;
import com.something.mahout.model.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.apache.mahout.cf.taste.impl.model.GenericDataModel;
import org.apache.mahout.cf.taste.impl.model.GenericUserPreferenceArray;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.PreferenceArray;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import java.util.List;

/**
 * @author cWww
 * @Title 推荐引擎，用电影数据进行推荐
 * @Description App https://grouplens.org/datasets/movielens/
 * @date: 2019/10/11  14:55
 */
@Slf4j
public class App {
    private static DataSource dataSource = new FileDataSource();

    /**
     * 用PearsonCorrelation 算法计算用户相似度
     */
    private static FastByIDMap<PreferenceArray> preferences = new FastByIDMap<>();

    static {
        final List<User> allUsers = dataSource.getAllUsers();
        for (User user : allUsers) {
            final List<Rate> rateByUser = dataSource.getRateByUser(user);
            if (CollectionUtils.isNotEmpty(rateByUser)) {
                final PreferenceArray prefsForUser = new GenericUserPreferenceArray(rateByUser.size());
                for (int i = 0 ; i < rateByUser.size() ; i++) {
                    final Rate rate = rateByUser.get(i);
                    prefsForUser.setUserID(i, Long.valueOf(rate.getUserId()));
                    prefsForUser.setItemID(i, Long.valueOf(rate.getMovieId()));
                    prefsForUser.setValue(i, Float.valueOf(rate.getPreference()));
                }
                preferences.put(Long.valueOf(user.getId()), prefsForUser);
            }
        }
    }

    private static void userBased() throws Exception {
        final long start = System.currentTimeMillis();
        log.info("start main method :[{}]", start);

        final DataModel model = new GenericDataModel(preferences);
        // 相似度
        final UserSimilarity userSimilarity = new PearsonCorrelationSimilarity(model);
        // 邻域
        final UserNeighborhood userNeighborhood = new NearestNUserNeighborhood(3, userSimilarity, model);
        // 构建推荐引擎
        final Recommender recommender = new GenericUserBasedRecommender(model, userNeighborhood, userSimilarity);
        // 进行推荐
        final List<RecommendedItem> recommend = recommender.recommend(1, 5);
        log.info("end main method consuming:[{}]", System.currentTimeMillis() - start);

        recommend.forEach(r -> log.info("id:[{}],recommend:[{}]", r.getItemID(), r));
    }

    private static void itemBased() throws Exception {
        final long start = System.currentTimeMillis();
        log.info("start main method :[{}]", start);

        final DataModel model = new GenericDataModel(preferences);
        // 相似度
        final ItemSimilarity itemSimilarity = new PearsonCorrelationSimilarity(model);
        // 构建推荐引擎
        final Recommender recommender = new GenericItemBasedRecommender(model, itemSimilarity);
        // 进行推荐
        final List<RecommendedItem> recommend = recommender.recommend(1, 5);
        log.info("end main method consuming:[{}]", System.currentTimeMillis() - start);

        recommend.forEach(r -> log.info("id:[{}],recommend:[{}]", r.getItemID(), r));
    }

    public static void main(String[] args) throws Exception {

        log.info("users size:[{}]", dataSource.getAllUsers().size());
        log.info("movies size:[{}]", dataSource.getAllMovies().size());
        log.info("rates size:[{}]", dataSource.getAllRate().size());
        userBased();
//        itemBased();
    }



}
