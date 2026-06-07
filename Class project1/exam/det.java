package exam;

import java.util.Scanner;


public class det {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		long a11 = s.nextInt();
		long a12 = s.nextInt();
		long a13 = s.nextInt();
		long a14 = s.nextInt();
		long a21 = s.nextInt();
		long a22 = s.nextInt();
		long a23 = s.nextInt();
		long a24 = s.nextInt();
		long a31 = s.nextInt();
		long a32 = s.nextInt();
		long a33 = s.nextInt();
		long a34 = s.nextInt();
		long a41 = s.nextInt();
		long a42 = s.nextInt();
		long a43 = s.nextInt();
		long a44 = s.nextInt();
		
		long b1 = a11*a22*a33*a44;
		long b2 = a12*a23*a34*a41;
		long b3 = a13*a24*a31*a42;
		long b4 = a14*a21*a32*a43;
		long b5 = a14*a23*a32*a41;
		long b6 = a13*a22*a31*a44;
		long b7 = a12*a21*a34*a43;
		long b8 = a11*a24*a33*a42;
		
		long res = b1+ b2+b3+b4 -b5-b6-b7-b8;
		System.out.println(res);
		s.close();
	}
}
