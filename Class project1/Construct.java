public class Construct {
    public static void main(String[] args) {
        Undergraduate ug = new Undergraduate(666);
    }
}

class Person{
    Person(){
        System.out.println("Person");
    }
}

class Student extends Person{
    Student(int i){
        System.out.println("Student "+ i);
    }
}

class Undergraduate extends Student{
    Undergraduate(int i){
        super(i);
        System.out.println("Undergraduate");
    }
}