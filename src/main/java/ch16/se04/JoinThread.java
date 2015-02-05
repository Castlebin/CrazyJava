package ch16.se04;

public class JoinThread extends Thread {
	// 提供一个构造方法，用于设置该线程的名字
	public JoinThread(String name) {
		super(name);
	}
	
	@Override
	public void run() {
		for(int i=0; i<100; i++) {
			System.out.println(getName()+ " " +i);
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		// 启动子线程
		new JoinThread("新线程").start();
		for(int i=0; i<100; i++) {
			if(i == 20) {
				JoinThread jt = new JoinThread("join进来的线程");
				jt.start();
				
				// 调用join()方法
				// 导致主线程必须等到jt执行完毕才会向下执行
				jt.join();
			}
			
			System.out.println("main: " + Thread.currentThread().getName() + " " + i);
		}
	}
}
