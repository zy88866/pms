package com.fzy.pms.service.impl;

import com.fzy.pms.dao.RoleRepository;
import com.fzy.pms.dao.UserRepository;
import com.fzy.pms.entity.dto.UserDto;
import com.fzy.pms.entity.mapper.UserMapper;
import com.fzy.pms.entity.security.User;
import com.fzy.pms.exception.SystemErrorException;
import com.fzy.pms.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @program: UserServiceImpl
 * @description:
 * @author: fzy
 * @date: 2019/03/16 18:46:52
 **/

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseGet(()-> {
            throw new UsernameNotFoundException("用户名不存在");
        });
    }

    @Override
    public Optional<UserDto> getCurrUserInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!Objects.isNull(authentication)){
            return Optional.ofNullable(userMapper.toDto((User) authentication.getPrincipal()));
        }
        return Optional.empty();
    }

    @Override
    public User registerUser(User user){
        //密码加密
       user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
       if (userRepository.findByUsername(user.getUsername()).isPresent()){
           throw new SystemErrorException("用户名已存在");
       }

       if(Objects.isNull(user.getRole())){
           throw new SystemErrorException("角色不能为空");
       }
       roleRepository.findById(user.getRole().getId())
               .orElseThrow(()->new SystemErrorException("角色不存在"));
        return userRepository.save(user);
    }

    @Override
    public void lockUser(Long id){
        userRepository.lockUser(id);
    }

    @Override
    public Optional<User> getUserByUsername(String Username){
        if (StringUtils.isNotBlank(Username)){
           return userRepository.findByUsername(Username);
        }
      return Optional.empty();
    }

    @Override
    public List<UserDto> findAllListSortCreateTime() {
        Sort sort=new Sort(Sort.Direction.DESC,"createTime");
        List<UserDto> userDtoList = userMapper.toDto(userRepository.findAll(sort));
        return userDtoList;
    }

    @Override
    public void updateUserInfo(User user) {
        userRepository.findById(user.getId()).ifPresent(detail ->{
            detail.setUsername(user.getUsername());
            detail.setRealName(user.getRealName());
            detail.setPhone(user.getPhone());
            detail.setEmail(user.getEmail());
            detail.setRole(user.getRole());
            userRepository.save(detail);
        });
    }
}
