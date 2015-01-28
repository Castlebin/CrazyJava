package ch18.se02;

import java.net.URL;

public class BootstrapTest {
	
	public static void main(String[] args) {
		URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
		for(URL url : urls) {
			System.out.println(url);
		}
	}

}
