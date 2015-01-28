package ch14.se03;

public class MyTest {
	
	@Testable
	public static void m1() {
		System.out.println("m1");
	}
	
	public static void m2() {
		System.out.println("m2");
	}
	
	@Testable
	public static void m3() {
		throw new IllegalArgumentException("m3: 参数出错！");
	}
	
	public static void m4() {
		throw new IllegalArgumentException("m4: 参数出错！");
	}
	
	@Testable
	public static void m5() {
		System.out.println("m5");
	}
	
	public static void m6() {
		System.out.println("m6");
	}
	
}
