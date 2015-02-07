package ch15.se01;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;

public class FilenameFilterTest {
	public static void main(String[] args) {
		File file = new File(".");
		String[] nameList = file.list(new MyFileFilter());
		System.out.println(Arrays.toString(nameList));
	}
}

class MyFileFilter implements FilenameFilter {
	@Override
	public boolean accept(File dir, String name) {// 如果是.java文件或者是目录，则列出
		return name.endsWith(".java") || new File(name).isDirectory();
	}
}
