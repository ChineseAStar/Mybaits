package tk.mybatis.simple.mapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
			//鑾峰彇UserMapper鎺ュ彛
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			//璋冪敤selectById鏂规硶锛屾煡璇d=1鐨勭敤鎴�
			SysUser user = userMapper.selectById(1l);
			//user涓嶄负绌�
			Assert.assertNotNull(user);
			//userName = admin
			Assert.assertEquals("admin", user.getUserName());
		}finally {
			//涓嶈蹇樿鍏抽棴sqlSession
			sqlSession.close();
		}
	}
	
	@Test
	public void testSelectAll() {
		SqlSession sqlSession = getSqlSession();
		try {
			//鑾峰彇UserMapper鎺ュ彛
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			//璋冪敤selectById鏂规硶锛屾煡璇d=1鐨勭敤鎴�
			List<SysUser> userList = userMapper.selectAll();
			//缁撴灉涓嶄负绌�
			Assert.assertNotNull(userList);
			//鐢ㄦ埛鏁伴噺澶т簬0
			Assert.assertTrue(userList.size() > 0);
		}finally {
			//涓嶈蹇樿鍏抽棴sqlSession
			sqlSession.close();
		}
	}
	
	@Test
	public void testSelectRolesUserId() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			//璋冪敤selectRolesByUserId鏂规硶鏌ヨ鐢ㄦ埛鐨勮鑹�
			List<SysRole> roleList = userMapper.selectRolesByUserId(1L);
			//缁撴灉涓嶄负绌�
			Assert.assertNotNull(roleList);
			//瑙掕壊鏁伴噺澶т簬0涓�
			Assert.assertTrue(roleList.size() > 0);
		}finally {
			//涓嶈蹇樿鍏抽棴sqlSession
			sqlSession.close();
		}
	}
	
	@Test
	public void testInsert() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			//鍒涘缓涓�涓猽ser瀵硅薄
			SysUser user = new SysUser();
			user.setUserName("test1");
			user.setUserPassword("123456");
			user.setUserEmail("test@mybatis.tk");
			user.setUserInfo("test info");
			//姝ｅ父搴旇璇诲叆涓�涓浘鐗囧瓨鍒癰yte鏁扮粍涓�
			user.setHeadImg(new byte[]{1,2,3});
			user.setCreateTime(new Date());
			//灏嗘柊寤虹殑瀵硅薄鎻掑叆鏁版嵁搴擄紝result鏄墽琛岀殑sql褰卞搷鐨勮鏁�
			int result = userMapper.insert(user);
			//鍙彃鍏�1鏉℃暟鎹�
			Assert.assertEquals(1, result);
			//id涓簄ull锛屾病鏈夌粰id澶嶅埗锛屽苟涓旀病鏈夐厤缃洖鍐檌d鐨勫��
			Assert.assertNull(user.getId());
		}finally {
			//涓轰簡涓嶅奖鍝嶅叾浠栨祴璇曪紝杩欓噷閫夋嫨鍥炴粴
			//鐢变簬榛樿鐨剆qlSessionFactory.openSession()鏄笉鑷姩鎻愪氦鐨�
			//鍥犳涓嶆墜鍔ㄦ墽琛宑ommit涔熶笉浼氭彁浜ゅ埌鏁版嵁搴�
			sqlSession.rollback();
			//涓嶈蹇樿鍏抽棴sqlSession
			sqlSession.close();
		}
	}
	
	@Test
	public void testInsert2() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			//鍒涘缓涓�涓猽ser瀵硅薄
			SysUser user = new SysUser();
			user.setUserName("test1");
			user.setUserPassword("123456");
			user.setUserEmail("test@mybatis.tk");
			user.setUserInfo("test info");
			//姝ｅ父搴旇璇诲叆涓�涓浘鐗囧瓨鍒癰yte鏁扮粍涓�
			user.setHeadImg(new byte[]{1,2,3});
			user.setCreateTime(new Date());
			//灏嗘柊寤虹殑瀵硅薄鎻掑叆鏁版嵁搴擄紝result鏄墽琛岀殑sql褰卞搷鐨勮鏁�
			int result = userMapper.insert2(user);
			//鍙彃鍏�1鏉℃暟鎹�
			Assert.assertEquals(1, result);
			//id涓簄ull锛屾病鏈夌粰id澶嶅埗锛屽苟涓旀病鏈夐厤缃洖鍐檌d鐨勫��
			Assert.assertNotNull(user.getId());
			System.out.println(user.getId());
		}finally {
			//涓轰簡涓嶅奖鍝嶅叾浠栨祴璇曪紝杩欓噷閫夋嫨鍥炴粴
			//鐢变簬榛樿鐨剆qlSessionFactory.openSession()鏄笉鑷姩鎻愪氦鐨�
			//鍥犳涓嶆墜鍔ㄦ墽琛宑ommit涔熶笉浼氭彁浜ゅ埌鏁版嵁搴�
			sqlSession.rollback();
			//涓嶈蹇樿鍏抽棴sqlSession
			sqlSession.close();
		}
	}
	
	@Test
	public void testInsert2Selective() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			SysUser user = new SysUser();
			user.setUserName("test-selective");
			user.setUserPassword("123456");
			user.setUserInfo("test info");
			user.setCreateTime(new Date());
			userMapper.insert2(user);
			user = userMapper.selectById(user.getId());
			Assert.assertEquals("test@mybatis.tk", user.getUserEmail());
		}finally {
			sqlSession.rollback();
			sqlSession.close();
		}
	}
	
	@Test
	public void testUpdateById() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			//浠庢暟鎹簱鏌ヨ1涓猽ser瀵硅薄
			SysUser user = userMapper.selectById(1L);
			//褰撳墠userName涓篴dmin
			Assert.assertEquals("admin", user.getUserName());
			//淇敼鐢ㄦ埛鍚�
			user.setUserName("admin_test");
			//淇敼閭
			user.setUserEmail("test@mybatis.tk");
			//鏇存柊鏁版嵁锛岀壒鍒敞鎰忥紝杩欓噷鐨勮繑鍥炲�紃esult鏄墽琛岀殑SQL褰卞搷鐨勮鏁�
			int result = userMapper.updateById(user);
			//鍙洿鏂�1鏉℃暟鎹�
			Assert.assertEquals(1, result);
			//鏍规嵁褰撳墠id鏌ヨ淇敼鍚庣殑鏁版嵁
			user = userMapper.selectById(1L);
			//淇敼鍚庣殑鍚嶅瓧鏄痑dmin_test
			Assert.assertEquals("admin_test", user.getUserName());
		}finally {
			//涓轰簡涓嶅奖鍝嶅叾浠栨祴璇曪紝杩欓噷閫夋嫨鍥炴粴
			//鐢变簬榛樿鐨剆qlSessionFactory.openSession()鏄笉鑷姩鎻愪氦鐨�
			//鍥犳涓嶆墜鍔ㄦ墽琛宑ommit涔熶笉浼氭彁浜ゅ埌鏁版嵁搴�
			sqlSession.rollback();
			//涓嶈蹇樿鍏抽棴sqlSession
			sqlSession.close();
		}
	}
	
	@Test
	public void testDeleteById() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			//浠庢暟鎹簱鏌ヨ1涓猽ser瀵硅薄
			SysUser user1 = userMapper.selectById(1L);
			//鐜板湪杩樿兘鏌ヨ鍑簎ser瀵硅薄
			Assert.assertNotNull(user1);
			//璋冪敤鏂规硶鍒犻櫎
			Assert.assertEquals(1, userMapper.deleteById(1L));
			//鍐嶆鏌ヨ搴旇涓簄ull
			Assert.assertNull(userMapper.selectById(1L));
			
			//浣跨敤SysUser鍙傛暟鍦ㄨ繘琛屼竴娆℃祴璇曪紝鏍规嵁id = 1001鏌ヨ
			SysUser user2 = userMapper.selectById(1001L);
			//鐜板湪杩樿兘鏌ヨ鍑簎ser瀵硅薄
			Assert.assertNotNull(user2);
			//璋冪敤鍒犻櫎锛屽弬鏁颁负user2
			Assert.assertEquals(1, userMapper.deleteById(user2));
			//鍐嶆鏌ヨ锛屽簲璇ヤ负null
			Assert.assertNull(userMapper.selectById(1001L));
		}finally {
			//涓轰簡涓嶅奖鍝嶅叾浠栨祴璇曪紝杩欓噷閫夋嫨鍥炴粴
			//鐢变簬榛樿鐨剆qlSessionFactory.openSession()鏄笉鑷姩鎻愪氦鐨�
			//鍥犳涓嶆墜鍔ㄦ墽琛宑ommit涔熶笉浼氭彁浜ゅ埌鏁版嵁搴�
			sqlSession.rollback();
			//涓嶈蹇樿鍏抽棴sqlSession
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
			//涓轰簡涓嶅奖鍝嶅叾浠栨祴璇曪紝杩欓噷閫夋嫨鍥炴粴
			//鐢变簬榛樿鐨剆qlSessionFactory.openSession()鏄笉鑷姩鎻愪氦鐨�
			//鍥犳涓嶆墜鍔ㄦ墽琛宑ommit涔熶笉浼氭彁浜ゅ埌鏁版嵁搴�
			sqlSession.rollback();
			//涓嶈蹇樿鍏抽棴sqlSession
			sqlSession.close();
		}
	}
	
	@Test
	public void testSelectByUser() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			//鍙煡璇㈢敤鎴峰悕鏃�
			SysUser query = new SysUser();
			query.setUserName("ad");
			List<SysUser> userList = userMapper.selectByUser(query);
			Assert.assertTrue(userList.size() > 0);
			//涔嬫煡璇㈢敤鎴烽偖绠辨椂
			query = new SysUser();
			query.setUserEmail("test@mybatis.tk");
			userList = userMapper.selectByUser(query);
			Assert.assertTrue(userList.size() > 0);
			//鍚屾椂鏌ヨ
			query = new SysUser();
			query.setUserName("ad");
			query.setUserEmail("test@mybatis.tk");
			userList = userMapper.selectByUser(query);
			//娌℃湁绗﹀悎鏉′欢鐨勭敤鎴凤紝缁撴灉鏁伴噺涓�0
			Assert.assertTrue(userList.size() == 0);
		}finally {
			sqlSession.close();
		}
	}

	@Test
	public void testUpdateByIdSelective() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			SysUser user = new SysUser();
			user.setId(1L);
			user.setUserEmail("test@mybatis.tk");
			int result = userMapper.updateByIdSelective(user);
			Assert.assertEquals(1, result);
			user = userMapper.selectById(1L);
			Assert.assertEquals("admin", user.getUserName());
			Assert.assertEquals("test@mybatis.tk", user.getUserEmail());
		}finally {
			sqlSession.rollback();
			sqlSession.close();
		}
	}
	
	@Test
	public void testSelectByIdOrUserName() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			SysUser query = new SysUser();
			query.setId(1L);
			query.setUserName("admin");
			SysUser user = userMapper.selectByIdOrUserName(query);
			Assert.assertNotNull(user);
			query.setId(null);
			user = userMapper.selectByIdOrUserName(query);
			Assert.assertNotNull(user);
			query.setUserName(null);
			user = userMapper.selectByIdOrUserName(query);
			Assert.assertNull(user);
		}finally {
			sqlSession.close();
		}
	}

	@Test
	public void testSelectByIdList() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			List<Long> idList = new ArrayList<Long>();
			idList.add(1L);
			idList.add(1001L);
			List<SysUser> userList = userMapper.selectByIdList(idList);
			Assert.assertEquals(2, userList.size());
		}finally {
			sqlSession.close();
		}
	}
	
	@Test
	public void testInsertList() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			List<SysUser> userList = new ArrayList<>();
			for(int i = 0; i < 2; i++) {
				SysUser user = new SysUser();
				user.setUserName("test" + i);
				user.setUserPassword("123456");
				user.setUserEmail("test@mybatis.tk");
				userList.add(user);
			}
			int result = userMapper.insertList(userList);
			for(SysUser user : userList) {
				System.out.println(user.getId());
			}
			Assert.assertEquals(2, result);
		}finally {
			sqlSession.rollback();
			sqlSession.close();
		}
	}
	
	@Test
	public void testUpdateByMap() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			Map<String, Object> map = new HashMap<>();
			map.put("id", 1L);
			map.put("user_email", "test@mybatis.tk");
			map.put("user_password", "12345678");
			userMapper.updateByMap(map);
			SysUser user = userMapper.selectById(1L);
			Assert.assertEquals("test@mybatis.tk", user.getUserEmail());
		}finally {
			sqlSession.rollback();
			sqlSession.close();
		}
	}
	
	@Test
	public void testSelectUserAndRoleById() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
//			SysUser user = userMapper.selectUserAndRoleById(1001L);
			SysUser user = userMapper.selectUserAndRoleById2(1001L);
			Assert.assertNotNull(user);
			Assert.assertNotNull(user.getRole());
		}finally {
			sqlSession.close();
		}
	}
	
	@Test
	public void testSelectUserAndRoleByIdSelect() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			SysUser user = userMapper.selectUserAndRoleByIdSelect(1001L);
			Assert.assertNotNull(user);
			System.out.println("调用user.getRole()===");
			Assert.assertNotNull(user.getRole());
		}finally {
			sqlSession.close();
		}
	}
	
}
