package exception;

import java.util.Date;

public class TestException {
	
	public static void main(String[] args) {
		try {
			System.out.println("Before exception");
			throw new Exception("test Exception");
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Exception Processing...");
		}
		
		System.out.println("Out of Exception block");
		
		Date date = new Date();
		System.out.println(date);
	}

}
