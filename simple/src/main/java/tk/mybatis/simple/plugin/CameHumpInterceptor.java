package tk.mybatis.simple.plugin;

import java.sql.Statement;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

/**
 * MyBatis Map类型下划线key转小写驼峰形式
 * @author 星星
 *
 */
@Intercepts(@Signature(
		type = ResultSetHandler.class,
		method = "handleResultSets",
		args = {Statement.class}
))
@SuppressWarnings({"unchecked","rawtypes"})
public class CameHumpInterceptor implements Interceptor {

	@Override
	public Object intercept(Invocation invocation) throws Throwable{
		List<Object> list = (List<Object>) invocation.proceed();
		for(Object object : list) {
			System.out.println(object.getClass());
			if(object instanceof Map) {
				processMap((Map)object);
			}else {
				break;
			}
		}
		return list;
	}
	
	/**
	 * 处理Map类型
	 * 
	 * @param map
	 */
	private void processMap(Map<String, Object> map) {
		System.out.println("22");
		Set<String> keySet = new HashSet<String>(map.keySet());
		for(String key : keySet) {
			if((key.charAt(0) >= 'A'
					&& key.charAt(0) <= 'Z')
					|| key.indexOf("_") >= 0) {
				Object value = map.get(key);
				map.remove(key);
				map.put(underlineToCamelhump(key), value);
			}
		}
	}

	private static String underlineToCamelhump(String inputString) {
		StringBuilder sb = new StringBuilder();

		System.out.println("33");
		boolean nextUpperCase = false;
		for(int i= 0; i< inputString.length(); i++) {
			char c = inputString.charAt(i);
			if(c == '_') {
				if(sb.length() > 0) {
					nextUpperCase = true;
				}
			}else {
				if(nextUpperCase) {
					sb.append(Character.toUpperCase(c));
					nextUpperCase = false;
				}else {
					sb.append(Character.toLowerCase(c));
				}
			}
		}
		return sb.toString();
	}

	@Override
	public Object plugin(Object target) {
		// TODO Auto-generated method stub
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
		// TODO Auto-generated method stub
		
	}
	
}
