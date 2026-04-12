class Empinfo{
    String name;
    String designaiton;
    String deparment;

    public void print(){
        System.out.println("name: " + this.name);
        System.out.println("designation: " + this.designaiton);
        System.out.println("department: " + this.deparment);
    }

}


public class MyClassObj{
    public static void main (String[] args ){
        Empinfo emp1 = new Empinfo();
        emp1.name = "robert.java";
        emp1.designaiton = "manager";
        emp1.deparment = "coffee shop";

        emp1.print();
    }
}