alter table `sys_user`
modify column `user_email` varchar(50) null default 'test@mybatis.tk'
	comment '邮箱'
	after `user_password`;