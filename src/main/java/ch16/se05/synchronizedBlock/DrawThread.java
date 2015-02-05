package ch16.se05.synchronizedBlock;

import ch16.se05.notsafe.Account;

//模拟用户取钱操作
public class DrawThread extends Thread {
	// 操作的账户
	private Account account;
	// 当前取钱线程希望取的钱数目
	private double drawAmount;
	
	public DrawThread(String name, Account account, double drawAmount) {
		super(name);
		this.account = account;
		this.drawAmount = drawAmount;
	}
	
	// 当多个线程修改同一份数据时，将涉及线程安全的问题
	@Override
	public void run() {
		// 使用account对象作为同步监视器
		// 任何线程在进入同步代码块之前，必须先获得对同步监视器的锁定
		
		// 同步代码块中其实就是原来的线程执行体，并没有改变
		synchronized (account) {
			// 只有检查到账户余额大于取钱数目时，才能取钱成功
			if(account.getBalance() > drawAmount) {
				System.out.println("取钱成功！吐出钞票：" + drawAmount);
				
				// 模拟耗时
				try {
					Thread.sleep(1);
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
				
				// 修改余额
				account.setBalance(account.getBalance() - drawAmount);
				System.out.println("取钱完毕！还剩余额为：" + account.getBalance());
			} else {
				System.out.println("余额不足！无法取款");
			} // 同步代码块执行完毕，线程自动释放对同步监视器的锁定
		}
	}
}
