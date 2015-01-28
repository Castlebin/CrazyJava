package ch17.se02.downloadTool;

import java.io.IOException;

public class MultiThreadDownloadTest {
	public static void main(String[] args) throws IOException {
		String path = "http://apache.fayea.com/tomcat/tomcat-8/v8.0.17/bin/apache-tomcat-8.0.17.tar.gz";
		String targetFile = "apache-tomcat-8.0.17.tar.gz";
		final DownUtil downUtil = new DownUtil(path, targetFile, 5);
		downUtil.download();
		
		new Thread(() -> {
			double rate = 0 ;
			while( (rate = downUtil.getCompleteRate()) < 1) {
				// 每隔0.1秒查询一下完成情况
				System.out.println(rate);
				try {
					Thread.sleep(100);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			
			System.out.println("Download Completed!");
		}).start();
	}
}
