package ch18.se03;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;

// 使用4个注解修饰该类
@SuppressWarnings("unchecked")
@Deprecated
// 2个可重复注解
@Anno
@Anno
public class ClassTest {
	// 私有的构造器
	private ClassTest() {}
	
	// 定义一个有参数的构造器
	public ClassTest(String name) {
		System.out.println("执行ClassTest类的有参构造器，参数name=" + name);
	}
	
	// 定义一个无参的info()方法
	public void info() {
		System.out.println("执行无参的info()方法");
	}
	
	// 定义一个有参数的info()方法
	public void info(String str) {
		System.out.println("执行有参的info()方法，参数str="+str);
	}
	
	// 定义一个测试用的内部类
	class Inner {}
	
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, ClassNotFoundException {
		// 获取ClassTest类对应的Class对象
		Class<?> clazz = ClassTest.class;
		System.out.println(clazz.getName());
		
		// 获取该类的全部构造器
		Constructor<?>[] ctors = clazz.getDeclaredConstructors();
		System.out.println("ClassTest类的全部构造器如下：");
		for(Constructor<?> ctor : ctors) {
			System.out.println(ctor);
		}
		
		Constructor<?>[] publicCtors = clazz.getConstructors();
		System.out.println("ClassTest类的全部public构造器如下：");
		for(Constructor<?> ctor : publicCtors) {
			System.out.println(ctor);
		}
		
		// 获取该类全部的public方法
		Method[] mtds = clazz.getMethods();
		System.out.println("ClassTest类的全部public方法如下：");
		for(Method mtd : mtds) {
			System.out.println(mtd);
		}
		
		// 获取该类的指定方法
		System.out.println("ClassTest类中带有一个字符串参数的info方法为：" + clazz.getMethod("info", String.class));
		
		// 获取该类的全部注解 
		Annotation[] anns = clazz.getAnnotations();
		System.out.println("ClassTest类的全部注解如下：");
		for(Annotation ann : anns) {
			System.out.println(ann);
		}
		
		// 因为@SuppressWarnings是源代码级别的注解，所以在编译后无法获得它的信息
		System.out.println("ClassTest类上的@SuppressWarnings注解为：" + 
				Arrays.toString(clazz.getAnnotationsByType(SuppressWarnings.class)));
		
		// 获取内部类
		Class<?>[] innerClasses = clazz.getDeclaredClasses();
		System.out.println("ClassTest类的所以内部类如下：");
		for(Class<?> c : innerClasses) {
			System.out.println(c);
		}
		
		Class<?> c = Class.forName("ch18.se03.ClassTest$Inner");
		// 获取内部类所在的外部类
		System.out.println("ch18.se03.ClassTest$Inner类所在的外部类为：" + c.getDeclaringClass());
		
		System.out.println("ClassTest类所在的包为：" + clazz.getPackage());
		System.out.println("ClassTest类的父类为：" + clazz.getSuperclass());
	}
}
