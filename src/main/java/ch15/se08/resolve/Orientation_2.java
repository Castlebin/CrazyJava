package ch15.se08.resolve;

import java.io.Serializable;

// 提供readResolve()方法，可以在进行反序列化时使用它的返回值作为反序列化的结果，而不管原来是怎么序列化的
public class Orientation_2 implements Serializable {
	public static final Orientation_2 HORIZONTAL = new Orientation_2(1);
	public static final Orientation_2 VERTICAL = new Orientation_2(2);
	
	private int value;
	
	private Orientation_2(int value) {
		this.value = value;
	}
	
	// 提供readResolve()方法，可以在进行反序列化时使用它的返回值作为反序列化的结果，而不管原来是怎么序列化的
	private Object readResolve() {
		if(value == 1) {
			return HORIZONTAL;
		} else if(value == 2) {
			return VERTICAL;
		}
		
		return null;
	}
}
