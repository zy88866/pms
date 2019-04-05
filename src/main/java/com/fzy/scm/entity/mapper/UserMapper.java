package com.fzy.scm.entity.mapper;

import com.fzy.scm.entity.dto.UserDto;
import com.fzy.scm.entity.security.User;
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
