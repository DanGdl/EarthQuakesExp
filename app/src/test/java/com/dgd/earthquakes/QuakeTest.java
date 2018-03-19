package com.dgd.earthquakes;

import com.dgd.earthquakes.models.Quake;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Date;

/**
 * Created by max on 06/10/17.
 */
@RunWith(JUnit4.class)
public class QuakeTest {
    private Quake q;

    @Before
    public void setup(){
        q = new Quake();
    }

    @Test
    public void testQuake(){
        Assert.assertNotNull(q);

        String valS = "djsjfnvjavksd";
        q.setId(valS);
        q.setLink(valS);
        q.setMagnitude(valS);
        q.setTitle(valS);
        Assert.assertEquals(valS, q.getId());
        Assert.assertEquals(valS, q.getLink());
        Assert.assertEquals(valS, q.getMagnitude());
        Assert.assertEquals(valS, q.getTitle());

        Date d = new Date();
        q.setDate(d);
        Assert.assertEquals(d, q.getDate());

        double valD = 543985D;
        q.setLatitude(valD);
        q.setLongitude(valD);
        Assert.assertEquals(valD, q.getLatitude(), 0.0);
        Assert.assertEquals(valD, q.getLongitude(), 0.0);
    }

    @After
    public void finish(){
        q = null;
    }
}
