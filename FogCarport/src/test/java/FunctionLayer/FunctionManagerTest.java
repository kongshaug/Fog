/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

import DataLayer.DataException;
import FunctionLayer.Enum.Role;
import FunctionLayer.HelpingClasses.Carport;
import FunctionLayer.HelpingClasses.Order;
import FunctionLayer.HelpingClasses.RoofType;
import FunctionLayer.HelpingClasses.User;
import FunctionLayer.HelpingClasses.Material;
import FunctionLayer.HelpingClasses.Roof;
import FunctionLayer.HelpingClasses.Shed;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author benja
 */
public class FunctionManagerTest
{

    FunctionManager fm;

    public FunctionManagerTest()
    {
    }

    @BeforeClass
    public static void setUpClass()
    {

    }

    @AfterClass
    public static void tearDownClass()
    {
    }

    @Before
    public void setUp() throws DataException
    {
        fm = FunctionManager.getInstance();
    }

    @After
    public void tearDown()
    {
    }

    /**
     * Test of getInstance method, of class FunctionManager.
     * @throws DataLayer.DataException
     */
    @Test
    public void testGetInstance() throws DataException
    {
        FunctionManager expResult = FunctionManager.getInstance();
        FunctionManager result = FunctionManager.getInstance();
        assertEquals(expResult, result);

    }

    /**
     * Test of login method, of class FunctionManager.
     * @throws DataLayer.DataException
     */
    @Test
    public void testLogin() throws DataException
    {
        String email = "benja_kongs@hotmail.com";
        String password = "tun89tcv";
        User result = fm.login(email, password);

        assertEquals(password, result.getPassword());
        assertEquals(email, result.getEmail());
    }

    /**
     * Test of newUser method, of class FunctionManager.
     * @throws DataLayer.DataException
     */
    @Test
    public void testNewUser() throws DataException
    {
        String email = "newUser@hotmail.com";
        String password = "1234";

        User user = new User(email, password, "testname", "teststreeet", "2200", "11111111", Role.CUSTOMER);

        String expResult = "Din bruger er nu oprettet";
        String result = fm.newUser(user);
        assertEquals(expResult, result);
        
        fm.removeUser(user);

    }

    /**
     * Test of placeOrder method, of class FunctionManager.
     * @throws DataLayer.DataException
     */
    @Test
    public void testPlaceOrder() throws DataException
    {
        User user = new User(60, "testtiltest@em.dk", "1234", "benja", "teststreeet", "2200", "11111111", Role.CUSTOMER);
        Material roof_materialOne = new Material(39, "B & C rygsten rød", "stk", "tag", 15.00);
        Material roof_materialTwo = new Material(36, "B & C dobbelt -s rød", "stk", "tag", 15.00);
        RoofType type = new RoofType(2, "Betontagsten - rød", "slope", roof_materialOne, roof_materialTwo);

        Roof SlopeRoof = new Roof(15, type);
        Shed shed = new Shed(200, 200);
        Carport carport = new Carport(650, 650, SlopeRoof, shed);
        Order order = new Order(user, carport);

        String expResult = "Tak for din forespørgsel. Vi vil behandle den hurtigst muligt";

        String result = fm.placeOrder(order);
        assertEquals(expResult, result);
    }

    /**
     * Test of isShipped method, of class FunctionManager.
     */
//    @Test
//    public void testIsShipped() throws Exception {
//        System.out.println("isShipped");
//        Order order = null;
//        FunctionManager instance = new FunctionManager();
//        instance.isShipped(order);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    /**
     * Test of calcCarport method, of class FunctionManager.
     * @throws DataLayer.DataException
     */
    @Test
    public void testCalcCarport() throws DataException
    {
        Material roof_materialOne = new Material(39, "B & C rygsten rød", "stk", "tag", 15.00);
        Material roof_materialTwo = new Material(36, "B & C dobbelt -s rød", "stk", "tag", 15.00);

        RoofType type = new RoofType(2, "Betontagsten - rød", "slope", roof_materialOne, roof_materialTwo);
        Roof SlopeRoof = new Roof(1, 15, type);
        Shed shed = new Shed(1, 200, 200);
        Carport carport = new Carport(450, 450, SlopeRoof, shed);

        //calcCarport calls 2 methods one that puts 3 elements in the list and one that puts one elements in the list therefor there should be 4 elements in the list
        int expResult = 4;
        fm.calcCarport(carport);
        int result = carport.getParts().size();

        assertEquals(expResult, result);
    }

    /**
     * Test of calcCarport method, of class FunctionManager, test's if the
     * carport's partlist is null if the mesurments is too big.
     * @throws DataLayer.DataException
     */
    @Test
    public void testTooBigCalcCarport() throws DataException
    {
        Material roof_materialOne = new Material(39, "B & C rygsten rød", "stk", "tag", 15.00);
        Material roof_materialTwo = new Material(36, "B & C dobbelt -s rød", "stk", "tag", 15.00);
        RoofType type = new RoofType(2, "Betontagsten - rød", "slope", roof_materialOne, roof_materialTwo);

        Roof SlopeRoof = new Roof(1, 15, type);
        Shed shed = new Shed(1, 200, 200);
        Carport carport = new Carport(800, 800, SlopeRoof, shed);

        int expResult = carport.getParts().size();
        fm.calcCarport(carport);
        int result = carport.getParts().size();

        assertEquals(expResult, result);

    }

    /**
     * Test of calcShed method, of class FunctionManager.
     * @throws DataLayer.DataException
     */
    @Test
    public void testCalcShed() throws DataException
    {
        System.out.println("calcShed");

        Material roof_materialOne = new Material(39, "B & C rygsten rød", "stk", "tag", 15.00);
        Material roof_materialTwo = new Material(36, "B & C dobbelt -s rød", "stk", "tag", 15.00);
        RoofType type = new RoofType(2, "Betontagsten - rød", "slope", roof_materialOne, roof_materialTwo);

        Roof SlopeRoof = new Roof(1, 15, type);
        Shed shed = new Shed(1, 200, 200);
        Carport carport = new Carport(650, 650, SlopeRoof, shed);

        int expResult = 13;

        fm.calcShed(carport);

        int result = carport.getShed().getParts().size();

        assertEquals(expResult, result);
    }

    /**
     * Test of getSlopedRoofs method, of class FunctionManager.
     * @throws DataLayer.DataException
     */
    @Test
    public void testGetSlopedRoofsListSize() throws DataException
    {
        int expResult = 5;
        int result = fm.getSlopedRoofs().size();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFlatRoofs method, of class FunctionManager.
     * @throws DataLayer.DataException
     */
    @Test
    public void testGetFlatRoofs() throws DataException
    {
        int expResult = 1;
        int result = fm.getFlatRoofs().size();
        assertEquals(expResult, result);
    }

    /**
     * Test of getRoofType method, of class FunctionManager.
     * @throws DataLayer.DataException
     */
    @Test
    public void testGetRoofType() throws DataException
    {
        int typeId = 2;
        RoofType result = fm.getRoofTypeById(typeId);
        assertEquals("Betontagsten - rød", result.getName());

    }

}
