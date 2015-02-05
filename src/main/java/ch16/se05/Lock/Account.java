package ch16.se05.Lock;

import java.util.concurrent.locks.ReentrantLock;

// 使用同步锁来保证线程安全，简单、灵活
public class Account {
	// 定义一个锁对象
	private final ReentrantLock lock = new ReentrantLock();
	
	private String accountNo;
	private double balance;
	
	// 同步锁，用来进行取款操作
	public void draw(double drawAmount) {
		// 在进行涉及到线程安全的操作前，必须先获取锁
		lock.lock();
		
		try {
			if(balance >= drawAmount) {
				System.out.println("取钱成功！吐出钞票：" + drawAmount);
				
				// 模拟耗时
				try {
					Thread.sleep(1);
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
				
				balance -= drawAmount;
				System.out.println("取钱完毕！还剩余额为：" + balance);
			} else {
				System.out.println("余额不足！无法取款");
			}
		} finally {// 在final块中显式的释放锁
			lock.unlock();
		}
	}
	
	public Account() {}

	public Account(String accountNo, double balance) {
		this.accountNo = accountNo;
		this.balance = balance;
	}

	// 根据accountNo来重写hashCode()和equals()方法
	@Override
	public int hashCode() {
		return accountNo.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if(obj!=null && obj.getClass()==Account.class) {
			Account target = (Account)obj;
			return target.getAccountNo().equals(accountNo);
		}
		
		return false;
	}

	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
}
