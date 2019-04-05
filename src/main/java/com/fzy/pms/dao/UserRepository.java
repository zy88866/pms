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

    @Modifying
    @Query(value ="update User set deleteFlag=1 where id=:id")
    int  lockUser(@Param("id") Long id);

}
