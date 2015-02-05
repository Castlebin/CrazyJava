package ch16.se05.notsafe;

// 我们可以看到，出现了线程安全问题
public class DrawTest {
	public static void main(String[] args) {
		 // 要取款的账户
		Account acct = new Account("1000001", 1000);
		
		// 模拟两个线程对账户进行取款操作
		new DrawThread("甲", acct, 800).start();
		new DrawThread("乙", acct, 800).start();
	}
}
