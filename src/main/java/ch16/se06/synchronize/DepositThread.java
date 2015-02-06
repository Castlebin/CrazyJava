package ch16.se06.synchronize;

public class DepositThread extends Thread {
	// 线程要进行取款的账户
	private Account account;
	// 线程要取的钱数
	private double depositAmount;
	
	public DepositThread(String name, Account account, double depositAmount) {
		super(name);
		this.account = account;
		this.depositAmount = depositAmount;
	}
	
	// 重复100次存款操作
	@Override
	public void run() {
		for(int i=0; i<100; i++) {
			account.deposit(depositAmount);
		}
	}
	
}
