package ch16.se07;

class MyThread extends Thread {
	public MyThread(String name) {
		super(name);
	}
	public MyThread(ThreadGroup group, String name) {
		super(group, name);
	}
	
	@Override
	public void run() {
		for(int i=0; i<20; i++) {
			System.out.println(getName()+"线程的i变量: " + i);
		}
	}
}

public class ThreadGroupTest {
	public static void main(String[] args) {
		// 获取主线程所在的线程组
		ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
		System.out.println("主线程组的名字：" + mainGroup.getName());
		System.out.println("主线程是否为后台线程："+mainGroup.isDaemon());
		
		new MyThread("主线程组的线程").start();
		
		// 新建一个线程组
		ThreadGroup tg = new ThreadGroup("新线程组");
		// 设置为后台线程组
		tg.setDaemon(true);
		System.out.println(tg.getName()+"是否为后台线程组：" + tg.isDaemon());
		
		// 新建两个线程并且加入到后台线程组中
		new MyThread(tg, "tg组线程甲").start();
		new MyThread(tg, "tg组线程乙").start();
	}
}
