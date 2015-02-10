package ch15.se10;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class FileVistorTest {
	public static void main(String[] args) throws IOException {
		Files.walkFileTree(Paths.get("."), new SimpleFileVisitor<Path>() {
			
			// 开始访问目录时触发该方法
			@Override
			public FileVisitResult visitFile(Path file,
					BasicFileAttributes attrs) throws IOException {
				System.out.println("正在访问文件：" + file);
				if(file.endsWith("FileVistorTest.java")) {
					System.out.println("找到了目标文件，路径为：" + file.toAbsolutePath());
					return FileVisitResult.TERMINATE;
				}
				
				return FileVisitResult.CONTINUE;
			}
			
			// 访问文件时触发该方法
			@Override
			public FileVisitResult preVisitDirectory(Path dir,
					BasicFileAttributes attrs) throws IOException {
				System.out.println("正在访问路径：" + dir);
				return FileVisitResult.CONTINUE;
			}
			
		});
	}
}
