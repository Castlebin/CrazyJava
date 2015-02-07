package ch15.se03;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriteTest {
	public static void main(String[] args) throws IOException {
		try(FileWriter fw = new FileWriter("poem.txt")) {
			fw.write("我爱你-匿名用户\n");
			fw.write("我爱你\n");
			fw.write("但我不敢说\n");
			fw.write("我怕我说了\n");
			fw.write("就会死\n");
			fw.write("我不怕死\n");
			fw.write("我怕我死了\n");
			fw.write("再没有人像我这样爱你\n");
		}
	}
}
