package ch15.se08.resolve;

import java.io.Serializable;

// 我们在反序列化Orientation_1会发现反序列化出来的HORIZONTAL、VERTICAL和原来的值不一样了
// (也说明反序列化确实是不通过构造器来创建对象的（因为我们看到本类中构造器是私有的，不可能被外部使用）)
public class Orientation_1 implements Serializable {
	public static final Orientation_1 HORIZONTAL = new Orientation_1(1);
	public static final Orientation_1 VERTICAL = new Orientation_1(2);
	
	private int value;
	
	private Orientation_1(int value) {
		this.value = value;
	}
}
