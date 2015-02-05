package ch16.se04;

//Thread类的静态方法yield()
public class YieldTest extends Thread {
	public YieldTest(String name) {
		super(name);
	}
	
	@Override
	public void run() {
		for(int i=0;i<50;i++) {
			System.out.println(getName()+" " + i);
			if(i == 20) {
				System.out.println(getName() + "yield()");
				Thread.yield();
			}
		}
	}
	
	// 如果在多CPU机器上，可能观察到的结果并不明显，因为多个CPU就意味着本省同时就可以并行执行多个线程
	public static void main(String[] args) {
		// 启动两个并发线程
		YieldTest yt1 = new YieldTest("高优先级线程");
		yt1.setPriority(Thread.MAX_PRIORITY);
		YieldTest yt2 = new YieldTest("低优先线程");
		yt2.setPriority(Thread.MIN_PRIORITY);
		
		yt1.start();
		yt2.start();
	}
}
