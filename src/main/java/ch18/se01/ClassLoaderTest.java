package ch18.se01;

class Tester {
	static {
		System.out.println("执行 "+Tester.class.getName() + " 类的初始化…");
	}
}

public class ClassLoaderTest {
	public static void main(String[] args) throws ClassNotFoundException {
		ClassLoader cl = ClassLoader.getSystemClassLoader();
		
		// 下面语句仅仅是加载类，不会导致类的初始化操作
		cl.loadClass("ch18.se01.Tester");
		System.out.println("系统加载了Tester类");
		
		// 下面语句才会导致类的初始化
		Class.forName("ch18.se01.Tester");
	}
}
