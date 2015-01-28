package ch18.se04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

// 根据配置文件生成一个对象池，相当于一个简单的对象容器
public class ObjectPoolFactory {
	
	// 声明一个Map对象，相当于对象池容器
	private Map<String, Object> objectPool = new HashMap<>();
	
	// 定义一个简单的创建对象的方法，传入一个类名称，根据类名称调用默认的无参构造器来创建一个对象
	public Object createObject(String clazzName) throws ClassNotFoundException, 
			InstantiationException, IllegalAccessException {
		Class<?> clazz = Class.forName(clazzName);
		return clazz.newInstance();
	}
	
	// 根据配置文件来初始化对象池
	public void initPool(String... fileNames) throws FileNotFoundException,
			IOException, ClassNotFoundException, InstantiationException,
			IllegalAccessException {
		Properties props = new Properties();
		for(String fileName : fileNames) {
			props.load(new FileInputStream(fileName));
			for (String name : props.stringPropertyNames()) {
				objectPool.put(name, createObject(props.getProperty(name)));
			}
		}
	}
	
	public Object getObject(String name) {
		return objectPool.get(name);
	}
	
	public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
		ObjectPoolFactory pf = new ObjectPoolFactory();
		pf.initPool("obj.properties", "obj2.properties");
		System.out.println(pf.getObject("a"));
		System.out.println(pf.getObject("b"));
		System.out.println(pf.getObject("c"));
		System.out.println(pf.getObject("d"));
	}

}
