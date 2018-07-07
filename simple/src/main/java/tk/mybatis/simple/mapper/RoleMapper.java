package tk.mybatis.simple.mapper;

import java.util.List;

import org.apache.ibatis.annotations.CacheNamespaceRef;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import tk.mybatis.simple.model.SysRole;

@CacheNamespaceRef(RoleMapper.class)
public interface RoleMapper {

//	别名的方式
//	@Select({
//		"select id, role_name, enabled, create_by createBy, create_time createTime",
//		"from sys_role",
//		"where id = #{id}"
//	})

	/**
	 * 使用mapUnderscoreToCamelCase的方式
	 * @param id
	 * @return
	 */
	@Select({
		"select id, role_name, enabled, create_by, create_time",
		"from sys_role",
		"where id = #{id}"
	})
	SysRole selectById(Long id);
	
//	使用resultmap的方式
	 @Results({
		@Result(property = "id", column = "id", id = true),
 		@Result(property = "roleName", column = "role_name"),
 		@Result(property = "enabled", column = "enabled"),
 		@Result(property = "createBy", column = "create_by"),
 		@Result(property = "createTime", column = "create_time")
	 })
	@Select({
		"select id, role_name, enabled, create_by, create_time",
		"from sys_role",
		"where id = #{id}"
	})
	 SysRole selectById2(Long id);
	//mybatis3.3.1开始，可以通过引用id的方式，引用xml中和Java文件中的Resultmap类型
//	@Results(id = "roleResultMap",value= {
//			@Result(property = "id", column = "id", id = true),
//			//其他...
//	})
//	@ResultMap("roleResultMap")
//	@Select("select * from sys_role")
//	List<SysRole> selectAll();
	 
	 //不返回主键
	 @Insert({
		 "insert into sys_role(id, role_name, enabled, create_by, create_time)",
		 "values(#{id}, #{roleName}, #{enabled}, #{createBy}, #{createTime, jdbcType=TIMESTAMP})"
	 })
	 int insert(SysRole sysRole);

	 //返回自增主键
	 @Insert({
		 "insert into sys_role(role_name, enabled, create_by, create_time)",
		 "values(#{roleName}, #{enabled}, #{createBy}, #{createTime, jdbcType=TIMESTAMP})"
	 })
	 @Options(useGeneratedKeys = true, keyProperty = "id")
	 int insert2(SysRole sysRole);
	 
	 //返回自增主键
	 @Insert({
		 "insert into sys_role(role_name, enabled, create_by, create_time)",
		 "values(#{roleName}, #{enabled}, #{createBy}, #{createTime, jdbcType=TIMESTAMP})"
	 })
	 @SelectKey(statement = "select last_insert_id()",
			 keyProperty = "id",
			 resultType = Long.class,
			 before = false)
//	 等同于
//	 <selectKey keyColumn="id" resultType="long" keyProperty="id" order="AFTER">
//	 		select last_insert_id()
//	 </selectKey>
//	 before为false，表示order="BEFORE"
	 int insert3(SysRole sysRole);
	 
	 @Update({
		 "update sys_role",
		 "set role_name = #{roleName},",
			 "enabled = #{enabled},",
			 "create_by = #{createBy},",
			 "create_time = #{createTime, jdbcType = TIMESTAMP}",
		 "where id = #{id}"
	 })
	 int updateById(SysRole sysRole);
	 
	 @Delete("delete from sys_role where id = #{id}")
	 int deleteById(Long id);
	 
	 /**
	  * 根据用户id获取用户的角色信息
	  */
	 List<SysRole> selectRoleByUserIdChoose(Long userId);
	
}
