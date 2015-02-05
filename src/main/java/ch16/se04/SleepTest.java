package ch16.se04;

import java.util.Date;
// Thread类的静态方法sleep()
public class SleepTest {
	public static void main(String[] args) throws InterruptedException {
		for(int i=0; i<10; i++) {
			System.out.println("当前时间" + new Date());
			// 暂停1s
			Thread.sleep(1000);
		}
	}
}
