package com.fzy.pms.entity.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
@ApiModel("Base")
public class Base implements Serializable {

    @Id
    @ApiModelProperty("id")
    @NotNull(groups = {Update.class},message = "ID 不能为空")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(value = "创建时间",hidden = true)
    @JsonIgnore
    @CreationTimestamp
    private Date createTime;

    @ApiModelProperty(value ="更新时间",hidden = true)
    @JsonIgnore
    @UpdateTimestamp
    private Date updateTime;

    @ApiModelProperty(value ="删除标志位 0 为删除 1删除",hidden = true)
    @JsonIgnore
    @NotNull
    private int deleteFlag;

    public interface Update{}
}
