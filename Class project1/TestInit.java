public class TestInit {
    int x = 10;
    public static void main(String[] args) {
        TestInit init = new TestInit();
        int x = (int)(Math.random() * 100);
        int z;
        int y = 0;
        if (x < 50){
            y = 9;
        }
        z = x + y + init.x;
        System.out.println(x);
        System.out.println(y);
        System.out.println(z);
        System.out.println(init.x);
    }
}
