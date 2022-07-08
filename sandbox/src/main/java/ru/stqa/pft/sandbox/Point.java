package ru.stqa.pft.sandbox;

public class Point {
    public double x;
    public double y;


    public Point(double x, double y) {
        this.x=x;
        this.y=y;

    }

    public  double distance(Point p2){
        double d = Math.sqrt ((p2.y - y) * (p2.y - y) + (p2.x - x) * (p2.x - x));
        return d;




    }


}

