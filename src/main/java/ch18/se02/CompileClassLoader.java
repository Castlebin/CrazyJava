package ch18.se02;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CompileClassLoader extends ClassLoader {
	
	// 读取一个class文件的内容
	private byte[] getBytes(String filename) throws FileNotFoundException, IOException {
		File file = new File(filename);
		long len = file.length();
		byte[] raw = new byte[(int)len];
		try(
			FileInputStream fin = new FileInputStream(file)) {
			// 一次读取class文件的全部二进制内容
			int r = fin.read(raw);
			if(r != len) {
				throw new IOException("无法读取全部文件：" + r + " != " + len);
			}
			
			return raw;
		}
	}
	
	// 定义编译一个Java源文件的方法
	private boolean compile(String javaFile) throws IOException {
		System.out.println("CompileClassLoader正在编译源文件：" + javaFile + " ...");
		
		// 调用javac命令
		Process p = Runtime.getRuntime().exec("javac " + javaFile);
		try {
			// 其他线程都等待这个线程完成
			p.waitFor();
		} catch(InterruptedException ie) {
			ie.printStackTrace();
		}
		
		// 获取上面编译线程的退出值，判断是否成功完成编译
		int ret = p.exitValue();
		
		// 返回是否成功编译
		return ret == 0;
	}
	
	// 重写ClassLoader类的findClass()方法
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		Class<?> clazz = null;
		// 将包路径中的.替换为文件系统的分隔符/
		String fileStub = name.replace(".", "/");
		String javaFilename = fileStub + ".java";
		String classFilename = fileStub + ".class";
		File javaFile = new File(javaFilename);
		File classFile = new File(classFilename);
		
		// 指定当Java源文件存在，但.class文件不存在，
		// 或者.class文件的形成时间比Java源文件的修改时间晚时
		// 重新编译源文件
		if( (javaFile.exists() && !classFile.exists()) 
				|| (javaFile.lastModified() > classFile.lastModified())) {
			try {
				if(!compile(javaFilename)) {// 如果编译失败
					throw new ClassNotFoundException("ClassNotFoundException: " + javaFilename);
				}
			} catch(IOException ex) {
				ex.printStackTrace();
			}
		}
		
		// class文件存在
		if(classFile.exists()) {
			try {
				byte[] raw = getBytes(classFilename);
				// 调用defineClass()方法，根据二进制数据获得类的Class对象
				clazz = defineClass(name, raw, 0, raw.length);
			} catch(ClassFormatError | IOException e) {
				e.printStackTrace();
			}
		}
		// 如果Class对象为null，表示加载失败
		if(clazz == null) {
			throw new ClassNotFoundException(name);
		}
		
		return clazz;
	}
	
	// java ch18.se02.CompileClassLoader Hello arg1 arg2 arg3	
	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, NoSuchMethodException, SecurityException {
		if(args.length < 1) {
			System.out.println("缺少目标类，请按以下格式运行Java源文件：");
			System.out.println("\t" + "java ch18.se02.CompileClassLoader $CLASSNAME");
		}
		
		// 获取要运行的类
		String progClass = args[0];
		// 剩下的参数作为运行目标类时的参数
		String[] progArgs = new String[args.length - 1];
		System.arraycopy(args, 1, progArgs, 0, progArgs.length);
		
		CompileClassLoader ccl = new CompileClassLoader();
		// 加载需要运行的类
		Class<?> clazz = ccl.loadClass(progClass);
		Method main = clazz.getMethod("main", (new String[0]).getClass());
		Object[] argsArray = {progArgs};
		main.invoke(null, argsArray);
	}

}
