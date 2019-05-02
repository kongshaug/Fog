/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer;

import FunctionLayer.Enum.Role;
import FunctionLayer.HelpingClasses.Carport;
import FunctionLayer.HelpingClasses.Material;
import FunctionLayer.HelpingClasses.Order;
import FunctionLayer.HelpingClasses.RoofType;
import FunctionLayer.HelpingClasses.User;
import com.sun.org.apache.bcel.internal.generic.AALOAD;
import java.sql.SQLException;
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
    public void setUp()
    {
    }
    
    @After
    public void tearDown()
    {
    }

    /**
     * Test of getInstance method, of class DataFacade.
     */
    @Test
    public void testGetInstance() throws Exception
    {
        System.out.println("getInstance");
        DataFacade expResult = null;
        DataFacade result = DataFacade.getInstance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMaterial method, of class DataFacade.
     */
    @Test
    public void testGetMaterial_String() throws Exception
    {
        System.out.println("getMaterial");
        String material_name = "";
        DataFacade instance = null;
        Material expResult = null;
        Material result = instance.getMaterial(material_name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMaterial method, of class DataFacade.
     */
    @Test
    public void testGetMaterial_int() throws Exception
    {
        System.out.println("getMaterial");
        int material_id = 0;
        DataFacade instance = null;
        Material expResult = null;
        Material result = instance.getMaterial(material_id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMaterials method, of class DataFacade.
     */
    @Test
    public void testGetMaterials() throws Exception
    {
        System.out.println("getMaterials");
        DataFacade instance = null;
        List<Material> expResult = null;
        List<Material> result = instance.getMaterials();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCarport method, of class DataFacade.
     */
    @Test
    public void testGetCarport() throws Exception
    {
        System.out.println("getCarport");
        int carport_id = 0;
        DataFacade instance = null;
        Carport expResult = null;
        Carport result = instance.getCarport(carport_id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of orderCarport method, of class DataFacade.
     */
    @Test
    public void testOrderCarport() throws Exception
    {
        System.out.println("orderCarport");
        Carport carport = null;
        DataFacade instance = null;
        instance.orderCarport(carport);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRoofs method, of class DataFacade.
     */
    @Test
    public void testGetRoofs() throws Exception
    {
        System.out.println("getRoofs");
        DataFacade instance = null;
        List<RoofType> expResult = null;
        List<RoofType> result = instance.getRoofs();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUser method, of class DataFacade.
     */
    @Test
    public void testGetUser() throws Exception
    {
        System.out.println("getUser");
        int user_id = 0;
        DataFacade instance = null;
        User expResult = null;
        User result = instance.getUser(user_id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUsers method, of class DataFacade.
     */
    @Test
    public void testGetUsers() throws Exception
    {
       
        DataFacade instance = new DataFacade();
        List<User> expResult = null;
        List<User> result = instance.getUsers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of newUser method, of class DataFacade.
     *
     * @throws DataLayer.DataException
     */
    @Test
    public void testNewUser() throws DataException
    {
        DataFacade instance = new DataFacade();
        
        String email = "test@hotmail.com";
        String password = "1234";
        String name = "test";
        String address = "testvej 1";
        String zipcode = "1234";
        String phone = "88888888";
        Role r = Role.CUSTOMER;
        
        User newUser = new User(email, password, name, address, zipcode, phone, r);
        instance.newUser(newUser);
        instance.getUser(newUser.getId());
        
        assertEquals(newUser.getName(), name);
        assertEquals(newUser.getAddress(), address);
        assertEquals(newUser.getRole(), r);
        
        instance.removeUser(newUser);
    }

    /**
     * Test of login method, of class DataFacade.
     */
    @Test
    public void testLogin() throws Exception
    {
        System.out.println("login");
        String email = "";
        String password = "";
        DataFacade instance = null;
        User expResult = null;
        User result = instance.login(email, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of placeOrder method, of class DataFacade.
     */
    @Test
    public void testPlaceOrder() throws Exception
    {
        System.out.println("placeOrder");
        Order order = null;
        DataFacade instance = null;
        instance.placeOrder(order);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of orderShipped method, of class DataFacade.
     */
    @Test
    public void testOrderShipped() throws Exception
    {
        System.out.println("orderShipped");
        int order_id = 0;
        DataFacade instance = null;
        String expResult = "";
        String result = instance.orderShipped(order_id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
