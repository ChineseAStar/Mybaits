import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvokationHandler implements InvocationHandler {

	private Object target;
	
	public void setTarget(Object target) {
		this.target = target;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		Dog test = new GunDog();
		test.setName("test");
		DogUtil du = new DogUtil();
		du.method1();
		System.out.println(target);
		System.out.println(test);
		Object result = method.invoke(target, args);
		du.method2();
		
		return result;
	}

}
