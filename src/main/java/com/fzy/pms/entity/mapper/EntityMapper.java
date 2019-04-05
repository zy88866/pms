package com.fzy.pms.entity.mapper;

/**
 * @program: EntityMapper
 * @description:
 * @author: fzy
 * @date: 2019-03-30 21:54
 **/
public interface EntityMapper<D,E> {

    /**
     * Dto 转 Entity
     * @param dto
     * @return
     */
    E toEntity(D dto);


    /**
     * entity 转 dto
     * @param entity
     * @return
     */
    D toDto(E entity);



}

