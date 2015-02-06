package synchronize;

public class WaitTest {
	
	public static void main(String[] args) throws InterruptedException {
		Integer i = 0;
		i.wait();// IllegalMonitorStateException
	}

}
