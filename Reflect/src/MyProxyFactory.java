import java.lang.reflect.Proxy;

public class MyProxyFactory {

	public static Object getProxy(Object target) throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		MyInvokationHandler handler = new MyInvokationHandler();
		handler.setTarget(Class.forName("GunDog").newInstance());
		return Proxy.newProxyInstance(Class.forName("GunDog").getClassLoader(), 
				Class.forName("GunDog").getInterfaces(), handler);
	}
	
}
