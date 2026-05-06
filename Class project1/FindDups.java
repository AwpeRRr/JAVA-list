import java.util.Set;
import java.util.HashSet;


public class FindDups{
    public static void main(String[] args){
        Set<String> s = new HashSet<String>();
        String[] fruits = {
            "apple",
            "banana",
            "orange",
            "grape",
            "kiwi",
            "melon"
        };
        for (int i = 0 ; i < fruits.length ; i++){
            if (!s.add(fruits[i]))
            System.out.println("Duplicate: " + fruits[i]);    
        }

        System.out.println(s.size() + " distinct words detected. " + s);
    }
}

