package HappyFarm;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner s1 = new Scanner(System.in);
		
		System.out.println("======== ");
	}
}

class FarmObj{
	boolean flag;
	String attitude;
	String sort;
	int num;
	void attitude() {
		if (flag) {
			System.out.println("Alive");
		}
		else {
			System.out.println("Dead");
		}
	}
}

class Wheat extends FarmObj{
	
}

class Corn extends FarmObj{
	
}

class Chicken extends FarmObj{
	
}

class Cow extends FarmObj{
	
}

class Sheep extends FarmObj{
	
}
