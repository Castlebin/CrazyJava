package ch18.se06;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

public class GenericTest {
	
	private Map<String, Integer> score;
	
	private int age;
	
	private Integer a;
	
	public static void main(String[] args) throws Exception {
		Class<?> clazz = GenericTest.class;
		Field f = clazz.getDeclaredField("score");
		// 直接只能获取类型信息，不能获取泛型信息
		Type t = f.getType();
		System.out.println(t);
		
		Type gt = f.getGenericType();
		// 如果gt是一个参数化类型的实例
		if(gt instanceof ParameterizedType) {
			ParameterizedType p = (ParameterizedType)gt;
			// 获取原始类型
			Type[] paramTypes = p.getActualTypeArguments();
			System.out.println("泛型信息：");
			for(Type paramType : paramTypes) {
				System.out.println(paramType);
			}
		}
		
		System.out.println(clazz.getDeclaredField("age").getGenericType());
		System.out.println(clazz.getDeclaredField("a").getGenericType());
	}

	public Map<String, Integer> getScore() {
		return score;
	}

	public void setScore(Map<String, Integer> score) {
		this.score = score;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Integer getA() {
		return a;
	}

	public void setA(Integer a) {
		this.a = a;
	}
	
}
