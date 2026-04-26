public class Rec {
    
}

class Rectangle {
    double width =0.0, height =0.0;

    public double getArea(){
        double area;
        area = width * height;
        return area;

    }

    public double getPerimeter(){
        double Perimeter;
        Perimeter = (width + height) * 2;
        return Perimeter;

    }

}

class Point {
    double x=0.0 , y=0.0;

    public double distance(Point p){
        double D , dis;
        D = (x-p.x) * (x - p.x) + (y - p.y) * (y - p.y);
        dis = Math.sqrt(D);
        return dis;
    }
}

