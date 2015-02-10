package ch15.se10;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.DosFileAttributeView;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.Date;
import java.util.List;

public class AttributeViewTest {
	public static void main(String[] args) throws IOException {
		Path path = Paths.get("hehe.txt");
		// 获取访问文件基本属性的AttributeView
		BasicFileAttributeView basicView = Files.getFileAttributeView(path, BasicFileAttributeView.class);
		// 获取基本属性合集
		BasicFileAttributes basicAttribs = basicView.readAttributes();
		// 访问文件的基本属性
		System.out.println("文件的创建时间：" + new Date(basicAttribs.creationTime().toMillis()));
		System.out.println("文件的最后访问时间：" + new Date(basicAttribs.lastAccessTime().toMillis()));
		System.out.println("文件的最后修改时间：" + new Date(basicAttribs.lastModifiedTime().toMillis()));
		System.out.println("文件大小：" + basicAttribs.size());
		
		// 获取访问文件属主信息的FileOwnerAttributeView
		FileOwnerAttributeView ownerView = Files.getFileAttributeView(path, FileOwnerAttributeView.class);
		// 获取属性
		System.out.println("文件所属用户为：" + ownerView.getOwner());
		
	/*	// 获取系统中guest用户
		UserPrincipal guest = FileSystems.getDefault()
				.getUserPrincipalLookupService()
				.lookupPrincipalByName("guest");
		// 修改用户
		ownerView.setOwner(guest);*/
		
		// 获取访问自定义属性的UserDefinedFileAttributeView
		UserDefinedFileAttributeView userView = Files.getFileAttributeView(path, UserDefinedFileAttributeView.class);
		List<String> attrNames = userView.list();
		// 遍历所有属性
		for(String name : attrNames) {
			ByteBuffer buf = ByteBuffer.allocate(userView.size(name));
			userView.read(name, buf);
			buf.flip();
			String value = Charset.defaultCharset().decode(buf).toString();
			System.out.println(name+" --> " + value);
		}
		
		// 添加一个自定义属性
		userView.write("发行者", Charset.defaultCharset().encode("木叶潇潇"));
		
		// 获取访问DOS属性的
		DosFileAttributeView dosView = Files.getFileAttributeView(path, DosFileAttributeView.class);
		// 将文件设置为只读、隐藏
		dosView.setReadOnly(true);
		dosView.setHidden(true);
	}
}
