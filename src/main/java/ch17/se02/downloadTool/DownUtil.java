package ch17.se02.downloadTool;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DownUtil {
	private URL url;
	private String targetFile;	// 想存放在本地的路径URL
	private int threadNum;	// 使用多少个线程来进行下载
	private DownThread[] threads;	// 定义用来现在的线程数组
	private int fileSize;	// 要下载的文件的总大小
	
	public DownUtil(String path, String targetFile, int threadNum) throws MalformedURLException {
		this.url = new URL(path);
		this.targetFile = targetFile;
		this.threadNum = threadNum;
		this.threads = new DownThread[threadNum];
	}
	
	public void download() throws IOException {
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setConnectTimeout(5 * 1000);
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Connection", "Keep-Alive");
		
		// 得到文件大小
		fileSize = conn.getContentLength();
		conn.disconnect();
		
		int currentPartSize = fileSize/threadNum + 1;
		// 创建一个RandomAccessFile实例代表下载到本地的文件所对应的Java对象
		RandomAccessFile file = new RandomAccessFile(targetFile, "rw");
		file.setLength(fileSize);	// 设置文件大小
		file.close();
		
		// 创建下载线程并为每个线程分配它对应该下载的文件部分，然后启动线程，开始下载
		for(int i=0; i<threadNum; i++) {
			// 计算线程开始下载的位置
			int startPos = i * currentPartSize;
			// 创建下载线程并开始下载
			threads[i] = new DownThread(startPos, currentPartSize);
			threads[i].start();
		}
	}
	
	// 统计已下载的百分比
	public double getCompleteRate() {
		int sumSize = 0;
		for(int i=0; i<threadNum; i++) {
			sumSize += threads[i].alreadyDownSize;
		}
		
		return sumSize * 1.0 / fileSize;
	}
	
	private class DownThread extends Thread {
		private int startPos;
		private int currentPartSize;
		public int alreadyDownSize;

		public DownThread(int startPos, int currentPartSize) {
			this.startPos = startPos;
			this.currentPartSize = currentPartSize;
		}
		
		@Override
		public void run() {
			try {
				HttpURLConnection conn = (HttpURLConnection)url.openConnection();
				conn.setConnectTimeout(5 * 1000);
				conn.setRequestMethod("GET");
				
				InputStream inStream = conn.getInputStream();
				// 跳到自己负责下载的开始位置
				inStream.skip(startPos);
				// 自己负责下载的部分
				RandomAccessFile currentPart = new RandomAccessFile(targetFile, "rw");
				// 跳到自己开始写文件的位置
				currentPart.seek(startPos);
				
				// 创建一个大小为1024byte的缓存
				byte[] buffer = new byte[1024];
				int hasRead = 0;
				while( alreadyDownSize<currentPartSize
						&& (hasRead=inStream.read(buffer)) != -1) {
					currentPart.write(buffer, 0, hasRead);
					alreadyDownSize += hasRead;
				}
				
				currentPart.close();
				inStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
