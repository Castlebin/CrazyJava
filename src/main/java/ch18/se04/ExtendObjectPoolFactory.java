package ch18.se04;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ExtendObjectPoolFactory {
	
	private Map<String, Object> objectPool = new HashMap<>();
	
	private Properties config = new Properties();
	
	public void init(String fileName) {
		try(FileInputStream fin = new FileInputStream(fileName)) {
			config.load(fin);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 定义一个简单的创建对象的方法，传入一个类名称，根据类名称调用默认的无参构造器来创建一个对象
	public Object createObject(String clazzName) throws ClassNotFoundException, 
			InstantiationException, IllegalAccessException {
		Class<?> clazz = Class.forName(clazzName);
		return clazz.newInstance();
	}
	
	// 初始化对象池
	public void initPool() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		for(String name : config.stringPropertyNames()) {
			if(!name.contains("%")) {
				objectPool.put(name, createObject(config.getProperty(name)));
			}
		}
	}
	
	public Object getObject(String name) {
		return objectPool.get(name);
	}
	
	// 调用setter方法来对对象池中的对象的属性进行赋值，目前比较简单，只接受String类型的属性的初始化
	public void initProperty() throws NoSuchMethodException, SecurityException, IllegalAccessException, 
			IllegalArgumentException, InvocationTargetException {
		for(String name : config.stringPropertyNames()) {
			if(name.contains("%")) {
				// 将value按%进行分割
				String[] objAndProp = name.split("%");
				// 取出需要调用setter方法的参数值
				Object target = getObject(objAndProp[0]);
				String prop = objAndProp[1];
				// 拼出setter方法的方法名
				String mtdName = "set" + prop.substring(0, 1).toUpperCase() + prop.substring(1);
				Method mtd = target.getClass().getMethod(mtdName, String.class);
				mtd.invoke(target, config.getProperty(name));
			}
		}
	}
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, 
			IllegalAccessException, NoSuchMethodException, SecurityException, 
			IllegalArgumentException, InvocationTargetException {
		ExtendObjectPoolFactory ef = new ExtendObjectPoolFactory();
		ef.init("extObj.properties");
		ef.initPool();
		ef.initProperty();
		System.out.println(ef.getObject("a"));
		System.out.println(ef.getObject("b"));
	}
}
