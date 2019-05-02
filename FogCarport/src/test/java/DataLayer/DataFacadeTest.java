/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer;

import FunctionLayer.Enum.Role;
import FunctionLayer.HelpingClasses.Carport;
import FunctionLayer.HelpingClasses.Material;
import FunctionLayer.HelpingClasses.RoofType;
import FunctionLayer.HelpingClasses.User;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author aamandajuhl
 */
public class DataFacadeTest
{

    DataFacade df;

    public DataFacadeTest()
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
        df = DataFacade.getInstance();
    }

    @After
    public void tearDown()
    {
    }

    /**
     * Test of getInstance method, of class DataFacade.
     *
     * @throws DataLayer.DataException
     */
    @Test
    public void testGetInstance() throws DataException
    {
        DataFacade expResult = DataFacade.getInstance();
        DataFacade result = DataFacade.getInstance();
        assertEquals(expResult, result);
    }

    /**
     * Test of newUser method, of class DataFacade.
     *
     * @throws DataLayer.DataException
     */
    @Test
    public void testNewUser() throws DataException
    {
        String email = "test@hotmail.com";
        String password = "1234";
        String name = "test";
        String address = "testvej 1";
        String zipcode = "1234";
        String phone = "88888888";
        Role r = Role.CUSTOMER;

        User newUser = new User(email, password, name, address, zipcode, phone, r);
        df.newUser(newUser);
        df.getUser(newUser.getId());

        assertEquals(newUser.getName(), name);
        assertEquals(newUser.getAddress(), address);
        assertEquals(newUser.getRole(), r);

        df.removeUser(newUser);
    }

    /**
     * Test of login method, of class DataFacade.
     *
     * @throws DataLayer.DataException
     */
    @Test
    public void testLogin() throws DataException
    {
        String email = "test@hotmail.com";
        String password = "1234";
        User login = df.login(email, password);

        assertEquals("test@hotmail.com", login.getEmail());
        assertEquals("1234", login.getPassword());
    }

    /**
     * Test of getUser method, of class DataFacade.
     *
     * @throws DataLayer.DataException
     */
    @Test
    public void testGetUser() throws DataException
    {
        User user = df.getUser(1);

        assertNotNull(user);
        assertEquals("Amalie", user.getName());
        assertEquals("malie@hotmail.dk", user.getEmail());
        assertEquals("2700", user.getZipcode());
        assertEquals(Role.CUSTOMER, user.getRole());
    }

    /**
     * Test of getUser method, of class DataFacade.
     *
     * @throws DataLayer.DataException
     */
    @Test
    public void testNegativeGetUser() throws DataException
    {
        User user = df.getUser(3);

        assertNotNull(user);
        assertNotEquals("benjamin", user.getName());
        assertNotEquals(Role.ADMIN, user.getRole());
    }

    /**
     * Test of getUsers method, of class DataFacade.
     *
     * @throws DataLayer.DataException
     */
    @Test
    public void testGetUsers() throws DataException
    {
        List<User> users = df.getUsers();
        assertNotNull(users);
    }

    /**
     * Test of getMaterial method, of class DataFacade.
     *
     * @throws DataLayer.DataException
     */
    @Test
    public void testGetMaterial_String() throws DataException
    {
        Material m = df.getMaterial("38x73 mm taglægte T1");
        assertEquals("træ", m.getMaterial_class());
        assertEquals(15.00, m.getPrice(), 0.01);
        assertEquals(7, m.getId());

        Material m2 = df.getMaterial("1x20 mm hulbånd 10 mtr");
        assertEquals("beslag og skruer", m2.getMaterial_class());
        assertEquals("rulle", m2.getUnit());
        assertEquals(80.00, m2.getPrice(), 0.01);
    }

    /**
     * Test of getMaterial method, of class DataFacade.
     *
     * @throws DataLayer.DataException
     */
    @Test
    public void testGetMaterial_int() throws DataException
    {
        Material m = df.getMaterial(12);
        assertEquals("Plastmo Ecolite blåtonet", m.getName());
        assertEquals("tag", m.getMaterial_class());
        assertEquals("stk", m.getUnit());
        assertEquals(70.00, m.getPrice(), 0.01);

        Material m2 = df.getMaterial(22);
        assertEquals("Vinkelbeslag", m2.getName());
        assertEquals("beslag og skruer", m2.getMaterial_class());
        assertEquals(15.00, m2.getPrice(), 0.01);
    }

    /**
     * Test of getMaterials method, of class DataFacade.
     *
     * @throws DataLayer.DataException
     */
    @Test
    public void testGetMaterials() throws DataException
    {
        List<Material> materials = df.getMaterials();
        assertEquals(25, materials.get(24).getId());
        assertEquals("Hulpladebeslag", materials.get(42).getName());
        assertEquals("rulle", materials.get(30).getUnit());
        assertEquals("træ", materials.get(0).getMaterial_class());
        assertEquals(50.00, materials.get(9).getPrice(), 0.01);

        int expResult = 45;
        int result = df.getMaterials().size();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMaterials method, of class DataFacade.
     *
     * @throws DataLayer.DataException
     */
    @Test
    public void testNegativeGetMaterials() throws DataException
    {
        List<Material> materials = df.getMaterials();
        assertNotEquals("skur", materials.get(5).getMaterial_class());
        assertNotEquals("meter", materials.get(23).getUnit());

        int expResult = 42;
        int result = df.getMaterials().size();
        assertNotEquals(expResult, result);
    }

    /**
     * Test of getRoofs method, of class DataFacade.
     *
     * @throws DataLayer.DataException
     */
    @Test
    public void testGetRoofs() throws DataException
    {
        List<RoofType> rooftype = df.getRoofs();
        assertEquals("Plasttrapezplader", rooftype.get(0).getName());
        assertEquals("slope", rooftype.get(2).getRoof_class());
        assertEquals(14, rooftype.get(4).getM1().getId());

        int expResult = 6;
        int result = df.getRoofs().size();
        assertEquals(expResult, result);
    }

    /**
     * Test of getRoofs method, of class DataFacade.
     *
     * @throws DataLayer.DataException
     */
    @Test
    public void testNegativeGetRoofs() throws DataException
    {
        List<RoofType> rooftype = df.getRoofs();
        assertNotEquals("Betontagsten - blå", rooftype.get(1).getName());
        assertNotEquals("flat", rooftype.get(2).getRoof_class());

        int expResult = 2;
        int result = df.getRoofs().size();
        assertNotEquals(expResult, result);
    }

    /**
     * Test of getCarport method, of class DataFacade.
     *
     * @throws DataLayer.DataException
     */
    @Test
    public void testGetCarport() throws DataException
    {
        Carport c = df.getCarport(1);

        assertNotNull(c);
        assertEquals(650, c.getDepth());
        assertEquals(650, c.getWidth());
        assertEquals(1, c.getShed().getId());
        assertEquals(1, c.getRoof().getId());
    }
//
//    /**
//     * Test of orderCarport method, of class DataFacade.
//     */
//    @Test
//    public void testOrderCarport() throws Exception
//    {
//        System.out.println("orderCarport");
//        Carport carport = null;
//        DataFacade instance = null;
//        instance.orderCarport(carport);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//
//    /**
//     * Test of placeOrder method, of class DataFacade.
//     */
//    @Test
//    public void testPlaceOrder() throws Exception
//    {
//        System.out.println("placeOrder");
//        Order order = null;
//        DataFacade instance = null;
//        instance.placeOrder(order);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of orderShipped method, of class DataFacade.
//     */
//    @Test
//    public void testOrderShipped() throws Exception
//    {
//        System.out.println("orderShipped");
//        int order_id = 0;
//        DataFacade instance = null;
//        String expResult = "";
//        String result = instance.orderShipped(order_id);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
}
