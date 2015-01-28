package ch18.se05.dynaproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {
	private Object target;

	public MyInvocationHandler(Object target) {
		this.target = target;
	}


	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		DogUtil du = new DogUtil();
		// 执行第一个方法
		du.method1();
		
		// 执行原本的方法
		Object result = method.invoke(target, args);
		
		// 执行第二个方法
		du.method2();
		
		return result;
	}


	public Object getTarget() {
		return target;
	}

	public void setTarget(Object target) {
		this.target = target;
	}

}
