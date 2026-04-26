public class Passtest1{
    public int change(int value){
        return 55;
    }

    public static void main (String args[]){
        int val;
        Passtest1 pt = new Passtest1();

        val = 11;
        val = pt.change(val);
        System.out.println(val);
    }
}