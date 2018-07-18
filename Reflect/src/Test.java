
public class Test {

	public static void main(String args[]) throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		Dog target = new GunDog();
		System.out.println(target);
		Dog dog = (Dog) MyProxyFactory.getProxy(target);
		dog.setName("dog");
		System.out.println(dog);
		dog.info();
		System.out.println(dog);
		dog.run();
		System.out.println(dog);
	}
	
}
