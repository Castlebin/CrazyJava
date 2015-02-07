package ch15.se07;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Date;

// RandomAccessFile依然不能用来直接向文件的指定位置开始插入内容，如果这么做的话，实际上是从指定位置开始覆盖后面的文件内容
// 所以，如果想要向文件的指定位置插入内容的话，需要先将指定位置之后的内容保存起来，等待将要插入的内容写入后，再将保存的内容追加到后面
// 这样才能完成向文件指定位置插入内容
public class InsertContent {
	public static void insert(String fileName, long pos, String insertContent) throws IOException {
		File tmp = File.createTempFile("tmp", null);
		tmp.deleteOnExit();
		try(RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
				// 使用临时文件来保存插入点后面的文件内容
				FileOutputStream tmpOut = new FileOutputStream(tmp);
				FileInputStream tmpIn = new FileInputStream(tmp)) {
			// 移动文件指针到要进行插入的位置
			raf.seek(pos);
			
			// 好了，下面将插入点后面的内容放到临时文件中保存好
			byte[] bbuf = new byte[1024];
			int hasRead = 0;
			while((hasRead=raf.read(bbuf)) > 0) {
				// 写入临时文件中
				tmpOut.write(bbuf, 0, hasRead);
			}
			
			// 现在将文件指针重新定位到要插入的位置上
			raf.seek(pos);
			// 追加要插入的内容
			raf.write(insertContent.getBytes());
			
			// 好了，现在可以将临时文件中保存的内容添加到文件后面了
			while((hasRead=tmpIn.read(bbuf)) > 0) {
				raf.write(bbuf, 0, hasRead);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		insert("hehe.txt", 20, "\n这里是插入的内容，开始插入的位置是20  " + new Date()	+ "\n");
	}
}
