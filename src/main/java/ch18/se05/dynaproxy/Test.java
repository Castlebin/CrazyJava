package ch18.se05.dynaproxy;

public class Test {
	public static void main(String[] args) {
		Dog target = new GunDog();
		Dog dog = (Dog)MyProxtFactory.getProxy(target);
		dog.info();
		dog.run();
	}
}
