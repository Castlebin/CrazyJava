package ch06.se10;

import java.lang.ref.WeakReference;

public class ReferenceTest {
	public static void main(String[] args) throws InterruptedException {
		// 创建一个字符集对象
		String str = new String("疯狂Java讲义");// 注意这个地方不能直接写str=“疯狂Java讲义”，那样会看不到效果，因为那样的话Java会把字符串放入常量池中，始终都会持有它的强引用
		// 创建一个弱引用，引用到此字符串
		WeakReference<String> wr = new WeakReference<String>(str);
		
		// 好了，现在切断str和“疯狂Java讲义”字符串之间的引用
		str = null;
		
		// 取出弱引用所引用的对象
		System.out.println(wr.get());
		
		// 强制垃圾回收
		System.gc();
		System.runFinalization();
		
		// 再次试图取出弱引用所引用的对象
		System.out.println(wr.get());
	}
}
