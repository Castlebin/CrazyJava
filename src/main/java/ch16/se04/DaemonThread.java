package ch16.se04;

//后台进程会随着所有前台线程的死亡而自动死亡
public class DaemonThread extends Thread {
	@Override
	public void run() {
		for(int i=0; i<1000; i++) {
			System.out.println(getName()+" " +i);
		}
	}
	
	public static void main(String[] args) {
		DaemonThread dt = new DaemonThread();
		// 调用Thread的setDaemon()方法将线程设置为后台线程
		dt.setDaemon(true);
		// 启动线程
		dt.start();
		
		for(int i=0; i<10; i++) {
			System.out.println(Thread.currentThread().getName()+" " +i);
		}
		
		// 后台进程会随着所有前台线程的死亡而自动死亡
	}
}
