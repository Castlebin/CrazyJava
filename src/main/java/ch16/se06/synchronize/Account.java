package ch16.se06.synchronize;

/*
 * 模拟一个奇特的场景：
 * 某个账户要求取款者和存款者不断的重复存款、取款的操作，而且要求每当存款者将钱存入账户中，取款者立即全部取出这笔钱。
 * 不允许存款者连续两次存款，也不允许取款者连续两次取款
 * */
public class Account {
	
	private String accountNo;
	private double balance;
	
	// 设置一个旗标，用于标识账户中是否有存款（有的话即表示可以取款，没有则表示不可进行取款操作）
	private boolean flag = false;

	public Account(String accountNo, double balance) {
		this.accountNo = accountNo;
		this.balance = balance;
	}
	
	// 取款
	public synchronized void draw(double drawAmount) {
		try {
			if(!flag) {// 如果flag为假，表示没有存款，所以取钱操作应该阻塞，等待有人存款进去
				System.out.println(Thread.currentThread().getName()+"阻塞，等待有其他线程往账户："+accountNo+"里存钱");
				wait();
			} else {
				System.out.println(Thread.currentThread().getName()+" 取钱：" + drawAmount);
				// 模拟耗时
				Thread.sleep(10);
				
				balance -= drawAmount;
				System.out.println("账户余额为：" + balance);
				
				// 取钱后，将账户状态标记为false，表示没有存款了
				flag = false;
				
				// 唤醒在当前同步监视器上等待的线程（告诉存款者你可以进行存款操作了，快醒醒）
				notifyAll();
			}
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	// 存款
	public synchronized void deposit(double depositAmount) {
		try {
			if(flag) {// 账户里面现在有钱，所以存款操作无法进行，必须等待
				System.out.println(Thread.currentThread().getName()+"阻塞，等待有其他线程从账户："+accountNo+"里取走钱");
				wait();
			} else {
				System.out.println(Thread.currentThread().getName()+" 存钱：" + depositAmount);
				// 模拟耗时
				Thread.sleep(10);
				
				balance += depositAmount;
				System.out.println("账户余额为：" + balance);
				
				// 存款后，将账户状态标记为true，表示有存款了，取款线程可以取款了。同时通知取款线程
				flag = true;
				notifyAll();
			}
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
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

}
