public class OOverriding extends Base{
    private int i =2;
    public static void main(String[] args){
        OOverriding no = new OOverriding();
        no.increase();
        System.out.println(no.i);
        System.out.println(no.getI());

        Base no1 = new OOverriding();
        no1.increase();
        System.out.println(no1.getI());


    }
    
}

class Base{
    private int i =100;
    public void increase(){
        this.i ++;

    }
    public int getI(){
        return this.i;
    }
}
