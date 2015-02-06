package ch16.se06;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueTest {
	public static void main(String[] args) throws InterruptedException {
		// 定义一个长度为2的BlockingQueue
		BlockingQueue<String> bq = new ArrayBlockingQueue<>(2);
		
		bq.put("Java");
		bq.put("JavaEE");
		System.out.println("--");
		bq.put("Android");	// 阻塞队列已满，无法向里面继续put放入数据，程序阻塞
		System.out.println("-----");
	}
}
