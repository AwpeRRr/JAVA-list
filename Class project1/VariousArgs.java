public class VariousArgs{
    public double rating(double r, int ...points){
        int sum = 0;
        for(int p: points) sum+= p;
        return ((sum* r)/ points.length);
    }

    public static void main(String[] args){
        VariousArgs va = new VariousArgs();
        System.out.println(va.rating(0.5, new int[]{6,66,666}));

    }
}