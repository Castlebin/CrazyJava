package ch14.se03.deeper;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import ch14.se03.deeper.listener.CancelListener;
import ch14.se03.deeper.listener.OKListener;

public class AnnotationTest {
	
	private JFrame mainWin = new JFrame("使用注解来绑定事件监听器");
	
	@ActionListenerFor(listener=OKListener.class)
	private JButton ok = new JButton("确定");
	
	@ActionListenerFor(listener=CancelListener.class)
	private JButton cancel = new JButton("取消");
	
	public void init() {
		JPanel jp = new JPanel();
		jp.add(ok);
		jp.add(cancel);
		mainWin.add(jp);
		
		// 使用处理类来处理
		ActionListenerInstaller.processAnnotation(this);
		
		mainWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWin.pack();
		mainWin.setVisible(true);
	}
	
	public static void main(String[] args) {
		new AnnotationTest().init();
	}
}
