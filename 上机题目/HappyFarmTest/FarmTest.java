package HappyFarmTest;

import java.util.Scanner;

public class FarmTest {
    // 使用常量设置数组最大容量
    private static final int MAX_CAPACITY = 10;
    // 使用数组进行存储
    private static FarmObject[] farm = new FarmObject[MAX_CAPACITY];
    private static int count = 0; // 当前农场中的对象数量

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            showMenu();
            System.out.print("请选择操作：");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    createFarmObject(scanner);
                    break;
                case "2":
                    searchByName(scanner);
                    break;
                case "3":
                    outputByType(scanner);
                    break;
                case "4":
                    outputBeforeId(scanner);
                    break;
                case "5":
                    careForObject(scanner);
                    break;
                case "6":
                    judgeObjectType(scanner);
                    break;
                case "7":
                    harvestOrRemove(scanner);
                    break;
                case "8":
                    outputAll();
                    break;
                case "0":
                    running = false;
                    System.out.println("感谢使用开心农场管理系统，再见！");
                    break;
                default:
                    System.out.println("输入有误，请重新选择！");
            }
        }
        scanner.close();
    }

    // 显示功能菜单
    private static void showMenu() {
        System.out.println("\n========== 开心农场管理系统 ==========");
        System.out.println("1. 创建农场对象");
        System.out.println("2. 根据名称查找农场对象");
        System.out.println("3. 根据类型输出农场对象");
        System.out.println("4. 输出指定编号之前的农场对象");
        System.out.println("5. 照料指定农场对象");
        System.out.println("6. 判断指定农场对象的类型");
        System.out.println("7. 收获或移除农场对象");
        System.out.println("8. 输出所有农场对象");
        System.out.println("0. 退出系统");
    }

    // 1. 创建农场对象
    private static void createFarmObject(Scanner scanner) {
        if (count >= MAX_CAPACITY) {
            System.out.println("农场已满，无法继续添加对象！");
            return;
        }
        System.out.println("请选择要创建的对象类型: 1.小麦 2.玉米 3.鸡 4.牛");
        String typeChoice = scanner.nextLine();
        System.out.print("请输入对象名称: ");
        String name = scanner.nextLine();

        FarmObject newObj = null;
        switch (typeChoice) {
            case "1": newObj = new Wheat(name); break;
            case "2": newObj = new Corn(name); break;
            case "3": newObj = new Chicken(name); break;
            case "4": newObj = new Cow(name); break;
            default: System.out.println("无效的类型选择！"); return;
        }
        
        farm[count] = newObj;
        count++;
        System.out.println("创建成功！" + newObj.toString());
    }

    // 2. 根据名称查找农场对象
    private static void searchByName(Scanner scanner) {
        System.out.print("请输入要查找的名称: ");
        String name = scanner.nextLine();
        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (farm[i].getName().equals(name)) {
                System.out.println("找到对象: " + farm[i].toString());
                found = true;
            }
        }
        if (!found) {
            System.out.println("未找到名称为 '" + name + "' 的对象。");
        }
    }

    // 3. 根据类型输出农场对象
    private static void outputByType(Scanner scanner) {
        System.out.print("请输入要输出的类型关键字(如: 农作物, 动物, 小麦): ");
        String keyword = scanner.nextLine();
        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (farm[i].getType().contains(keyword)) {
                System.out.println(farm[i].toString());
                found = true;
            }
        }
        if (!found) System.out.println("未找到该类型的对象。");
    }

    // 4. 输出指定编号之前的农场对象
    private static void outputBeforeId(Scanner scanner) {
        System.out.print("请输入指定编号: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            boolean found = false;
            for (int i = 0; i < count; i++) {
                if (farm[i].getId() < id) {
                    System.out.println(farm[i].toString());
                    found = true;
                }
            }
            if (!found) System.out.println("没有找到编号小于 " + id + " 的对象。");
        } catch (NumberFormatException e) {
            System.out.println("请输入有效的数字编号！");
        }
    }

    // 5. 照料指定农场对象 (体现多态)
    private static void careForObject(Scanner scanner) {
        System.out.print("请输入要照料的对象编号: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            FarmObject obj = findById(id);
            if (obj != null) {
                obj.care(); // 多态调用
            } else {
                System.out.println("未找到该编号的对象。");
            }
        } catch (NumberFormatException e) {
            System.out.println("请输入有效的数字编号！");
        }
    }

    // 6. 判断指定农场对象的类型 (静态方法)
    private static void judgeObjectType(Scanner scanner) {
        System.out.print("请输入要判断的对象编号: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            FarmObject obj = findById(id);
            if (obj != null) {
                checkInstanceType(obj); // 调用静态方法判断类型
            } else {
                System.out.println("未找到该编号的对象。");
            }
        } catch (NumberFormatException e) {
            System.out.println("请输入有效的数字编号！");
        }
    }

    // 静态方法：对传入的对象实例进行判断
    public static void checkInstanceType(FarmObject obj) {
        System.out.print("对象 [" + obj.getName() + "] 的具体实例类型是: ");
        if (obj instanceof Wheat) {
            System.out.println("Wheat (小麦)");
        } else if (obj instanceof Corn) {
            System.out.println("Corn (玉米)");
        } else if (obj instanceof Chicken) {
            System.out.println("Chicken (鸡)");
        } else if (obj instanceof Cow) {
            System.out.println("Cow (牛)");
        } else {
            System.out.println("未知类型");
        }
    }

    // 7. 收获或移除农场对象 (删除数组中的对象)
    private static void harvestOrRemove(Scanner scanner) {
        System.out.print("请输入要收获/移除的对象编号: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            int indexToRemove = -1;
            for (int i = 0; i < count; i++) {
                if (farm[i].getId() == id) {
                    indexToRemove = i;
                    break;
                }
            }

            if (indexToRemove != -1) {
                System.out.println("成功移除: " + farm[indexToRemove].getName());
                // 将后面的元素向前移位
                for (int i = indexToRemove; i < count - 1; i++) {
                    farm[i] = farm[i + 1];
                }
                farm[count - 1] = null; // 清空最后一位
                count--;
            } else {
                System.out.println("未找到该编号的对象，无法移除。");
            }
        } catch (NumberFormatException e) {
            System.out.println("请输入有效的数字编号！");
        }
    }

    // 8. 输出所有农场对象
    private static void outputAll() {
        if (count == 0) {
            System.out.println("当前农场为空！");
            return;
        }
        System.out.println("--- 当前农场对象列表 ---");
        for (int i = 0; i < count; i++) {
            System.out.println(farm[i].toString());
        }
        System.out.println("------------------------");
    }

    // 辅助方法：根据编号查找对象
    private static FarmObject findById(int id) {
        for (int i = 0; i < count; i++) {
            if (farm[i].getId() == id) {
                return farm[i];
            }
        }
        return null;
    }
}