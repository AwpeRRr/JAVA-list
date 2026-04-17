class Glyph {
    void draw(){
        System.out.println("Glyph.draw()");
    }
    Glyph(){
        System.out.println("Glyph() before draw()");
        draw();
        System.out.println("Glyph() after draw()");
    }
    
} 
class RoundGlyph extends Glyph{
    private int radius = 169;
    RoundGlyph(int r){
        radius = r;
        System.out.println("RG.RoundGlyph() , radius = " + radius);
    }
    /*方法重写是在父类对象构造之前吗？*/
    void draw(){
        System.out.println("RG.draw().radius = " + radius);
    }
}

public class PolyConstruct{
    public static void main(String[] args){
        new RoundGlyph(5);
    }
}