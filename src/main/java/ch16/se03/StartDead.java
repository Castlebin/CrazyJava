package ch16.se03;

public class StartDead extends Thread {
	
	private int i;
	
	@Override
	public void run() {
		for(; i<100;i++) {
			System.out.println(getName()+" " + i);
		}
	}
	
	public static void main(String[] args) {
		StartDead sd = new StartDead();
		for(int i=0; i<100; i++) {
			System.out.println(Thread.currentThread()+" " + i);
			if(i == 20) {
				System.out.println(sd.isAlive());
				sd.start();
				System.out.println(sd.isAlive());
			}
			
			// 当线程处于新建、死亡这两种状态时，isAlive()方法返回false
			// 死亡的线程不能再次被调用
			if(i > 20 && !sd.isAlive()) {
				sd.start();// IllegalThreadStateException
			}
		}
	}

}
