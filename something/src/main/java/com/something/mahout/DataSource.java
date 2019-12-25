package com.something.mahout;

import com.something.mahout.model.Movie;
import com.something.mahout.model.Rate;
import com.something.mahout.model.User;

import java.util.List;

/**
 * @author cWww
 * @Title DataSource
 * @Description 数据源
 * @date: 2019/10/11  14:56
 */
public interface DataSource {

    /**
     * 获取所有用户
     * @return User
     */
    List<User> getAllUsers();

    /**
     * 获取所有电影
     * @return Movie
     */
    List<Movie> getAllMovies();

    /**
     * 获取所有评分
     * @return Rate
     */
    List<Rate> getAllRate();

    /**
     * 通过用户获取评分
     * @param user 用户
     * @return 评分
     */
    List<Rate> getRateByUser(User user);

    /**
     * 通过电影获取评分
     * @param movie 电影
     * @return 评分
     */
    List<Rate> getRateByMovie(Movie movie);

}
