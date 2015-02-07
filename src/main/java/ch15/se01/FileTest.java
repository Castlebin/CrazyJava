package ch15.se01;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class FileTest {
	public static void main(String[] args) throws IOException {
		// 以当前路径来创建一个File对象
		File file = new File(".");
		
		// 直接获取文件名，输出一个点（.）
		System.out.println(file.getName());
		// 获取相对路径的父路径可能出错，返回null。因为创建File对象时，使用的为相对路径(.)，导致其实无法获取到父路径
		System.out.println(file.getParent());
		
		// 获取绝对路径
		System.out.println(file.getAbsolutePath());
		// 通过绝对路径对应的File实例可以顺利获取父路径
		System.out.println(file.getAbsoluteFile().getParent());
		
		// 在当前路径下创建一个临时文件
		File tmpFile = File.createTempFile("aaa", ".tmp", file);
		// 指定JVM退出时删除该文件
		tmpFile.deleteOnExit();
		System.out.println(tmpFile.getAbsolutePath());
		
		// 以系统时间为文件名创建新的File对象
		File newFile = new File(System.currentTimeMillis()+"");
		System.out.println("新文件是否存在：" + newFile.exists());// 输出为false
		// 以指定的File实例来在磁盘上创建真实的文件
		newFile.createNewFile();
		System.out.println("新文件现在是否存在：" + newFile.exists());// 输出为false
		
		// 以该newFile对象来创建一个目录（因为该File对象已经在磁盘上以文件形式存在，所以无法再以它来创建一个目录，此操作会失败）
		System.out.println(newFile.mkdir());// 创建失败，返回false
		
		// 使用list()方法列出当前路径下的所有子目录和文件
		String[] fileList = file.list();
		System.out.println(Arrays.toString(fileList));
		
		// 用listRoots()方法获取系统的所有根路径
		System.out.println(Arrays.toString(File.listRoots()));
	}
}
