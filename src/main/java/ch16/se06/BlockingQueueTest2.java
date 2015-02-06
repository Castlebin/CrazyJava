package ch16.se06;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class Producer extends Thread {
	private BlockingQueue<String> bq;

	public Producer(String name, BlockingQueue<String> bq) {
		super(name);
		this.bq = bq;
	}
	
	@Override
	public void run() {
		for(int i=0; i<100; i++) {
			try {
				String item =Thread.currentThread().getName() + "产物-" + i;
				System.out.println(Thread.currentThread().getName()+" 试图生产元素: " + item);
				bq.put(item);// 尝试放入元素。如果队列已满，则线程被阻塞
				System.out.println(Thread.currentThread().getName()+" 生产了元素: " + item);
				Thread.sleep(10);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class Consumer extends Thread {
	private BlockingQueue<String> bq;

	public Consumer(String name, BlockingQueue<String> bq) {
		super(name);
		this.bq = bq;
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				System.out.println(Thread.currentThread().getName()+" 试图消费元素");
				String item = bq.take();// 尝试取出元素。阻塞队列里如果没有元素的话，线程阻塞
				System.out.println(Thread.currentThread().getName()+" 消费了元素: " + item);
				Thread.sleep(10);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

public class BlockingQueueTest2 {
	public static void main(String[] args) {
		BlockingQueue<String> bq = new ArrayBlockingQueue<String>(1);
		
		// 启动三个生产者线程
		new Producer("生产者1", bq).start();
		new Producer("生产者2", bq).start();
		new Producer("生产者3", bq).start();
		
		// 启动一个消费者线程
		new Consumer("消费者1", bq).start();
	}
}
