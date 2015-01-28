package ch18.se02;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

public class ClassLoaderPropTest {
	
	public static void main(String[] args) throws IOException {
		// 获取系统类加载器
		ClassLoader systemLoader = ClassLoader.getSystemClassLoader();
		System.out.println("系统类加载器：" + systemLoader);
		
		// 获取系统类加载器的加载路径
		Enumeration<URL> em1 = systemLoader.getResources("");
		while(em1.hasMoreElements()) {
			System.out.println(em1.nextElement());
		}
		
		// 获取系统类加载器的父类加载器，即扩展类加载器
		ClassLoader extLoader = systemLoader.getParent();
		System.out.println("扩展类加载器：" + extLoader);
		// 获取扩展类加载器的加载路径
		System.out.println("扩展类加载器的加载路径：" + System.getProperty("java.ext.dirs"));
		
		// 获取扩展类加载器的父类加载器，及根类加载器。由于根类加载器并不是由Java实现的，而且程序通常无需直接访问根类加载器，所以这里返回的是null
		System.out.println(extLoader.getParent());
	}

}
