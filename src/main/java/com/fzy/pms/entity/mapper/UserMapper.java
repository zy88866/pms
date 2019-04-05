package com.fzy.pms.entity.mapper;

import com.fzy.pms.entity.dto.UserDto;
import com.fzy.pms.entity.security.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @program: UserMapper
 * @description:
 * @author: fzy
 * @date: 2019-04-05 15:45
 **/
@Mapper(componentModel  = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends EntityMapper<UserDto, User> {
}
