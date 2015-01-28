package ch18.se03;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;

class Test {
	public void replace(String str, List<Integer> list) {}
}

// 测试Java 8新增的方法参数反射API
public class MethodParameterTest {
	// 编译时使用-parameter选项才能在运行时得到形参名信息，默认编译后是没有这些信息的
	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		// 获取Test类
		Class<?> clazz = Test.class;
		// 获取Test类的带两个参数的replace()方法
		Method replace = clazz.getMethod("replace", String.class, List.class);
		// 获取指定方法的参数个数
		System.out.println("replace方法包含的参数个数为：" + replace.getParameterCount());
		
		// 获取所有的参数信息
		Parameter[] parameters = replace.getParameters();
		int index = 1;
		for(Parameter p : parameters) {
			if(p.isNamePresent()) {
				System.out.println("---第"+index+"个参数的信息：");
				System.out.println("参数名：" + p.getName());
				System.out.println("形参类型：" + p.getType());
				System.out.println("泛型类型：" + p.getParameterizedType());
			}
		}
	}

}
