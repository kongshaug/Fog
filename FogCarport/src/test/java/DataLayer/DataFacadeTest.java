/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer;

import FunctionLayer.Enum.Paid;
import FunctionLayer.Enum.Role;
import FunctionLayer.Enum.Status;
import FunctionLayer.HelpingClasses.Carport;
import FunctionLayer.HelpingClasses.Material;
import FunctionLayer.HelpingClasses.Order;
import FunctionLayer.HelpingClasses.Roof;
import FunctionLayer.HelpingClasses.RoofType;
import FunctionLayer.HelpingClasses.Shed;
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
        String email = "customer@hotmail.dk";
        String password = "1234";
        User login = df.login(email, password);
        
        assertEquals(email, login.getEmail());
        assertEquals(password, login.getPassword());
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
        assertEquals("Bent", user.getName());
        assertEquals("employee@hotmail.dk", user.getEmail());
        assertEquals("2780", user.getZipcode());
        assertEquals(Role.EMPLOYEE, user.getRole());
    }

    /**
     * Test of getUser method, of class DataFacade.
     *
     * @throws DataLayer.DataException
     */
    @Test
    public void testNegativeGetUser() throws DataException
    {
        User user = df.getUser(2);
        
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
        
        int expected = 4;
        int result = df.getUsers().size();
        
        assertNotEquals(expected, result);
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
     * Test of orderCarport method, of class DataFacade.
     *
     * @throws DataLayer.DataException
     */
    @Test
    public void testOrderCarport() throws DataException
    {
        List<RoofType> rooftype = df.getRoofs();

        //test orderCarport with roof and without shed
        Roof roof = new Roof(15, rooftype.get(1));
        Carport carport = new Carport(300, 400, roof);
        df.orderCarport(carport);
        
        assertEquals(300, carport.getWidth());
        assertEquals(400, carport.getDepth());
        assertEquals(15, carport.getRoof().getSlope());
        assertEquals("Betontagsten - rød", carport.getRoof().getType().getName());

        //test orderCarport with roof and shed
        Shed shed = new Shed(470, 470);
        Carport carport1 = new Carport(500, 500, roof, shed);
        df.orderCarport(carport1);
        
        assertEquals(500, carport1.getWidth());
        assertEquals(470, carport1.getShed().getDepth());
        
        //test placeOrder
        User user = df.getUser(2);
        Order order = new Order(user, carport);
        
        df.placeOrder(order);
        
        //test removeOrder
        df.removeOrder(order);
    }
//
//    /**
//     * Test of getAllOrders method, of class DataFacade.
//     *
//     * @throws DataLayer.DataException
//     */
//    @Test
//    public void testGetAllOrders() throws DataException
//    {
//        List<Order> orders = df.getOrders();
//        
//        assertEquals(Status.MODTAGET, orders.get(0).getStatus());
//        assertEquals(Paid.BETALT, orders.get(0).getPaid());
//        
//        assertNotNull(df.getOrders().size());
//
//    }

    /**
     * Test of getAllOrdersByEmail method, of class DataFacade.
     *
     * @throws DataLayer.DataException
     */
    @Test
    public void testGetAllOrdersByEmail_String() throws DataException
    {
        String email = "employee@hotmail.dk";
        assertNotNull(df.getOrdersByEmail(email).size());

    }

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
