public class Inheritance {
    public static void main (String[] args){
        Rectangle r1 = new Rectangle();
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
