package tk.mybatis.simple.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import tk.mybatis.simple.model.SysRole;
import tk.mybatis.simple.model.SysUser;

public class UserMapperTest extends BaseMapperTest {

	@Test
	public void testSelectById() {
		SqlSession sqlSession = getSqlSession();
		try {
			//获取UserMapper接口
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			//调用selectById方法，查询id=1的用户
			SysUser user = userMapper.selectById(1l);
			//user不为空
			Assert.assertNotNull(user);
			//userName = admin
			Assert.assertEquals("admin", user.getUserName());
		}finally {
			//不要忘记关闭sqlSession
			sqlSession.close();
		}
	}
	
	@Test
	public void testSelectAll() {
		SqlSession sqlSession = getSqlSession();
		try {
			//获取UserMapper接口
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			//调用selectById方法，查询id=1的用户
			List<SysUser> userList = userMapper.selectAll();
			//结果不为空
			Assert.assertNotNull(userList);
			//用户数量大于0
			Assert.assertTrue(userList.size() > 0);
		}finally {
			//不要忘记关闭sqlSession
			sqlSession.close();
		}
	}
	
	@Test
	public void testSelectRolesUserId() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			//调用selectRolesByUserId方法查询用户的角色
			List<SysRole> roleList = userMapper.selectRolesByUserId(1L);
			//结果不为空
			Assert.assertNotNull(roleList);
			//角色数量大于0个
			Assert.assertTrue(roleList.size() > 0);
		}finally {
			//不要忘记关闭sqlSession
			sqlSession.close();
		}
	}
	
	@Test
	public void testInsert() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			//创建一个user对象
			SysUser user = new SysUser();
			user.setUserName("test1");
			user.setUserPassword("123456");
			user.setUserEmail("test@mybatis.tk");
			user.setUserInfo("test info");
			//正常应该读入一个图片存到byte数组中
			user.setHeadImg(new byte[]{1,2,3});
			user.setCreateTime(new Date());
			//将新建的对象插入数据库，result是执行的sql影响的行数
			int result = userMapper.insert(user);
			//只插入1条数据
			Assert.assertEquals(1, result);
			//id为null，没有给id复制，并且没有配置回写id的值
			Assert.assertNull(user.getId());
		}finally {
			//为了不影响其他测试，这里选择回滚
			//由于默认的sqlSessionFactory.openSession()是不自动提交的
			//因此不手动执行commit也不会提交到数据库
			sqlSession.rollback();
			//不要忘记关闭sqlSession
			sqlSession.close();
		}
	}
	
	@Test
	public void testInsert2() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			//创建一个user对象
			SysUser user = new SysUser();
			user.setUserName("test1");
			user.setUserPassword("123456");
			user.setUserEmail("test@mybatis.tk");
			user.setUserInfo("test info");
			//正常应该读入一个图片存到byte数组中
			user.setHeadImg(new byte[]{1,2,3});
			user.setCreateTime(new Date());
			//将新建的对象插入数据库，result是执行的sql影响的行数
			int result = userMapper.insert2(user);
			//只插入1条数据
			Assert.assertEquals(1, result);
			//id为null，没有给id复制，并且没有配置回写id的值
			Assert.assertNotNull(user.getId());
			System.out.println(user.getId());
		}finally {
			//为了不影响其他测试，这里选择回滚
			//由于默认的sqlSessionFactory.openSession()是不自动提交的
			//因此不手动执行commit也不会提交到数据库
			sqlSession.rollback();
			//不要忘记关闭sqlSession
			sqlSession.close();
		}
	}
	
	@Test
	public void testUpdateById() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			//从数据库查询1个user对象
			SysUser user = userMapper.selectById(1L);
			//当前userName为admin
			Assert.assertEquals("admin", user.getUserName());
			//修改用户名
			user.setUserName("admin_test");
			//修改邮箱
			user.setUserEmail("test@mybatis.tk");
			//更新数据，特别注意，这里的返回值result是执行的SQL影响的行数
			int result = userMapper.updateById(user);
			//只更新1条数据
			Assert.assertEquals(1, result);
			//根据当前id查询修改后的数据
			user = userMapper.selectById(1L);
			//修改后的名字是admin_test
			Assert.assertEquals("admin_test", user.getUserName());
		}finally {
			//为了不影响其他测试，这里选择回滚
			//由于默认的sqlSessionFactory.openSession()是不自动提交的
			//因此不手动执行commit也不会提交到数据库
			sqlSession.rollback();
			//不要忘记关闭sqlSession
			sqlSession.close();
		}
	}
	
	@Test
	public void testDeleteById() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			//从数据库查询1个user对象
			SysUser user1 = userMapper.selectById(1L);
			//现在还能查询出user对象
			Assert.assertNotNull(user1);
			//调用方法删除
			Assert.assertEquals(1, userMapper.deleteById(1L));
			//再次查询应该为null
			Assert.assertNull(userMapper.selectById(1L));
			
			//使用SysUser参数在进行一次测试，根据id = 1001查询
			SysUser user2 = userMapper.selectById(1001L);
			//现在还能查询出user对象
			Assert.assertNotNull(user2);
			//调用删除，参数为user2
			Assert.assertEquals(1, userMapper.deleteById(user2));
			//再次查询，应该为null
			Assert.assertNull(userMapper.selectById(1001L));
		}finally {
			//为了不影响其他测试，这里选择回滚
			//由于默认的sqlSessionFactory.openSession()是不自动提交的
			//因此不手动执行commit也不会提交到数据库
			sqlSession.rollback();
			//不要忘记关闭sqlSession
			sqlSession.close();
		}
	}
	
	@Test
	public void testSelectRolesByUserIdAndRoleEnabled() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			List<SysRole> userList = userMapper.selectRolesByUserIdAndRoleEnabled(1L, 1);
			Assert.assertNotNull(userList);
			Assert.assertTrue(userList.size() > 0);
		}finally {
			//为了不影响其他测试，这里选择回滚
			//由于默认的sqlSessionFactory.openSession()是不自动提交的
			//因此不手动执行commit也不会提交到数据库
			sqlSession.rollback();
			//不要忘记关闭sqlSession
			sqlSession.close();
		}
	}
	
}
