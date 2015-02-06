package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ThreadTimeoutTest {
	public static void main(String[] args) {
		FutureTask<String> future = new FutureTask<String>(new MyThread());
		// 使用FutureTask来包装Callable对象
		// 进一步将FutureTask包装为Thread对象，启动执行
		new Thread(future).start();
		try {
	//		String result = future.get(7, TimeUnit.SECONDS);// 要求线程7秒内返回结果
			String result = future.get(2, TimeUnit.SECONDS);
		} catch(InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
	//		future.cancel(true);
		}
	}
}

class MyThread implements Callable<String> {
	@Override
	public String call() throws Exception {
		// 模拟一个耗时大概5s的任务
		Thread.sleep(5 * 1000);
		
		System.out.println("线程执行结束");
		return "Ok";
	}
	
}