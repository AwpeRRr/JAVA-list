package HappyFarm;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner s1 = new Scanner(System.in);
		
		System.out.println("======== 简单开心农场管理系统 ========");
		System.out.println("1. 创建农场对象");
		System.out.println("2. 根据名称查找农场对象");
		System.out.println("3. 根据类型输出农场对象");
		System.out.println("4. 输出指定编号之前的农场对象");
		System.out.println("5. 照料指定农场对象");
		System.out.println("6. 判断指定农场对象的类型");
		System.out.println("7. 收获或移除农场对象");
		System.out.println("8. 输出所有农场对象");
		System.out.println("0. 退出系统");
		System.out.println("请选择操作：");


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
