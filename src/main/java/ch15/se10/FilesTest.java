package ch15.se10;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FilesTest {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		// 复制文件
		Files.copy(Paths.get("src/main/java/ch15/se10/FilesTest.java"), new FileOutputStream("out.txt"));
		
		// 判断是否为隐藏文件
		System.out.println(Files.isHidden(Paths.get("src/main/java/ch15/se10/FilesTest.java")));
		
		// 一次性读取文件的所有行
		List<String> lines = Files.readAllLines(Paths.get("src/main/java/ch15/se10/FilesTest.java"));
		System.out.println(lines);
		
		// 判断指定文件的大小
		System.out.println("文件大小为：" + Files.size(Paths.get("src/main/java/ch15/se10/FilesTest.java")));
		
		List<String> poem = new ArrayList<>();
		poem.add("水晶潭底银鱼跃");
		poem.add("清徐风中碧杆横");
		
		// 直接将多个字符串内容写入指定文件中
		Files.write(Paths.get("poem.txt"), poem, Charset.forName("UTF-8"));
		
		// 使用Java 8中新增的Stream API列出当前目录下的所有文件和子目录
		Files.list(Paths.get(".")).forEach(path->System.out.println(path));
		// 使用Java 8中新增的Stream API读取文件内容
		Files.lines(Paths.get("src/main/java/ch15/se10/FilesTest.java"), Charset.forName("UTF-8")).forEach(line->System.out.println(line));
		
		FileStore cStore = Files.getFileStore(Paths.get("."));
		// 判断空间
		System.out.println(". 目录共有多少空间：" + cStore.getTotalSpace());
		System.out.println(". 目录还剩多少空间：" + cStore.getUsableSpace());
	}
}
