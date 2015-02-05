package ch16.se05.synchronizedMethod;

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
		account.draw(drawAmount);
	}
}
