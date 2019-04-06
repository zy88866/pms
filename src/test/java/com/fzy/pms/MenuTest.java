package com.fzy.pms;

import com.fzy.pms.entity.dto.MenuDto;
import com.fzy.pms.service.MenuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

/**
 * @program: MenuTest
 * @description:
 * @author: fzy
 * @date: 2019/04/06 14:44:51
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class MenuTest {

    @Autowired
    private MenuService menuService;

    @Test
    public void testMenuTree(){
        Set<MenuDto> menuTree = menuService.findByMenuTree();
        System.out.println(menuTree);
    }
}
