package ch16.se05.synchronizedMethod;

// 使用了同步方法来保证了对balance的修改是线程安全的
public class Account {
	private String accountNo;
	private double balance;
	
	// 同步方法，用来进行取款操作
	public synchronized void draw(double drawAmount) {
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
		} // 同步方法块执行完毕，线程自动释放对同步监视器的锁定
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
