package tk.mybatis.simple.mapper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import tk.mybatis.simple.model.SysPrivilege;
import tk.mybatis.simple.model.SysRole;
import tk.mybatis.simple.type.Enabled;

public class RoleMapperTest extends BaseMapperTest {

	@Test
	public void testSelectById() {
		//获取sqlSession
		SqlSession sqlSession = getSqlSession();
		try {
			//获取RoleMapper接口
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			//调用selectById方法，查询id = 1的角色
			SysRole role = roleMapper.selectById(1L);
			//role不为空
			Assert.assertNotNull(role);
			//roleName=管理员
			Assert.assertEquals("管理员", role.getRoleName());
		}finally {
			//不要忘记关闭sqlSession
			sqlSession.close();
		}
	}
	
	@Test
	public void testSelectById2() {
		//获取sqlSession
		SqlSession sqlSession = getSqlSession();
		try {
			//获取RoleMapper接口
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			//调用selectById方法，查询id = 1的角色
			SysRole role = roleMapper.selectById2(1L);
			//role不为空
			Assert.assertNotNull(role);
			//roleName=管理员
			Assert.assertEquals("管理员", role.getRoleName());
		}finally {
			//不要忘记关闭sqlSession
			sqlSession.close();
		}
	}
	
	@Test
	public void testInsert() {
		//获取sqlSession
		SqlSession sqlSession = getSqlSession();
		try {
			//获取RoleMapper接口
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			//调用selectById方法，查询id = 1的角色
			SysRole role = roleMapper.selectById2(1L);
			//role不为空
			Assert.assertNotNull(role);
			role.setId(null);
			//roleName=管理员
			int result = roleMapper.insert(role);
			Assert.assertEquals(1, result);
		}finally {
			sqlSession.rollback();
			//不要忘记关闭sqlSession
			sqlSession.close();
		}
	}
	
	@Test
	public void testInsert2() {
		//获取sqlSession
		SqlSession sqlSession = getSqlSession();
		try {
			//获取RoleMapper接口
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			//调用selectById方法，查询id = 1的角色
			SysRole role = roleMapper.selectById2(1L);
			//role不为空
			Assert.assertNotNull(role);
			role.setId(null);
			//roleName=管理员
			int result = roleMapper.insert2(role);
			Assert.assertEquals(1, result);
		}finally {
			sqlSession.rollback();
			//不要忘记关闭sqlSession
			sqlSession.close();
		}
	}
	
	@Test
	public void testInsert3() {
		//获取sqlSession
		SqlSession sqlSession = getSqlSession();
		try {
			//获取RoleMapper接口
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			//调用selectById方法，查询id = 1的角色
			SysRole role = roleMapper.selectById2(1L);
			//role不为空
			Assert.assertNotNull(role);
			role.setId(null);
			//roleName=管理员
			int result = roleMapper.insert3(role);
			Assert.assertEquals(1, result);
		}finally {
			sqlSession.rollback();
			//不要忘记关闭sqlSession
			sqlSession.close();
		}
	}
	
//	@Test
//	public void testUpdateById() {
//		//获取sqlSession
//		SqlSession sqlSession = getSqlSession();
//		try {
//			//获取RoleMapper接口
//			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
//			//调用selectById方法，查询id = 1的角色
//			SysRole role = roleMapper.selectById2(1L);
//			//role不为空
//			Assert.assertNotNull(role);
//			//roleName=管理员
//			System.out.println(role.getCreateTime());
//			int result = roleMapper.updateById(role);
//			Assert.assertEquals(1, result);
//		}finally {
//			sqlSession.rollback();
//			//不要忘记关闭sqlSession
//			sqlSession.close();
//		}
//	}
	
	@Test
	public void testUpdateById() {
		//获取sqlSession
		SqlSession sqlSession = getSqlSession();
		try {
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			SysRole role = roleMapper.selectById(2L);
			Assert.assertEquals(Enabled.enabled, role.getEnabled());
			role.setEnabled(Enabled.disabled);
			roleMapper.updateById(role);
		}finally {
			sqlSession.rollback();
			//不要忘记关闭sqlSession
			sqlSession.close();
		}
	}
	
	@Test
	public void testDelete() {
		//获取sqlSession
		SqlSession sqlSession = getSqlSession();
		try {
			//获取RoleMapper接口
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			int result = roleMapper.deleteById(1L);
			Assert.assertEquals(1, result);
		}finally {
			sqlSession.rollback();
			//不要忘记关闭sqlSession
			sqlSession.close();
		}
	}
	
	@Test
	public void testSelectRoleByUserId() {
		SqlSession sqlSession = getSqlSession();
		try {
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			SysRole role = roleMapper.selectById(2L);
			role.setEnabled(Enabled.disabled);
			roleMapper.updateById(role);
			List<SysRole> roleList = roleMapper.selectRoleByUserIdChoose(1L);
			for(SysRole r : roleList) {
				System.out.println("角色名：" + r.getRoleName());
				if(r.getId().equals(1L)) {
					Assert.assertNotNull(r.getPrivilegeList());
				}else if(r.getId().equals(2L)){
					Assert.assertNull(r.getPrivilegeList());
					continue;
				}
				for(SysPrivilege privilege : r.getPrivilegeList()) {
					System.out.println("权限名：" + privilege.getPrivilegeName());
				}
			}
		}finally {
			sqlSession.close();
		}
	}
	
}
