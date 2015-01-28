package ch18.se02;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Properties;

public class URLClassLoaderTest {
	
	public static Connection conn;
	
	// 需要事先将mysql-connector-java-5.1.18.jar文件放到工程目录下
	public static Connection getConn(String url, String user, String pass) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, IOException {
		if(conn == null) {
			URL[] urls = {new URL("file:mysql-connector-java-5.1.18.jar")};
			URLClassLoader myClassLoader = new URLClassLoader(urls);
			
			// 加载MySQL的JDBC驱动，并创建默认实例
			Driver driver = (Driver)myClassLoader.loadClass("com.mysql.jdbc.Driver").newInstance();
			// 创建一个设置JDBC连接属性的Properties对象
			Properties props = new Properties();
			props.setProperty("user", user);
			props.setProperty("password", pass);
			
			// 调用Driver对象的connect()方法来获取数据库连接
			conn = driver.connect(url, props);
			myClassLoader.close();
		}
		
		return conn;
	}
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, IOException {
		System.out.println(getConn("jdbc:mysql://localhost:3306/crazyjava", "root", "123456"));
	}

}
