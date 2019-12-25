package com.something.mahout.model;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cWww
 * @Title Movie
 * @Description 电影模型
 * @date: 2019/10/11  14:57
 */
@Data
@ToString
public class Movie {
    private String id;
    private String title;
    private List<String> genres;

    public Movie(){
        this.genres = new ArrayList<>();
    }
}
