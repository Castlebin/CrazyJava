package ch16.se06.synchronize;

public class DrawThread extends Thread {
	// 线程要进行取款的账户
	private Account account;
	// 线程要取的钱数
	private double drawAmount;
	
	public DrawThread(String name, Account account, double drawAmount) {
		super(name);
		this.account = account;
		this.drawAmount = drawAmount;
	}
	
	// 重复100次取款操作
	@Override
	public void run() {
		for(int i=0; i<100; i++) {
			account.draw(drawAmount);
		}
	}
	
}
