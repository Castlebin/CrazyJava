package ch16.se08;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

// 继承RecurisiveTask来实现“有返回值的、可分解”的任务
// 典型的“分而治之”方法
class CalTask extends RecursiveTask<Integer> {
	// 每个“小任务”最多累加20个数
	private static final int THRESHOLD = 20;
	
	private int arr[];
	private int start;
	private int end;
	
	public CalTask(int[] arr, int start, int end) {
		this.arr = arr;
		this.start = start;
		this.end = end;
	}

	@Override
	protected Integer compute() {
		int sum = 0;
		if((end - start) < THRESHOLD) {// 确实是一个“小任务”，那么开始实际的计算工作
			for(int i=start; i<end; i++) {
				sum += arr[i];
			}
			
			return sum;// 返回计算结果
		} else {// 是一个“大任务”，那么将它分解为两个“小任务”
			int middle = (start + end) / 2;
			CalTask left = new CalTask(arr, start, middle);
			CalTask right = new CalTask(arr, middle, end);
			
			// 并行执行两个“小任务”
			left.fork();
			right.fork();
			
			// 合并两个“小任务”的计算结果
			return left.join() + right.join();
		}
	}
	
}

public class Sum {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		int[] arr = new int[100];
		Random rand = new Random();
		int total = 0;
		// 初始化100个数字元素
		for(int i=0; i<arr.length; i++) {
			int tmp = rand.nextInt(20);
			total += (arr[i]=tmp);	// 对数组元素进行赋值，并且计算出总和
		}
		System.out.println(total);
		
		// 建立ForkJoinPool线程池
		ForkJoinPool pool = new ForkJoinPool();
		// 向ForkJoinPool线程池中提交“可分解”的大任务，并且获取执行结果
		Future<Integer> future = pool.submit(new CalTask(arr, 0, arr.length));
		System.out.println(future.get());
		
		// 不要忘记关闭线程池
		pool.shutdown();
	}
}
