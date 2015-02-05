package ch16.se05;

class A {
	public synchronized void foo(B b) throws InterruptedException {
		System.out.println("当前线程名：" + Thread.currentThread().getName() + " 进入了A实例的foo()方法块");
		Thread.sleep(200);
		
		System.out.println("当前线程名： " + Thread.currentThread().getName()+ " 试图调用B实例的last()方法");
		b.last();
	}
	public synchronized void last() {
		System.out.println("进入了A实例的last()方法");
	}
}

class B {
	public synchronized void bar(A a) throws InterruptedException {
		System.out.println("当前线程名：" + Thread.currentThread().getName() + " 进入了B实例的foo()方法块");
		Thread.sleep(200);
		
		System.out.println("当前线程名： " + Thread.currentThread().getName()+ " 试图调用A实例的last()方法");
		a.last();
	}
	public synchronized void last() {
		System.out.println("进入了B实例的last()方法");
	}
}


public class DeadLock implements Runnable {
	A a = new A();
	B b = new B();
	
	public void init() throws InterruptedException {
		Thread.currentThread().setName("主线程");
		// 调用a对象的foo()方法
		a.foo(b);
		System.out.println("进入了主线程之后");
	}
	
	@Override
	public void run() {
		Thread.currentThread().setName("副线程");
		// 调用b对象的bar方法
		try {
			b.bar(a);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("进入了副线程之后");
	}
	
	public static void main(String[] args) throws InterruptedException {
		DeadLock dl = new DeadLock();
		
		new Thread(dl).start();// 副线程
		
		dl.init(); // main线程
	}

}
