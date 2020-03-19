package com.something.mahout;

import com.something.mahout.model.Gender;
import com.something.mahout.model.Movie;
import com.something.mahout.model.Rate;
import com.something.mahout.model.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author cWww
 * @Title FileDataSource
 * @Description 文件数据
 * @date: 2019/10/11  15:15
 */
@Slf4j
public class FileDataSource implements DataSource {

    private static final Integer SIZE = 500;
    private static final String PATH = "/Users/cWww/Documents/app/ml-1m";
    private static final String USER_FILE = "users.dat";
    private static final String MOVIE_FILE = "movies.dat";
    private static final String RATE_FILE = "ratings.dat";
    private static final String DEFAULT_ENCODING = "UTF-8";
    private static final String SPLIT_STR = "::";
    private static final String SPLIT_MOVIE = "|";
    private final List<User> users;
    private final List<Movie> movies;
    private final List<Rate> rates;
    private final Map<String,List<Rate>> userRateMapping;
    private final Map<String,List<Rate>> movieRateMapping;

    public FileDataSource(){
        users = new ArrayList<>();
        movies = new ArrayList<>();
        rates = new ArrayList<>();
        userRateMapping = new HashMap<>(SIZE);
        movieRateMapping = new HashMap<>(SIZE);
        init();
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }

    @Override
    public List<Movie> getAllMovies() {
        return movies;
    }

    @Override
    public List<Rate> getAllRate() {
        return rates;
    }

    @Override
    public List<Rate> getRateByUser(User user) {
        return userRateMapping.get(user.getId());
    }

    @Override
    public List<Rate> getRateByMovie(Movie movie) {
        return movieRateMapping.get(movie.getId());
    }

    private void init(){
        long start = System.currentTimeMillis();
        log.info("init DataSource start:[{}]", start);
        final File userFile = new File(PATH + File.separator + USER_FILE);
        final File movieFile = new File(PATH + File.separator + MOVIE_FILE);
        final File rate = new File(PATH + File.separator + RATE_FILE);

        try {
            final List<String> users = FileUtils.readLines(userFile, DEFAULT_ENCODING);
            final List<String> movies = FileUtils.readLines(movieFile,DEFAULT_ENCODING);
            final List<String> rates = FileUtils.readLines(rate,DEFAULT_ENCODING);

            users.forEach(this::parseString2User);
            movies.forEach(this::parseString2Movie);
            rates.forEach(this::parseString2Rate);

            this.userRateMapping.putAll(this.rates.stream().collect(Collectors.groupingBy(Rate::getUserId)));
            this.movieRateMapping.putAll(this.rates.stream().collect(Collectors.groupingBy(Rate::getMovieId)));

        } catch (IOException e) {
            log.error("IOException",e);
        }
        log.info("init DataSource end consuming:[{}]", System.currentTimeMillis() - start);
    }

    /**
     * 将字符串转为User
     * @return
     */
    private void parseString2User(String line) {
        final String[] split = line.split(SPLIT_STR);
        final User user = new User();
        user.setId(split[0]);
        user.setGender(Gender.valueOf(split[1].toUpperCase()));
        user.setAge(Integer.valueOf(split[2]));
        user.setOccupation(split[3]);
        this.users.add(user);
    }

    /**
     * 将字符串转为Movie
     * @return
     */
    private void parseString2Movie(String line) {
        final String[] split = line.split(SPLIT_STR);
        final Movie movie = new Movie();
        movie.setId(split[0]);
        movie.setTitle(split[1]);
        final List<String> genres = Arrays.asList(split[2].split(SPLIT_MOVIE));
        movie.setGenres(genres);
        this.movies.add(movie);
    }

    /**
     * 将字符串转为Rate
     * @return
     */
    private void parseString2Rate(String line) {
        final String[] split = line.split(SPLIT_STR);
        final Rate rate = new Rate();
        rate.setUserId(split[0]);
        rate.setMovieId(split[1]);
        rate.setPreference(Integer.valueOf(split[2]));
        rate.setTimestamp(Long.valueOf(split[3]));
        this.rates.add(rate);
    }

    public Map<String, List<Rate>> getUserRateMapping() {
        return userRateMapping;
    }

    public Map<String, List<Rate>> getMovieRateMapping() {
        return movieRateMapping;
    }
}
