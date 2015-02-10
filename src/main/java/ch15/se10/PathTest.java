package ch15.se10;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathTest {
	public static void main(String[] args) {
		Path path = Paths.get(".");
		System.out.println("path里包含的路径数量：" + path.getNameCount());
		
		System.out.println("path的根路径：" + path.getRoot());
		
		// 获取path的绝对路径
		System.out.println("path的绝对路径：" + path.toAbsolutePath());
		
		// 获取绝对路径的根路径
		System.out.println("path绝对路径的根路径：" + path.toAbsolutePath().getRoot());
		// 获取绝对路径所包含的路径数量
		System.out.println("path的绝对路径里包含的路径数量：" + path.toAbsolutePath().getNameCount());
		System.out.println(path.toAbsolutePath().getName(3));
		
		// 以多个String来构建Path对象
		Path path2 = Paths.get("src", "main", "java");
		System.out.println(path2);
	}
}
