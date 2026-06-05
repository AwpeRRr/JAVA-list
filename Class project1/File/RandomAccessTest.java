package File;

import java.io.RandomAccessFile;
import java.io.IOException;

public class RandomAccessTest {
	public static void main(String[] args) throws IOException{
		long filePoint = 0;
		String s;
		RandomAccessFile file = new RandomAccessFile("JAVA-List/Class project1/File/RandomAccessTest.java", "r");
		
		long fileLength = file.length();
		
		while (filePoint < fileLength) {
			s = file.readLine();
			System.out.println(s);
			file.skipBytes(0);
			filePoint = file.getFilePointer();
		}
		
		file.close();
	}
}
