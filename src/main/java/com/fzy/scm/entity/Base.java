package com.fzy.scm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
public class Base implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @JsonIgnore
    private String createUser;

    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @JsonIgnore
    private String updateUser;

    @JsonIgnore
    @NotNull
    private int deleteFlag;
}
