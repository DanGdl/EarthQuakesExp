package com.dgd.earthquakes;

import com.dgd.earthquakes.data.network.infra.Geometry;
import com.dgd.earthquakes.data.network.infra.Properties;
import com.dgd.earthquakes.data.network.infra.QuakeData;
import com.dgd.earthquakes.data.network.infra.QuakesResponse;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by max on 06/10/17.
 */
@RunWith(JUnit4.class)
public class QuakeDtoTest {
    private String valS;

    @Before
    public void setup(){
        valS = "fdsfsdfsd";
    }

    @Test
    public void geometryTest(){
        getGeometry();
    }

    private Geometry getGeometry() {
        Geometry g = new Geometry();
        Assert.assertNotNull(g);

        List<Double> s = new ArrayList<>();
        s.add(1D);
        s.add(2D);
        s.add(3D);

        g.setCoordinates(s);
        Assert.assertEquals(s, g.getCoordinates());
        Assert.assertEquals(s.size(), g.getCoordinates().size());
        return g;
    }

    @Test
    public void propertiesTest(){
        getProperties();
    }

    private Properties getProperties() {
        Properties p = new Properties();
        Assert.assertNotNull(p);

        p.setTitle(valS);
        p.setUrl(valS);
        Assert.assertEquals(valS, p.getTitle());
        Assert.assertEquals(valS, p.getUrl());

        long valL = 4352L;
        p.setTime(valL);
        Assert.assertEquals(valL, p.getTime());

        float valF = 145.0F;
        p.setMagnitude(valF);
        Assert.assertEquals(valF, p.getMagnitude(), 0F);
        return p;
    }

    @Test
    public void quakeDataTest(){
        getQuakeData();
    }

    private QuakeData getQuakeData() {
        Geometry g = getGeometry();
        Properties p = getProperties();

        QuakeData qd = new QuakeData();
        Assert.assertNotNull(qd);

        qd.setId(valS);
        Assert.assertEquals(valS, qd.getId());

        qd.setGeometry(g);
        Assert.assertEquals(g, qd.getGeometry());

        qd.setProperties(p);
        Assert.assertEquals(p, qd.getProperties());
        return qd;
    }

    @Test
    public void test(){
        QuakeData qd = getQuakeData();

        List<QuakeData> list = new ArrayList<>();
        list.add(qd);
        list.add(qd);

        QuakesResponse qr = new QuakesResponse();
        Assert.assertNotNull(qr);

        qr.setQuakes(list);
        Assert.assertEquals(list, qr.getEarthquakes());
    }

    @After
    public void finish(){
        valS = null;
    }
}
