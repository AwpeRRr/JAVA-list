import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Scanner;

public class CalendarToollll {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n========= 日历与星期查询系统 =========");
            System.out.println("1. 根据年份输出全年日历");
            System.out.println("2. 根据日期查询星期几");
            System.out.println("0. 退出程序");
            System.out.print("请输入你的选择 (0/1/2): ");
            
            int choice = scanner.nextInt();

            if (choice == 1) {
                System.out.print("请输入年份 (例如 2024): ");
                int year = scanner.nextInt();
                printYearCalendar(year);
            } else if (choice == 2) {
                System.out.print("请输入年份: ");
                int y = scanner.nextInt();
                System.out.print("请输入月份: ");
                int m = scanner.nextInt();
                System.out.print("请输入日期: ");
                int d = scanner.nextInt();
                printDayOfWeek(y, m, d);
            } else if (choice == 0) {
                System.out.println("程序已退出。");
                break; // 结束循环，退出程序
            } else {
                System.out.println("输入无效，请重新输入！");
            }
        }
        scanner.close();
    }

    /**
     * 功能 1：打印指定年份的全年日历
     */
    private static void printYearCalendar(int year) {
        // 遍历 1 到 12 月
        for (int month = 1; month <= 12; month++) {
            System.out.println("\n------------- " + year + "年 " + month + "月 -------------");
            System.out.println("一\t二\t三\t四\t五\t六\t日");
            
            // 获取该月第一天的日期对象
            LocalDate firstDateOfMonth = LocalDate.of(year, month, 1);
            
            // 获取该月第一天是星期几 (1表示星期一，7表示星期日)
            int firstDayOfWeek = firstDateOfMonth.getDayOfWeek().getValue();
            
            // 获取该月一共有多少天
            int daysInMonth = firstDateOfMonth.lengthOfMonth();

            // 1. 打印第一周前面的空白制表符 (\t)
            for (int i = 1; i < firstDayOfWeek; i++) {
                System.out.print("\t");
            }

            // 2. 循环打印该月的每一天
            for (int day = 1; day <= daysInMonth; day++) {
                System.out.print(day + "\t");
                
                // 如果当前天数 + 前面的空白数 刚好是 7 的倍数，说明到了星期日，需要换行
                if ((day + firstDayOfWeek - 1) % 7 == 0) {
                    System.out.println();
                }
            }
            System.out.println(); // 每个月打印完后额外换行，保持美观
        }
    }

    /**
     * 功能 2：打印指定日期是星期几
     */
    private static void printDayOfWeek(int year, int month, int day) {
        try {
            // 根据输入的年月日构建日期对象
            LocalDate date = LocalDate.of(year, month, day);
            
            // 获取星期，并格式化为中文全称（如：星期一）
            String weekDay = date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.CHINESE);
            
            System.out.println("\n=> 结果：" + year + "年" + month + "月" + day + "日 是 【" + weekDay + "】");
        } catch (Exception e) {
            // 如果用户输入了不存在的日期（比如 2 月 30 日），LocalDate.of 会报错，这里进行捕获
            System.out.println("\n=> 错误：您输入的日期无效，请检查！");
        }
    }
}

