package ch18.se05.dynaproxy;

import java.lang.reflect.Proxy;


public class MyProxtFactory {
	public static Object getProxy(Object target) {
		MyInvocationHandler handler = new MyInvocationHandler(target);
		handler.setTarget(target);
		
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), handler);
	}
}
