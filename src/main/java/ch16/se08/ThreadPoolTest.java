package ch16.se08;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class MyThread implements Runnable {
	@Override
	public void run() {
		for(int i=0; i<20; i++) {
			System.out.println(Thread.currentThread().getName()+"的i变量：" + i);
		}
	}
}

public class ThreadPoolTest {
	public static void main(String[] args) {
		// 建立一个线程池
		ExecutorService pool = Executors.newFixedThreadPool(6);
		
		// 向线程池中提交两个线程
		pool.submit(new MyThread());
		pool.submit(new MyThread());
		
		// 要记得关闭线程池
		pool.shutdown();
	}
}
