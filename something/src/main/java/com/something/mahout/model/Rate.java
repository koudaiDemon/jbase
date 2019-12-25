package com.something.mahout.model;

import lombok.Data;
import lombok.ToString;

/**
 * @author cWww
 * @Title Rate
 * @Description 评分
 * @date: 2019/10/11  14:57
 */
@Data
@ToString
public class Rate {
    private String userId;
    private String movieId;
    private Integer preference;
    private Long timestamp;
}
