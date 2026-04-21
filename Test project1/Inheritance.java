public class Inheritance {
    public static void main (String[] args){
        Rec r1 = new Rec();
        r1.newDraw();
    }
}

class Shape{
    public void draw(){
        System.out.println("Draw shape");
    }
}

class Rectangle extends Shape{
    public void draw(){
        System.out.println("Draw Rectangle");
    }
    public void newDraw(){
        draw();
        super.draw();
    }
}
