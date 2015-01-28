package ch14.se03.deeper;

import java.awt.event.ActionListener;
import java.lang.reflect.Field;

import javax.swing.AbstractButton;

public class ActionListenerInstaller {

	public static void processAnnotation(Object obj) {
		try {
			Class<?> cl = obj.getClass();
			for(Field f :cl.getDeclaredFields()) {
				f.setAccessible(true);
				ActionListenerFor a = f.getAnnotation(ActionListenerFor.class);
				Object fObj = f.get(obj);
				if(a!=null && fObj!=null && fObj instanceof AbstractButton) {
					Class<? extends ActionListener> listenerClass = a.listener();
					// 使用反射来创建listener类的对象
					ActionListener al = listenerClass.newInstance();
					AbstractButton ab = (AbstractButton)fObj;
					// 添加事件处理器
					ab.addActionListener(al);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
