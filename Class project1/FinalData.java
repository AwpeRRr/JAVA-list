import java.util.Random;

public class FinalData {
    private static Random rand = new Random(47);
    private final int valueOne = 9;
    private final int i4 = rand.nextInt(20);
    private Value v1 = new Value(11);
    private final Value v2 = new Value(22);
    private final int[] a = {1,2,3,4,5,6};
    public static void main (String[] args){
        FinalData fd1 = new FinalData();
        //fd1.valueOne++;
        //fd1.i4++;
        fd1.v1 = new Value(9);
        //fd1.v2 = new Value(0);
        fd1.v2.i++;
        //fd1.a = new int[3];
        for (int i = 0; i <fd1.a.length; i++){
            fd1.a[i] = i++;

        }        
    }
}

class Value{
    int i;
    public Value(int i){
        this.i = i;

    }
}
