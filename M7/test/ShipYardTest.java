/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import spaceTrader.APIs.ShipYard;

/**
 *
 * @author jinyushi
 */
public class ShipYardTest {
    private ShipYard sy;

    @Before
    public void setUp() throws Exception {
        sy = new ShipYard();
    }

    /**
     * Test of isYardExist method, of class ShipYard.
     */
    @Test
    public void testIsYardExist() {
        System.out.println("isYardExist");
        ShipYard instance = new ShipYard();
        boolean expResult = false;
        boolean result = instance.isYardExist();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of refuel method, of class ShipYard.
     */
    @Test
    public void testRefuel() {
        System.out.println("refuel");
        ShipYard instance = new ShipYard();
        String expResult = "";
        String result = instance.refuel();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of playerBuyShip method, of class ShipYard.
     */
    @Test
    public void testPlayerBuyShip() {
        System.out.println("playerBuyShip");
        String name = "";
        ShipYard instance = new ShipYard();
        instance.playerBuyShip(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of playerBuyWeapon method, of class ShipYard.
     */
    @Test
    public void testPlayerBuyWeapon() {
        System.out.println("playerBuyWeapon");
        String name = "";
        ShipYard instance = new ShipYard();
        instance.playerBuyWeapon(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of playerBuyShield method, of class ShipYard.
     */
    @Test
    public void testPlayerBuyShield() {
        System.out.println("playerBuyShield");
        String name = "";
        ShipYard instance = new ShipYard();
        instance.playerBuyShield(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of playerBuyCargoExpansion method, of class ShipYard.
     */
    @Test
    public void testPlayerBuyCargoExpansion() {
        System.out.println("playerBuyCargoExpansion");
        ShipYard instance = new ShipYard();
        instance.playerBuyCargoExpansion();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getShips method, of class ShipYard.
     */
    @Test
    public void testGetShips() {
        System.out.println("getShips");
        ShipYard instance = new ShipYard();
        List<String> expResult = null;
        List<String> result = instance.getShips();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getShipNames method, of class ShipYard.
     */
    @Test
    public void testGetShipNames() {
        System.out.println("getShipNames");
        ShipYard instance = new ShipYard();
        List<String> expResult = null;
        List<String> result = instance.getShipNames();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getWeaponNames method, of class ShipYard.
     */
    @Test
    public void testGetWeaponNames() {
        System.out.println("getWeaponNames");
        ShipYard instance = new ShipYard();
        List<String> expResult = null;
        List<String> result = instance.getWeaponNames();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getShieldNames method, of class ShipYard.
     */
    @Test
    public void testGetShieldNames() {
        System.out.println("getShieldNames");
        ShipYard instance = new ShipYard();
        List<String> expResult = null;
        List<String> result = instance.getShieldNames();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGadgetNames method, of class ShipYard.
     */
    @Test
    public void testGetGadgetNames() {
        System.out.println("getGadgetNames");
        ShipYard instance = new ShipYard();
        List<String> expResult = null;
        List<String> result = instance.getGadgetNames();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getShipPrices method, of class ShipYard.
     */
    @Test
    public void testGetShipPrices() {
        System.out.println("getShipPrices");
        ShipYard instance = new ShipYard();
        Map<String, Integer> expResult = null;
        Map<String, Integer> result = instance.getShipPrices();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getWeaponPrices method, of class ShipYard.
     */
    @Test
    public void testGetWeaponPrices() {
        System.out.println("getWeaponPrices");
        ShipYard instance = new ShipYard();
        Map<String, Integer> expResult = null;
        Map<String, Integer> result = instance.getWeaponPrices();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getShieldPrices method, of class ShipYard.
     */
    @Test
    public void testGetShieldPrices() {
        System.out.println("getShieldPrices");
        ShipYard instance = new ShipYard();
        Map<String, Integer> expResult = null;
        Map<String, Integer> result = instance.getShieldPrices();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGadgetPrices method, of class ShipYard.
     */
    @Test
    public void testGetGadgetPrices() {
        System.out.println("getGadgetPrices");
        ShipYard instance = new ShipYard();
        Map<String, Integer> expResult = null;
        Map<String, Integer> result = instance.getGadgetPrices();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
