package com.fzy.pms.dao;

import com.fzy.pms.entity.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUsername(String username);

//    @Query(value = "select new com.fzy.pms.entity.dto.UserDto(u.id,u.username,u.email,u.phone,u.realName,r.name) from User u left join Role r on u.role=r.id  order by u.createTime desc")
//    List<UserDto> findAllOrderByCreateTime();

    @Modifying
    @Query(value ="update User set deleteFlag=1 where id=:id")
    int  lockUser(@Param("id") Long id);

}
