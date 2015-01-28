package ch18.beanutils;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;
import org.junit.Test;

// 使用beanutils操作bean的属性
public class Demo1 {
	
	@Test
	public void test1() throws IllegalAccessException, InvocationTargetException {
		Person p = new Person();
		BeanUtils.setProperty(p, "name", "loveishard");
		System.out.println(p.getName());
	}
	
	@Test
	public void test2() throws IllegalAccessException, InvocationTargetException {
		String name = "heller";
		String password = "123456";
		String age = "18";
		Person p = new Person();
		BeanUtils.setProperty(p, "name", name);
		BeanUtils.setProperty(p, "password", password);
		BeanUtils.setProperty(p, "age", age);	// beanutils支持String到八种基本数据类型的自动转换
		BeanUtils.setProperty(p, "alive", "true");
		
		System.out.println(p.getName());
		System.out.println(p.getPassword());
		System.out.println(p.getAge());
		System.out.println(p.isAlive());
		
		String birthday = "1987-12-10";// 不支持(DateConverter does not support default String to 'Date' conversion)
		BeanUtils.setProperty(p, "birthday", birthday);
	}
	
	// 注册类型转换器让beanutils支持其他类型的转换
	@Test
	public void test3() throws IllegalAccessException, InvocationTargetException {
		Person p = new Person();
		String birthday = "1987-12-10";
		
		// 为BeanUtils注册类型转换器
		ConvertUtils.register(new Converter() {
			@Override
			public Object convert(Class paramClass, Object paramObject) {
				if(paramObject == null) {
					return null;
				}
				if(!(paramObject instanceof String)) {
					throw new ConversionException("只支持String类型的转换！");
				}
				if(((String)paramObject).trim().equals("")) {
					return null;
				}
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					return sf.parse(birthday);
				} catch (ParseException e) {
					throw new RuntimeException(e);
				}
			}
		}, Date.class);
		
		BeanUtils.setProperty(p, "birthday", birthday);
		System.out.println(p.getBirthday());
	}
	
	@Test
	public void test4() throws IllegalAccessException, InvocationTargetException {
		Person p = new Person();
		String birthday = "1980-09-09";// 这个是中文格式，所以，本测试用例在中文操作系统上会通过，但其他不能
		
		// 为BeanUtils注册类型转换器，使用的是Local格式的日期转换器，所以格式必须符合Locale格式，否则报错
		// 不幸的是我使用的就是英文的操作系统，555555~
		
		// 但是内置的这个日期转换器是有问题的，不够健壮，不能处理空值
		ConvertUtils.register(new DateLocaleConverter(), Date.class);
		BeanUtils.setProperty(p, "birthday", birthday);
		System.out.println(p.getBirthday());
	}
	
	@Test
	public void test5() throws IllegalAccessException, InvocationTargetException {
		Map<String, String> map = new HashMap<>();
		map.put("name", "heller");
		map.put("password", "123456");
		map.put("age", "20");
		map.put("alive", "true");
		
		Person p = new Person();
		// 使用Beanutils填充对象
		BeanUtils.populate(p, map);
		
		System.out.println(p.getName());
		System.out.println(p.getPassword());
		System.out.println(p.getAge());
		System.out.println(p.isAlive());
	}

}
