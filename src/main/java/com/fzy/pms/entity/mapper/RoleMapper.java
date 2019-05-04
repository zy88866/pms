package com.fzy.pms.entity.mapper;

import com.fzy.pms.entity.dto.RoleDto;
import com.fzy.pms.entity.security.Role;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @program: RoleMapper
 * @description:
 * @author: fzy
 * @date: 2019/05/04 15:40:47
 **/
@Mapper(componentModel  = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper extends EntityMapper<RoleDto, Role> {
}
