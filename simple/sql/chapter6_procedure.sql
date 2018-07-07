# 第一个存储过程
# 根据用户id查询用户其他信息
drop procedure if exists `select_user_by_id`;
DELIMITER ;;
create procedure `select_user_by_id`(
	in userId bigint,
	out userName varchar(50),
	out userPassword varchar(50),
	out userEmail varchar(50),
	out userInfo text,
	out headImg blob,
	out createTime datetime)
begin
# 根据用户id查询用户其他信息
select user_name, user_password, user_email, user_info, head_img, create_time
into userName, userPassword, userEmail, userInfo, headImg, createTime
from sys_user
where id = userId;
end
;;
DELIMITER;

# 第二个存储过程
# 简单根据用户名和分页参数进行查询，返回总数和分页数据
drop procedure if exists `select_user_page`;
DELIMITER ;;
create procedure `select_user_page`(
	in userName varchar(50),
	in _offset bigint,
	in _limit bigint,
	out total bigint)
begin
# 查询数据总数
select count(*) into total
from sys_user
where user_name like concat('%', userName, '%');
# 分页查询数据
select *
from sys_user
where user_name like concat('%', userName, '%')
limit _offset, _limit;
end
;;
DELIMITER;

# 第三个存储过程
# 保存用户信息和角色关联信息
drop procedure if exists `insert_user_and_roles`;
DELIMITER ;;
create procedure `insert_user_and_roles`(
	out userId bigint,
	in userName varchar(50),
	in userPassword varchar(50),
	in userEmail varchar(50),
	in userInfo text,
	in headImg blob,
	out createTime datetime,
	in roleIds varchar(200)
)
begin
# 设置当前时间
set createTime = NOW();
# 插入数据
insert into sys_user(user_name, user_password, user_email, user_info, head_img, create_time)
values (userName, userPassword, userEmail, userInfo, headImg, createTime);
# 获取自增主键
select last_insert_id() into userId;
# 保存用户和角色关系数据
set roleIds = concat(',',roleIds,',');
insert into sys_user_role(user_id, role_id)
select userId, id from sys_role
where INSTR(roleIds, concat(',',id,','))>0;
end
;;
DELIMITER;

# 第四个存储过程
# 删除用户信息和角色关联信息
drop procedure if exists `delete_user_by_id`;
DELIMITER ;;
create procedure `delete_user_by_id`(in userId bigint)
begin
# 根据用户id查询用户其他信息
delete from sys_user_role where user_id = userId;
delete from sys_user where id=userId;
end
;;
DELIMITER;