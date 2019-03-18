package com.fzy.scm.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.fzy.scm.dao.UserRepository;
import com.fzy.scm.entity.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    @JsonView(User.UserSimpleView.class)
    public List<User> getUser(@PageableDefault(page = 1,size = 20,sort = "userName desc") Pageable pageable){
        System.out.println(pageable.getPageNumber());
        return null;
    }

    @GetMapping("/{id:\\d+}")
    @JsonView(User.UserDataView.class)
    public User getUser(@PathVariable Long id){
        return userRepository.findById(id).get();
    }

    @PostMapping
    public User createUser(@Valid @RequestBody User user ,BindingResult errors){

        if(errors.hasErrors()){
            errors.getAllErrors().forEach(e->
                    System.out.println(e.getDefaultMessage())
            );
        }
        return userRepository.save(user);
    }

}
