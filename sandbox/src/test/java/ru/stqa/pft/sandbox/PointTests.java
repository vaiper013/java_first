package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;


public class PointTests {


    @Test
    public void testDistance(){
        Point p1 = new Point (10.00,10.00);
        Point p2 = new Point (50.00,50.00);

        assert p1.distance(p2) == 5.477225575051661;
    }

    @Test
    public void testDistance2(){
        Point p1 = new Point (10.00,10.00);
        Point p2 = new Point (50.00,50.00);
        Assert.assertEquals(p1.distance(p2),5.477225575051661);


    }


}
