public class Ooutput {
    public static void main(String[] args) {
        System.out.println(0.0 == -0.0);
        System.out.println(0.0 > -0.0);
        System.out.println(1.0 < Double.NaN);
        System.out.println(1.0 == Double.NaN);
        System.out.println(1.0 != Double.NaN);
        System.out.println(Double.NaN == Double.NaN);
        System.out.println(0.0 / 0.0);
        System.out.println(1.0 / 0.0);
        System.out.println(1.0 / -0.0);
        
    }
}
