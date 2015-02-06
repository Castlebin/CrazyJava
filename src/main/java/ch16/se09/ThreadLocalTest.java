package ch16.se09;

class Account {
	// 定义一个ThreadLocal类型变量，该类型变量会在每一个线程中保持一个独立的副本
	private ThreadLocal<String> name = new ThreadLocal<>();
	
	// 初始化name
	public Account(String name) {
		this.name.set(name);
	}
	
	// 设置ThreadLocal变量name的值
	public void setName(String name) {
		this.name.set(name);
	}
	
	// 得到ThreadLocal变量name的值
	public String getName() {
		return this.name.get();
	}
}

class MyThread extends Thread {
	private Account account;

	public MyThread(String name, Account account) {
		super(name);
		this.account = account;
	}
	
	@Override
	public void run() {
		for(int i=0; i<10; i++) {
			if(i==6) {
				// 重设ThreadLocal变量name为当前线程名
				account.setName(getName());
			}
			System.out.println(account.getName() + " 账户的i值：" + i);
		}
	}
}

public class ThreadLocalTest {
	public static void main(String[] args) {
		// 一个Account
		Account acct = new Account("初始名");
		System.out.println(acct.getName());	// 主线程中保持一个ThreadLocal变量值
		
		new MyThread("线程甲", acct).start();// 其他线程中也保持着各自的ThreadLocal变量值
		new MyThread("线程乙", acct).start();// 互不干扰
	}
}
