package ch15.se09;

import java.nio.CharBuffer;

public class BufferTest {
	public static void main(String[] args) {
		// 创建Buffer
		CharBuffer buff = CharBuffer.allocate(8);
		System.out.println("capacity = " + buff.capacity());
		System.out.println("limit = " + buff.limit());
		System.out.println("position = " + buff.position());
		
		// 放入数据
		buff.put('a');
		buff.put('b');
		buff.put('c');
		
		System.out.println("加入三个元素后，position = " + buff.position());
		System.out.println("limit: " + buff.limit());
		
		// 调用flip()方法  (flip()方法，为输出数据做准备，即-将position值设为0，从而下面步骤可以从buffer里面读取数据了)
		buff.flip();
		System.out.println("执行flip()后，limit: " + buff.limit());
		System.out.println("position: " + buff.position());
		
		// 取出第一个元素
		System.out.println("第一个元素(position=0): " + buff.get());
		System.out.println("取出第一个元素后，position = " + buff.position());
		
		// 调用clear方法（clear()方法，为重新输入数据做准备，即-将position值设为0）
		buff.clear();
		System.out.println("执行clear()后，limit = " + buff.limit());
		System.out.println("position = " + buff.position());
		
		// 但是其实buffer里面的数据并没有被清除
		System.out.println(buff.get(2));
		// 上一步执行的是绝对读取，现在看看之后position会有什么变化(执行绝对读取不会影响position值)
		System.out.println("执行了绝对读取之后，position = " + buff.position());
	}
}
