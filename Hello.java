public class Hello {
	
	public static void main(String[] args) {
		for(String arg : args) {
			// Windows下此处中文会乱码
			System.out.println("run Hello, arg(参数): " + arg);
		}
	}

}
