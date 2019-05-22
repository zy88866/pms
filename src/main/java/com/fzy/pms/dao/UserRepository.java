package com.fzy.pms.dao;

import com.fzy.pms.entity.dto.AccountDto;
import com.fzy.pms.entity.security.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long>, JpaSpecificationExecutor {

    Optional<User> findByUsername(String username);

    @Modifying
    @Query(value ="update User set deleteFlag=1 where id=:id")
    int lockUser(@Param("id") Long id);

    @Query(value ="select new com.fzy.pms.entity.dto.AccountDto(user.id,user.username,user.balance) from User user")
    Page<AccountDto> findAccountAll(Pageable pageable);

    @Query(value ="select new com.fzy.pms.entity.dto.AccountDto(user.id,user.username,user.balance) from User user where id= :id")
    Page<AccountDto> findAccountByUserId(@Param("id") Long id,Pageable pageable);
}
