package ch16.se02;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ThirdThread {
	
	public static void main(String[] args) {
		// 创建Callable对象
		ThirdThread rt = new ThirdThread();
		FutureTask<Integer> task = new FutureTask<Integer>(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				int i = 0;
				for(; i<100; i++) {
					System.out.println(Thread.currentThread().getName()+" 的循环变量i的值为：" + i);
				}
				
			/*	Thread.sleep(10 * 1000);
				System.out.println(i);*/
				return i;
			}
		});
		
		for(int i=0; i<100; i++) {
			System.out.println(Thread.currentThread().getName()+" 的循环变量i的值为：" + i);
			if(i == 20) {
				// 实质上还是以Callable对象来创建并开启线程
				new Thread(task, "有返回值的线程").start();
			}
		}
		
		try {
			// 获取线程的返回值
			System.out.println("子线程返回值：" + task.get());
			
			// 可以指定线程超时
			Integer retVal = task.get(5, TimeUnit.SECONDS);// if timeout, cause TimeoutException
			
		} catch(InterruptedException | ExecutionException | TimeoutException e) {
			e.printStackTrace();
		}
	}

}
