package Thread;

import java.util.Scanner;

public class myStack {
	private int idx = 0;
	private char[] data = new char[6];
	
	public void push(char c) {
		data[idx] = c;
		idx++;
	}
	
	public char pop() {
		idx--;
		return data[idx];
	}
	
	public static void main(String[] args) {
		Scanner s1 = new Scanner(System.in);
		char s;
		if(s1.nextLine() = "push") {
			
		}
	}
}
