package ch16.se05.notsafe;

public class Account {
	private String accountNo;
	private double balance;
	
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
