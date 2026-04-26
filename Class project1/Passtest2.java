public class Passtest2 {
    public String changeStr(String value){
        return "fuck";

    }

    public static void main (String args[]){
        String str;
        Passtest2 pt= new Passtest2();

        str= new String("Hello");
        str = pt.changeStr(str);
        System.out.println(str);

    }
}
