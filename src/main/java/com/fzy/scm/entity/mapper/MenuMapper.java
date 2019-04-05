package com.fzy.scm.entity.mapper;

import com.fzy.scm.entity.dto.MenuDTO;
import com.fzy.scm.entity.security.Menu;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @program: MenuMapper
 * @description:
 * @author: fzy
 * @date: 2019-03-30 21:57
 **/
@Mapper(componentModel  = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MenuMapper extends EntityMapper<MenuDTO, Menu> {
}
