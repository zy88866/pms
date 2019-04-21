# 物业管理系统

基于前后端分离开发</br>


## 前端技术栈
1. vue+Element-Ul
 

## 后端技术栈
1. springBoot+springSecurity+JPA+Redis

 
## 初始化脚本
```sql
INSERT INTO t_role(`id`, `create_time`, `delete_flag`, `update_time`, `name`, `remark`) VALUES (1, '2019-04-05 14:14:45', 0, '2019-04-05 14:14:45', '超级管理员', '我是超管,我怕谁');
INSERT INTO t_user(`id`, `create_time`, `delete_flag`, `update_time`, `email`, `password`, `phone`, `real_name`, `username`, `role_id`) VALUES (1, '2019-03-23 19:55:36', 0, '2019-03-23 19:55:36', '1010101010@qq.com', '$2a$10$cKEs3EJrpXOND7HIknIjquy0yFx990fNmFtIrW9oMpKhEMMZkwq2.', '18788779966', '张三', 'admin', 1);
INSERT INTO t_menu(id, create_time, delete_flag, update_time, component, icon, name, path, pid)VALUES (100,now(),0,now(),'Layout' ,'pms-icon-xitongguanli' ,'系统管理','/sysSteam',0 );
INSERT INTO t_menu(id, create_time, delete_flag, update_time, component, icon, name, path, pid)VALUES (101,now(),0,now(),'Sys/User' ,'pms-icon-userguanli' ,'用户管理','/sysSteam/user',100 );
INSERT INTO t_menu(id, create_time, delete_flag, update_time, component, icon, name, path, pid)VALUES (102,now(),0,now(),'Sys/Role' ,'pms-icon-jiaoseguanli' ,'角色管理','/sysSteam/role',100 );
INSERT INTO t_menu(id, create_time, delete_flag, update_time, component, icon, name, path, pid)VALUES (200,now(),0,now(),'Layout' ,'pms-icon-shoufeiguanli' ,'收费管理','/cost',0 );
INSERT INTO t_menu(id, create_time, delete_flag, update_time, component, icon, name, path, pid)VALUES (201,now(),0,now(),'Cost/Set' ,'pms-icon-feiyongshezhi' ,'费用设置','/cost/setting',200 );
INSERT INTO t_menu(id, create_time, delete_flag, update_time, component, icon, name, path, pid)VALUES (202,now(),0,now(),'Cost/Recharge' ,'pms-icon-chongczhi' ,'充值缴费','/cost/recharge',200 );
INSERT INTO t_menu(id, create_time, delete_flag, update_time, component, icon, name, path, pid)VALUES (300,now(),0,now(),'Layout' ,NULL ,'资源管理','/systeam',0 );
INSERT INTO t_menu(id, create_time, delete_flag, update_time, component, icon, name, path, pid)VALUES (301,now(),0,now(),NULL ,NULL ,'房产管理','/systeam/user',300 );
INSERT INTO t_menu(id, create_time, delete_flag, update_time, component, icon, name, path, pid)VALUES (302,now(),0,now(),NULL ,NULL ,'车位管理','/systeam/role',300 );
INSERT INTO t_menu(id, create_time, delete_flag, update_time, component, icon, name, path, pid)VALUES (303,now(),0,now(),NULL ,NULL ,'门禁管理','/systeam/role',300 );
INSERT INTO t_menu(id, create_time, delete_flag, update_time, component, icon, name, path, pid)VALUES (400,now(),0,now(),'Layout' ,NULL ,'报修管理','/systeam',0 );
INSERT INTO t_menu(id, create_time, delete_flag, update_time, component, icon, name, path, pid)VALUES (401,now(),0,now(),NULL ,NULL ,'报修申请','/systeam/user',400 );
INSERT INTO t_menu(id, create_time, delete_flag, update_time, component, icon, name, path, pid)VALUES (402,now(),0,now(),NULL ,NULL ,'报修中心','/systeam/role',400 );
INSERT INTO t_menu(id, create_time, delete_flag, update_time, component, icon, name, path, pid)VALUES (500,now(),0,now(),'Layout' ,NULL ,'报表中心','/systeam',0 );
INSERT INTO t_menu(id, create_time, delete_flag, update_time, component, icon, name, path, pid)VALUES (501,now(),0,now(),NULL ,NULL ,'缴费报表','/systeam/user',500 );
INSERT INTO t_menu(id, create_time, delete_flag, update_time, component, icon, name, path, pid)VALUES (502,now(),0,now(),NULL ,NULL ,'维修报表','/systeam/role',500 );
INSERT INTO roles_menus(role_id, menu_id) VALUES (1,100);
INSERT INTO roles_menus(role_id, menu_id) VALUES (1,101);
INSERT INTO roles_menus(role_id, menu_id) VALUES (1,102);
INSERT INTO roles_menus(role_id, menu_id) VALUES (1,200);
INSERT INTO roles_menus(role_id, menu_id) VALUES (1,201);
INSERT INTO roles_menus(role_id, menu_id) VALUES (1,202);
```

## 项目笔记
 ### Validator 常用的检验规则
 - @NotNull 值不能为空
 - @Null 值必须为空
 - @pattern(regex=) 字符串必须匹配正则表达式
 - @Size(min= max=) 集合元素的数量必须在min 和max之间
 - @CreditCardNumber 字符串必须是信用卡
 - @Email 字符串必须是Email地址
 - @Length(min= max=) 字符串长度
 - @NotBlank  字符串必须有字符
 - @NotEmpty 字符串不为null 集合有元素
 - @Range(min= max=) 数字必须大于等于 min 小于max
 - @SafeHtml 字符串是安全的Html
 - @URL 字符串是URL
 - @AssertFalse 值是false
 - @AssertTrue  值是true
 - @DecimalMax(value inclusive) 可以放在字符串 小于等于value 
 - @DecimalMin(value inclusive) 可以放在字符串 大于等于value 
 - @past 必须是一个过去的日期
 - @Digits(integer= inclusive=) 数字格式检查 integer 整数部分最大长度 inclusive小数部分最大长度
 - @Max(value=) 值必须小于value,不能放在字符串上
 - @Min(value=) 值必须大于value,不能放在字符串上  