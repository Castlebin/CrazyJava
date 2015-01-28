package ch18.se05;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface Person {
	void walk();
	void sayHello(String name);
}

class MyInvocationHandler implements InvocationHandler {
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("正在执行的方法：" + method);
		if(args != null) {
			System.out.println("下面是执行该方法所传入的参数：");
			for(Object arg : args) {
				System.out.println(arg);
			}
		} else {
			System.out.println("调用该方法没有实参");
		}
		
		return null;
	}
}

public class ProxyTest {
	public static void main(String[] args) {
		InvocationHandler handler = new MyInvocationHandler();
		Person p = (Person)Proxy.newProxyInstance(Person.class.getClassLoader(), new Class[]{Person.class}, handler);
		p.walk();
		p.sayHello("heller");
	}
}
