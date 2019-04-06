package com.fzy.pms.entity.mapper;

import com.fzy.pms.entity.dto.MenuDto;
import com.fzy.pms.entity.security.Menu;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @program: MenuMapper
 * @description:
 * @author: fzy
 * @date: 2019-03-30 21:57
 **/
@Mapper(componentModel  = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MenuMapper extends EntityMapper<MenuDto, Menu> {
}
