package com.fzy.scm.dao;

import com.fzy.scm.entity.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Override
    Optional<User> findById(Long id);

    @Override
    <S extends User> S save(S entity);

    Optional<User> findByUserName(String userName);
}
