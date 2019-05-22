package com.fzy.pms.dao;

import com.fzy.pms.entity.dto.AccountDetailDto;
import com.fzy.pms.entity.pms.AccountDetail;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: AccountDetailRepository
 * @description:
 * @author: fzy
 * @date: 2019/05/21 22:07:56
 **/
@Repository
public interface AccountDetailRepository extends JpaRepository<AccountDetail,Long>{

    @Query("select new com.fzy.pms.entity.dto.AccountDetailDto(user.id,user.username,user.realName,user.phone,acc.money,acc.rechargeTime,acc.rechargeType) " +
            "from AccountDetail acc left join User user on user.id=acc.user")
    List<AccountDetailDto> findAllDto(Sort sort);


    @Query("select new com.fzy.pms.entity.dto.AccountDetailDto(user.id,user.username,user.realName,user.phone,acc.money,acc.rechargeTime,acc.rechargeType) " +
            "from AccountDetail acc left join User user on user.id=acc.user where user.id= :userId")
    List<AccountDetailDto> search(@Param("userId") Long userId, Sort sort);

}
