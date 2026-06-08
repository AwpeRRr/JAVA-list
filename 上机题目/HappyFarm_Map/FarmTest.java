package HappyFarm_Map;

import java.util.Scanner;

public class FarmTest {
    private static Farm farm = new Farm();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        initFarm(scanner);

        boolean running = true;
        while (running) {
            showMenu();
            System.out.print("请选择操作：");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    resetFarm(scanner);
                    break;
                case "2":
                    createFarmObject(scanner);
                    break;
                case "3":
                    farm.printAllObjects();
                    break;
                case "4":
                    searchByName(scanner);
                    break;
                case "5":
                    careForObject(scanner);
                    break;
                case "6":
                    removeByPosition(scanner);
                    break;
                case "7":
                    clearFarm(scanner);
                    break;
                case "0":
                    running = false;
                    System.out.println("感谢使用简单开心农场管理系统，再见！");
                    break;
                default:
                    System.out.println("输入有误，请重新选择！");
            }
        }

        scanner.close();
    }

    private static void showMenu() {
        System.out.println("\n========== 简单开心农场管理系统 Map 版 ==========");
        System.out.println("1. 重新初始化农场行数");
        System.out.println("2. 创建并添加农场对象");
        System.out.println("3. 输出所有农场对象和对象总数");
        System.out.println("4. 根据名称查找农场对象");
        System.out.println("5. 照料指定位置的农场对象");
        System.out.println("6. 删除指定位置的农场对象");
        System.out.println("7. 清空农场对象");
        System.out.println("0. 退出系统");
    }

    private static void initFarm(Scanner scanner) {
        int rowCount;
        while (true) {
            rowCount = readInt(scanner, "请输入农场行数: ");
            if (rowCount > 0) {
                break;
            }
            System.out.println("农场行数必须大于 0！");
        }

        farm.initFarm(rowCount);
        System.out.println("农场初始化完成，共 " + rowCount + " 行，行号 index 范围为 0 到 " + (rowCount - 1) + "。");
    }

    private static void resetFarm(Scanner scanner) {
        System.out.print("重新初始化会清空当前农场，是否继续？(y/n): ");
        String answer = scanner.nextLine();
        if (answer.equalsIgnoreCase("y")) {
            initFarm(scanner);
        } else {
            System.out.println("已取消重新初始化。");
        }
    }

    private static void createFarmObject(Scanner scanner) {
        System.out.println("请选择要创建的对象类型: 1.小麦 2.玉米 3.鸡 4.牛");
        String typeChoice = scanner.nextLine();

        int id = readPositiveInt(scanner, "请输入对象编号: ");
        if (farm.hasId(id)) {
            System.out.println("编号 " + id + " 已存在，添加失败！");
            return;
        }

        System.out.print("请输入对象名称: ");
        String name = scanner.nextLine().trim();
        if (name.length() == 0) {
            System.out.println("对象名称不能为空，添加失败！");
            return;
        }

        int rowIndex = readInt(scanner, "请输入要添加到的行号 index（0 到 " + (farm.getRowCount() - 1) + "）: ");
        if (!farm.isValidRowIndex(rowIndex)) {
            System.out.println("行号不存在，添加失败！");
            return;
        }

        FarmObject newObj = createObjectByType(typeChoice, id, name);
        if (newObj == null) {
            System.out.println("无效的类型选择，添加失败！");
            return;
        }

        if (!farm.addObject(newObj, rowIndex)) {
            System.out.println("添加失败，请检查行号或编号是否重复。");
            return;
        }
        int positionIndex = farm.getRowSize(rowIndex) - 1;
        System.out.println("添加成功！" + newObj);
        System.out.println("所在位置: 第 " + rowIndex + " 行，第 " + positionIndex + " 个位置。");
    }

    private static FarmObject createObjectByType(String typeChoice, int id, String name) {
        switch (typeChoice) {
            case "1":
                return new Wheat(id, name);
            case "2":
                return new Corn(id, name);
            case "3":
                return new Chicken(id, name);
            case "4":
                return new Cow(id, name);
            default:
                return null;
        }
    }

    private static void searchByName(Scanner scanner) {
        System.out.print("请输入要查找的名称: ");
        String name = scanner.nextLine().trim();
        if (name.length() == 0) {
            System.out.println("名称不能为空！");
            return;
        }
        farm.searchByName(name);
    }

    private static void careForObject(Scanner scanner) {
        int rowIndex = readInt(scanner, "请输入行号 index: ");
        int positionIndex = readInt(scanner, "请输入位置编号 index: ");
        FarmObject obj = farm.getObject(rowIndex, positionIndex);

        if (obj == null) {
            printPositionNotFound(rowIndex, positionIndex);
            return;
        }

        System.out.println("准备照料: " + obj);
        obj.care();
    }

    private static void removeByPosition(Scanner scanner) {
        int rowIndex = readInt(scanner, "请输入要删除对象的行号 index: ");
        int positionIndex = readInt(scanner, "请输入要删除对象的位置编号 index: ");
        FarmObject removed = farm.removeObject(rowIndex, positionIndex);

        if (removed == null) {
            printPositionNotFound(rowIndex, positionIndex);
            return;
        }

        System.out.println("删除成功: " + removed);
    }

    private static void clearFarm(Scanner scanner) {
        System.out.print("确定要清空农场中的所有对象吗？(y/n): ");
        String answer = scanner.nextLine();
        if (answer.equalsIgnoreCase("y")) {
            farm.clearFarm();
            System.out.println("农场已清空。");
        } else {
            System.out.println("已取消清空。");
        }
    }

    private static void printPositionNotFound(int rowIndex, int positionIndex) {
        if (!farm.isValidRowIndex(rowIndex)) {
            System.out.println("第 " + rowIndex + " 行不存在。");
        } else {
            System.out.println("第 " + rowIndex + " 行中不存在位置 " + positionIndex + "。当前该行对象数量: " + farm.getRowSize(rowIndex));
        }
    }

    private static int readPositiveInt(Scanner scanner, String prompt) {
        int value;
        while (true) {
            value = readInt(scanner, prompt);
            if (value > 0) {
                return value;
            }
            System.out.println("请输入大于 0 的整数！");
        }
    }

    private static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                System.out.println("请输入有效的整数！");
            }
        }
    }
}
