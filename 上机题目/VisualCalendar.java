//package cn.lfd.test;  
import java.text.DateFormat;  
import java.text.ParseException;  
import java.text.SimpleDateFormat;  
import java.util.*;  
/** 
 *���ӻ����� 
 */  
public class VisualCalendar {  
    public static void main(String[] args) {  
        System.out.println("����������(��-��-��)��");  
        createCalendar();  
    }  
  
    private static void createCalendar() {  
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");//�涨�û���������ڸ�ʽ  
        boolean flag = true;  
        Date d = null;  
        while(flag) {  
            Scanner sc = new Scanner(System.in);  
            String datetime = sc.next();  
            try {  
                d = df.parse(datetime);//���û����������ת����Date����  
                flag = false;  
            } catch (ParseException e) {  
                System.out.println("���ڸ�ʽ����ȷ�����������룺");  
            }  
        }  
        Calendar c = new GregorianCalendar();  
        c.setTime(d);//ʹ���û�����ĵ� �������ô� Calendar ��ʱ�䡣  
        int day = c.get(Calendar.DATE);//�����û���������ڵ���  
          
        c.set(Calendar.DATE, 1);//��Calendar���������Ϊ1��  
        int week = c.get(Calendar.DAY_OF_WEEK);//�������һ�������ڼ�  
        int days = c.getActualMaximum(Calendar.DATE);//������µ�������  
        System.out.println("��\tһ\t��\t��\t��\t��\t��");  
        for(int j=1;j<week;j++) {  
            System.out.print("\t");  
        }  
        for(int i=1;i<=days;i++) {  
            if(day == i) {  
                System.out.print("*");//������û���������ӣ�������ǰ���ڣ�ʹ�������Ŀ  
            }  
            System.out.print(i+"\t");  
            //���������������  
            if(c.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY) {  
                System.out.println();  
            }  
            c.add(Calendar.DATE, 1);//��ӡһ�����Ӻ������ڼ�1  
        }  
    }  
}  