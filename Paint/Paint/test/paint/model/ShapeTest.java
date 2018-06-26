/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint.model;

import java.awt.Color;
import java.awt.Point;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ahmedhammad97
 */
public class ShapeTest {
    
    public ShapeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setPosition method, of class Shape.
     */
    @Test
    public void testSetPosition() {
        System.out.println("setPosition");
        Point position = null;
        Shape instance = new ShapeImpl();
        instance.setPosition(position);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPosition method, of class Shape.
     */
    @Test
    public void testGetPosition() {
        System.out.println("getPosition");
        Shape instance = new ShapeImpl();
        Point expResult = null;
        Point result = instance.getPosition();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setProperties method, of class Shape.
     */
    @Test
    public void testSetProperties() {
        System.out.println("setProperties");
        Map<String, Double> properties = null;
        Shape instance = new ShapeImpl();
        instance.setProperties(properties);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProperties method, of class Shape.
     */
    @Test
    public void testGetProperties() {
        System.out.println("getProperties");
        Shape instance = new ShapeImpl();
        Map<String, Double> expResult = null;
        Map<String, Double> result = instance.getProperties();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setColor method, of class Shape.
     */
    @Test
    public void testSetColor() {
        System.out.println("setColor");
        Color color = null;
        Shape instance = new ShapeImpl();
        instance.setColor(color);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getColor method, of class Shape.
     */
    @Test
    public void testGetColor() {
        System.out.println("getColor");
        Shape instance = new ShapeImpl();
        Color expResult = null;
        Color result = instance.getColor();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFillColor method, of class Shape.
     */
    @Test
    public void testSetFillColor() {
        System.out.println("setFillColor");
        Color color = null;
        Shape instance = new ShapeImpl();
        instance.setFillColor(color);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFillColor method, of class Shape.
     */
    @Test
    public void testGetFillColor() {
        System.out.println("getFillColor");
        Shape instance = new ShapeImpl();
        Color expResult = null;
        Color result = instance.getFillColor();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of draw method, of class Shape.
     */
    @Test
    public void testDraw() {
        System.out.println("draw");
        Object canvas = null;
        Shape instance = new ShapeImpl();
        instance.draw(canvas);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class ShapeImpl extends Shape {
    }
    
}
