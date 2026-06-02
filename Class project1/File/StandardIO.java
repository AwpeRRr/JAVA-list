package File;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class StandardIO {
	public static void main(String[] args) {
		String s;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("input: ");
		
		try {
			s = in.readLine();
			while (!s.equals("exit")) {
				System.out.println("read: " + s);
				s = in.readLine();
			}
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
}
