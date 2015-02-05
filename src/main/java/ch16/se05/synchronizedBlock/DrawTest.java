package ch16.se05.synchronizedBlock;

import ch16.se05.notsafe.Account;

// 使用了同步监视器，因此多线程操作是安全的
public class DrawTest {
	public static void main(String[] args) {
		 // 要取款的账户
		Account acct = new Account("1000001", 1000);
		
		// 模拟两个线程对账户进行取款操作
		new DrawThread("甲", acct, 800).start();
		new DrawThread("乙", acct, 800).start();
	}
}
