package ch14.se03;

import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.TYPE_USE)
@interface NotNull {
	
}

public class TypeAnnotaionTest implements @NotNull Serializable {
	private static final long serialVersionUID = -3862581496392919469L;
	
	public static void main(@NotNull String[] args) throws @NotNull Exception {
		Object obj = "哈哈哈哈";
		String str = (@NotNull String) obj;
		System.out.println(str);
	}
}
