package ch16.se06.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {
	// 使用显式的Lock对象来保证同步
	private final Lock lock = new ReentrantLock();
	// 获得Condition对象
	private final Condition condition = lock.newCondition();
	
	// Account本身原来的属性，账户号和余额
	private String accountNo;
	private double balance;
	
	// 旗标
	private boolean flag;

	public Account(String accountNo, double balance) {
		this.accountNo = accountNo;
		this.balance = balance;
	}
	
	// 取款
	public void draw(double drawAmount) {
		// 加锁
		lock.lock();
		try {
			if(!flag) {
				System.out.println(Thread.currentThread().getName()+"阻塞，等待有其他线程往账户："+accountNo+"里存钱");
				condition.await();
			} else {
				System.out.println(Thread.currentThread().getName()+" 取钱：" + drawAmount);
				// 模拟耗时
				Thread.sleep(10);
				
				balance -= drawAmount;
				System.out.println("账户余额为：" + balance);
				
				// 取钱后，将账户状态标记为false，表示没有存款了
				flag = false;
				// 唤醒在当前condition上等待的线程（告诉存款者你可以进行存款操作了，快醒醒）
				condition.signalAll();
			}
		} catch(InterruptedException e) {
			e.printStackTrace();
		} finally { // 记得使用fianlly块来释放锁！！！
			lock.unlock();
		}
	}
	
	// 存款
	public void deposit(double depositAmount) {
		// 加锁(操作完毕记得释放锁。使用lock进行线程同步时，必须由程序显式的进行加锁和解锁)
		lock.lock();
		try {
			if(flag) {
				System.out.println(Thread.currentThread().getName()+"阻塞，等待有其他线程从账户："+accountNo+"里取款");
				condition.await();
			} else {
				System.out.println(Thread.currentThread().getName()+" 存钱：" + depositAmount);
				// 模拟耗时
				Thread.sleep(10);
				
				balance += depositAmount;
				System.out.println("账户余额为：" + balance);
				
				// 取钱后，将账户状态标记为true，标识有存款了
				flag = true;
				// 唤醒在当前condition上等待的线程（告诉存款者你可以进行取款操作了，快醒醒）
				condition.signalAll();
			}
		} catch(InterruptedException e) {
			e.printStackTrace();
		} finally { // 记得使用fianlly块来释放锁！！！
			lock.unlock();
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
