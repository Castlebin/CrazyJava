package ch18.se04;

import java.lang.reflect.Array;

public class ArrayTest {
	// java.lang.reflect包下提供了一个Array类，代表所有的数组类型，程序可以通过Array类来动态的创建、操作数组元素
	public static void main(String[] args) {
		// 创建一个元素类型为String，长度为10的数组
		Object arr = Array.newInstance(String.class, 10);
		// 为数组中index为5、6的两个元素依此赋值
		Array.set(arr, 5, "Love is hard");
		Array.set(arr, 6, "爱是难题");
		
		// 依此取出
		System.out.println(Array.get(arr, 5));
		System.out.println(Array.get(arr, 6));
	}
}
