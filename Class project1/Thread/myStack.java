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
		myStack stack = new myStack();
		Scanner s1 = new Scanner(System.in);
		char s;
		boolean flag = true;
		
		while(flag) {
			System.out.println("push or pop?");
			if(s1.nextLine().equals("push")) {
				System.out.println("input: ");
				s = s1.next().charAt(0);
				stack.push(s);
			}
			else if (s1.nextLine().equals("pop")) {
				System.out.println(stack.pop());
			}
			else {
				flag = false;
			}
		}
		s1.close();
	}
}
