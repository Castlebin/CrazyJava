package ch14.se01;

@FunctionalInterface
public interface FunInterface {
	static void foo() {
		System.out.println("static foo 方法");
	}
	
	default void bar() {
		System.out.println("default bar方法");
	}
	
	void test();	// 只定义一个抽象方法
}
