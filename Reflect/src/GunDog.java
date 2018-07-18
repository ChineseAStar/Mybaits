
public class GunDog implements Dog {

	private String name = "";
	
	public void setName(String name) {
		this.name = name;
		System.out.println(name);
	}
	
	@Override
	public void info() {
		// TODO Auto-generated method stub
		System.out.println("I'm " + name);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("sdfafjkl");
	}
	
}
