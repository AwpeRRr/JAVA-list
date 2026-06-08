
import java.util.Scanner;

public class Pat {
	static int ax;
	static int ay;
	static int res(int x, int y, int a, int b) {
		int flag =-1;
		
		for(int i = x; i< x+a+1;i++) {
			for(int j = y; j<y+b+1; j++) {
				if(x==ax && y==ay) {
					flag =0;
				}
			}
		}
		return flag;
	}
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		Pat pat = new Pat();
		
		int num = s.nextInt();
		int[] x = new int[num];
		int[] y = new int[num];
		int[] a = new int[num];
		int[] b = new int[num];
		
		for(int i=0; i<num; i++) {
			
		}
		
		
	}
}
