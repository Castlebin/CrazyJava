package ch16.se03;

public class InvokeRun extends Thread {

	private int i;
	
	@Override
	public void run() {
		for(; i<100; i++) {
			// 直接调用run方法时，Thread的this.getName()方法返回的是该对象的名字
			// 而Thread.currentThread().getName()总是返回当前线程的名字
			System.out.println(Thread.currentThread().getName() + " " +i);
		}
	}
	
	public static void main(String[] args) {
		for(int i=0; i<100; i++) {
			System.out.println("main: "+Thread.currentThread().getName() + " " +i);
			if(i == 20) {
				// 直接调用run方法，系统会把这当做普通的方法调用，而不是线程体执行
				// 所以下面的两行代码并不会启动两个线程，而是依次执行两个run方法而已
				// 从得到的线程名称都是main，我们就可以看出
				new InvokeRun().run();
				new InvokeRun().run();
			}
		}
		
		System.out.println("==========================");
		// 必须要指出的是，调用了线程对象的run方法之后，线程就不再处于新建状态了，就不要再调用它的start方法来启动该线程了，
		// 不会启动的，当然，也不会报错(Java 8)，只是不能启动而已
		Thread t1 = new InvokeRun();
		t1.run();
		t1.start(); // 无效，不会执行线程体
		
	//	t1.start();	// 猜猜看，把这行打开会怎样(像普通一样，仍旧会引发IllegalThreadStateException)
	}
	
}
