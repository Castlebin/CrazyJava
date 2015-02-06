package ch16.se06.synchronize;

public class DrawTest {
	
	public static void main(String[] args) {
		Account acct = new Account("100001", 0);
		
		new DrawThread("取款者1", acct, 300).start();
		new DepositThread("存款者1", acct, 300).start();
		new DepositThread("存款者2", acct, 300).start();
		new DepositThread("存款者3", acct, 300).start();
	}

}
