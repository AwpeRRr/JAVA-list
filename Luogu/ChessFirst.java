import java.util.Scanner;

public class ChessFirst{
    int c, d; // Assuming c and d are instance variables

    long result(int x, int y){
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
    	Scanner s1 = new Scanner(System.in);

        int a = s1.nextInt();
        int b = s1.nextInt();
        int c = s1.nextInt();
        int d = s1.nextInt();
        
        
        
        
        ChessFirst chess = new ChessFirst();
        chess.c = c;
        chess.d = d;
        System.out.println(chess.result(a,b));

        s1.close();
    }
}