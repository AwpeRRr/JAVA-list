public class BlackFinal{
    private final int i = 0;
    private final int j;
    private final Poppet p;
    public BlackFinal(){
        j = 1;
        p = new Poppet(1);
    }

    public BlackFinal(int x){
        j = x;
        p = new Poppet(x);
    }

    public static void main(String[] args){
        new BlackFinal();
        new BlackFinal(6657);
    }
}

class Poppet{
    private int i;
    Poppet (int ii){
        i = ii;

    }
}