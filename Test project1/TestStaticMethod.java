import javax.print.event.PrintJobAdapter;

public class TestStaticMethod {
    public static void main(String[] args) {
        StaticMethod obj = new StaticMethod();
        StaticMethod.PrintXAndY(obj);
    }
}

class StaticMethod{
    int x = 0;
    static int y = 1;
    public void PrintAndIncreaseY(){
        sPrintY();
        y++;
    }
    public static void sPrintY(){
        //System.out.println(this.x);
        //PrintAndIncreaseY();
        System.out.println(StaticMethod.y);
    }

    public static void PrintXAndY(StaticMethod o){
        System.out.println(o.x);
        o.PrintAndIncreaseY();
        sPrintY();
    }
}
