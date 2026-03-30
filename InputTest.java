import java.util.Scanner;

public class InputTest {
    public static void main(String[] args)
    {
        Scanner input = new
        Scanner (System.in);
    System.out.print("Whats your favourite number ");
    int targetnum = input.nextInt();
    System.out.println("please give " + targetnum + " a fuck");
    input.close();
    }    
}
