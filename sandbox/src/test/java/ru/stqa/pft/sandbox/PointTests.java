package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;


public class PointTests {


    @Test
    public void testDistance(){
        Point p1 = new Point (50.00,70.40);
        Point p2 = new Point (10.00,80.40);

        assert p1.distance(p2) == 5.477225575051661;
    }

    @Test
    public void testDistance2(){
        Point p1 = new Point (80.00,90.40);
        Point p2 = new Point (40.00,20.40);
        Assert.assertEquals(p1.distance(p2),5.477225575051661);


    }


}
