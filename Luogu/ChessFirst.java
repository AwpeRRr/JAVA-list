import java.util.Scanner;

public class Main{
    Scanner s1 = new Scanner(System.in);

        int a = s1.nextInt();
        int b = s1.nextInt();
        int c = s1.nextInt();
        int d = s1.nextInt();
    
    int result(int x, int y){
            if(x == c+1 && y == d+2){
                return 0;
            }
            else if(x == c+1 && y == d-2){
                return 0;
            }
            else if(x == c-1 && y == d+2){
                return 0;
            }
            else if(x == c-1 && y == d-2){
                return 0;
            }
            else if(x == c+2 && y == d+1){
                return 0;
            }
            else if(x == c+2 && y == d-1){
                return 0;
            }
            else if(x == c-2 && y == d-1){
                return 0;
            }
            else if(x == c-2 && y == d+1){
                return 0;
            }
            else if(x == c && y == d){
                return 0;
            }
            else if(x == 0 && y == 0){
                return 1;
            }
            else if(x < 0 || y < 0){
                return 0;
            }
            
            return result(x-1,y) + result(x,y-1);
        };
    
    public static void main(String[] args){
        System.out.println(result(a,b));

        s1.close();
    }
}