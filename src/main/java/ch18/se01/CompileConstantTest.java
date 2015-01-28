package ch18.se01;

import org.junit.Test;

class MyTest {
	static {
		System.out.println("执行MyTest类的初始化…");
	}
	
	static final String compileConstant = "疯狂Java讲义";
}

class MyTest2 {
	static {
		System.out.println("执行MyTest2类的初始化…");
	}
	
	static final String compileConstant = System.getProperties().toString();
}

public class CompileConstantTest {
	@Test
	public void testCompileConstant() {
		// 访问类的“宏变量”，不会导致类的初始化操作
		System.out.println(MyTest.compileConstant);
	}
	
	@Test
	public void testCompileConstant2() {
		// 不是“宏变量”，所以类正常初始化
		System.out.println(MyTest2.compileConstant);
	}
}
