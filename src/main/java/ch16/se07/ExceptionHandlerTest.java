package ch16.se07;

class MyExceptionHandler implements Thread.UncaughtExceptionHandler {
	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.println(t + " 线程出现了异常：" + e);
	}
}

public class ExceptionHandlerTest {
	public static void main(String[] args) {
		// 为主线程设置一个异常处理器
		Thread.currentThread().setUncaughtExceptionHandler(new MyExceptionHandler());
		int a = 5/0;
		
		System.out.println("程序正常结束！");
	}
}
