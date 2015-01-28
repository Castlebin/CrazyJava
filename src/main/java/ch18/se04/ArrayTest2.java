package ch18.se04;

import java.lang.reflect.Array;

public class ArrayTest2 {
	
	public static void main(String[] args) {
		// 创建一个三维数组
		Object arr = Array.newInstance(String.class, 3, 4, 10);
		
		// 获取arr数组中index为2的元素，该元素为二维数组
		Object arrObj = Array.get(arr, 2);
		// 二维数组的元素是一维数组
		// 为一维数组赋值
		Array.set(arrObj, 1, new String[] {"彩虹沙漠", "晴朗", "温暖"});
		
		Object arrArrObj = Array.get(arrObj, 3);
		Array.set(arrArrObj, 8, "星空");
		
		// 强制转换为三维数组
		String[][][] cast = (String[][][])arr;
		System.out.println(cast[2][1][0]);
		System.out.println(cast[2][1][1]);
		System.out.println(cast[2][1][2]);
		
		System.out.println(cast[2][3][8]);
		System.out.println(cast[2][3][9]);
	}

}
