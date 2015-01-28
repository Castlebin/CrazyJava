package ch18.introspection;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import org.junit.Test;
/**
 * 内省：用来操作javabean的属性
 *
 */
public class Demo1 {
	
	@Test
	public void testIntrospector1() throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Person p = new Person();
		PropertyDescriptor pd = new PropertyDescriptor("age", Person.class);
		pd.getWriteMethod().invoke(p, 25);
		System.out.println(p.getAge());
		System.out.println(pd.getReadMethod().invoke(p, null));
		System.out.println(pd.getPropertyType());
		
		pd = new PropertyDescriptor("alive", Person.class);
		pd.getWriteMethod().invoke(p, true);
		System.out.println(p.isAlive());
		System.out.println(pd.getReadMethod().invoke(p, null));
		System.out.println(pd.getPropertyType());
	}
	
	@Test
	public void test1() throws IntrospectionException {
		BeanInfo info = Introspector.getBeanInfo(Person.class);
		PropertyDescriptor[] pds =  info.getPropertyDescriptors();
		for(PropertyDescriptor pd : pds) {
			System.out.println(pd.getName());
		}
	}
	
	@Test
	public void test2() throws IntrospectionException {
		BeanInfo info = Introspector.getBeanInfo(Person.class, Object.class);
		PropertyDescriptor[] pds =  info.getPropertyDescriptors();
		for(PropertyDescriptor pd : pds) {
			System.out.println(pd.getName());
		}
	}
	
	@Test
	public void test3() throws IntrospectionException {
		BeanInfo info = Introspector.getBeanInfo(Person.class, Date.class);
		PropertyDescriptor[] pds =  info.getPropertyDescriptors();
		for(PropertyDescriptor pd : pds) {
			System.out.println(pd.getName());
		}
	}

}
