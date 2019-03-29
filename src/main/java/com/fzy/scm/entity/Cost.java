package com.fzy.scm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import javax.persistence.Id;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @program: Cost
 * @description: 费用设置
 * @author: fzy
 * @date: 2019/03/29 20:47:18
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("Cost")
public class Cost {

    @Id
    private Long id;

    private String costName;

    @JsonIgnore
    @TimeToLive(unit = TimeUnit.MINUTES)
    private final Long expiration=120L;

    @JsonIgnore
    private List<String> list;

}
