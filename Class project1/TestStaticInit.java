public class TestStaticInit {
    public static void main(String[] args) {
        System.out.println("Main code:i=" + StaticDemo.i);
    }
}

class StaticDemo{
    static int i;
    static {
        i = 5;
        System.out.println("Static code:i=" + i++);
    }
}
