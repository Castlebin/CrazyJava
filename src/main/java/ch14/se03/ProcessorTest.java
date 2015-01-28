package ch14.se03;

import java.lang.reflect.Method;

public class ProcessorTest {
	
	public static void process(String clazz) throws SecurityException, ClassNotFoundException {
		int passed = 0;
		int failed = 0;
		
		for(Method m : Class.forName(clazz).getMethods()) {
			// 如果该方法使用了Testable注解修饰
			if(m.isAnnotationPresent(Testable.class)) {
				try {
					// 本测试中都是static方法，所以直接调用
					m.invoke(null);
					passed++;
				} catch(Exception e) {
					System.out.println("方法：" + m + "运行失败，异常：" + e.getCause());
					failed++;
				}
			}
		}
		
		System.out.println("共运行了：" +(passed+failed)+"个测试方法，其中：\n\t成功了：" + passed+"个，失败了：" + failed+"个！");
	}
	
}
