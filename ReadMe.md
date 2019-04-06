# 物业管理系统

基于前后端分离开发</br>


## 前端技术栈
1. vue+Element-Ul
 

## 后端技术栈
1. springBoot+springSecurity+JPA+Redis

 
## 初始化脚本
```sql
INSERT INTO t_role(`id`, `create_time`, `delete_flag`, `update_time`, `name`, `remark`) VALUES (1, '2019-04-05 14:14:45', 0, '2019-04-05 14:14:45', '超级管理员', '我是超管,我怕谁');
INSERT INTO t_user(`id`, `create_time`, `delete_flag`, `update_time`, `email`, `password`, `phone`, `real_name`, `username`, `role_id`) VALUES (1, '2019-03-23 19:55:36', 0, '2019-03-23 19:55:36', NULL, '$2a$10$cKEs3EJrpXOND7HIknIjquy0yFx990fNmFtIrW9oMpKhEMMZkwq2.', NULL, '张三', 'admin', 1);
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