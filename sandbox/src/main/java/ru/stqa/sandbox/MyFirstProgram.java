package ru.stqa.sandbox;

class MyFirstProgram {
    public static void main(String[] args) {
        Point pt1=new Point();
        pt1.x1=10.00;
        pt1.y1=20.00;

        Point pt2=new Point();
        pt2.x2=50.00;
        pt2.y2=70.00;
        System.out.println(distance(pt1,pt2));

    }
    public static double distance(Point pt1, Point pt2){
        double d = Math.sqrt((pt2.x2 - pt1.x1) * (pt2.x2 - pt1.x1) + (pt2.y2 - pt1.y1) * (pt2.y2 * pt1.y1));
        return d;

    }
}