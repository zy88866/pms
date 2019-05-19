package com.fzy.pms.service;

import com.fzy.pms.entity.pms.House;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

/**
 * @program: BaseService
 * @description:
 * @author: fzy
 * @date: 2019/05/19 08:27:29
 **/
public interface BaseService<T> {

    Page<T> findAll(Pageable pageableDefault);

    void create(T t);

    void update(T t);

    void delete(Long t);

    T findOne(Long id);

    default void batchDelete(Set<Long> ids){}

}
