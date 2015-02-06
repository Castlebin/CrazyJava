package ch16.se08;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

// 继承RecursiveAction来实现“可分解”的任务
class PrintTask extends RecursiveAction {
	// 每个“小任务”最多只打印50个数
	private static final int THRESHOLD = 50;
	
	private int start;
	private int end;
	
	public PrintTask(int start, int end) {
		this.start = start;
		this.end = end;
	}

	@Override
	protected void compute() {
		if((end-start) < THRESHOLD) {
			for(int i=start; i<end; i++) {
				System.out.println(Thread.currentThread().getName()+"->" +i);
			}
		} else {// 表明是一个大任务，将大任务分解为两个小任务！（分而治之）
			int middle = (start + end) / 2;
			// 分成两个小任务
			PrintTask left = new PrintTask(start, middle);
			PrintTask right = new PrintTask(middle, end);
			
			// 并行执行两个“小任务”
			left.fork();
			right.fork();
		}
	}
}

public class ForkJoinPoolTest {
	public static void main(String[] args) throws InterruptedException {
		ForkJoinPool pool = new ForkJoinPool();
		// 向ForkJoinPool中提交可以被分解的任务
		pool.submit(new PrintTask(0, 300));
		pool.awaitTermination(2, TimeUnit.SECONDS);
		
		// 关闭线程池
		pool.shutdown();
	}
}
