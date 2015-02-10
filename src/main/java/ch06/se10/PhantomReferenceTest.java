package ch06.se10;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

public class PhantomReferenceTest {
	public static void main(String[] args) {
		// 创建一个引用队列
		ReferenceQueue<String> rq = new ReferenceQueue<>();
		
		String str = new String("疯狂Java讲义");
		
		// 创建一个str的虚引用，必须绑定到一个引用队列上
		PhantomReference<String> pr = new PhantomReference<String>(str, rq);
		
		// 好了，现在切断str和字符串“疯狂Java讲义”之间的强引用
		str = null;
		
		// 试图取出虚引用所引用的对象（无效，并不能使用虚引用来获取被引用的对象，所以这里输出的是null）
		System.out.println(pr.get());
		
		// 现在强制进行垃圾回收
		System.gc();
		System.runFinalization();
		
		// 垃圾回收后，虚引用将被放入它创建时关联的引用队列中
		// 取出引用队列中最先进入的元素和原来的pr进行对比即可证明（它们俩相等）
		System.out.println(rq.poll() == pr); // true
	}
}
