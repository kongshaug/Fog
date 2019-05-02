/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package FunctionLayer;

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
public class FunctionManagerTest {
    
    public FunctionManagerTest() {
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
     * Test of getInstance method, of class FunctionManager.
     */
    @Test
    public void testGetInstance() throws Exception {
        System.out.println("getInstance");
        FunctionManager expResult = FunctionManager.getInstance();
        FunctionManager result = FunctionManager.getInstance();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of login method, of class FunctionManager.
     */
    @Test
    public void testLogin() throws Exception {
        System.out.println("login");
        String email = "test@hotmail.com";
        String password = "1234";
        FunctionManager instance = new FunctionManager();
        User expResult = null;
        User result = instance.login(email, password);
        assertEquals(password, result.getPassword());
        assertEquals(email, result.getEmail());
       
    }

    /**
     * Test of newUser method, of class FunctionManager.
     */
    @Test
    public void testNewUser() throws Exception {
        System.out.println("newUser");
        
        
        String email = "newUser@hotmail.com";
        String password = "1234";
        
        FunctionManager instance = new FunctionManager();
        
        
        
        User user = user( email,password, "testname", "teststreeet", "2200", "11111111", Role.CUSTOMER);
  
        
        String expResult = "Din bruger er nu oprettet";
        
        String result = instance.newUser(user);
        
        
        
        assertEquals(expResult, result);
        
        
        
        
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of placeOrder method, of class FunctionManager.
     */
    @Test
    public void testPlaceOrder() throws Exception {
        System.out.println("placeOrder");
        Order order = null;
        FunctionManager instance = new FunctionManager();
        String expResult = "";
        String result = instance.placeOrder(order);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isShipped method, of class FunctionManager.
     */
    @Test
    public void testIsShipped() throws Exception {
        System.out.println("isShipped");
        Order order = null;
        FunctionManager instance = new FunctionManager();
        instance.isShipped(order);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calcCarport method, of class FunctionManager.
     */
    @Test
    public void testCalcCarport() throws Exception {
         System.out.println("calcCarport");
        
        Material roof_materialOne = new Material(39, "B & C rygsten rød", "stk", "tag", 15.00);
        Material roof_materialTwo = new Material(36, "B & C dobbelt -s rød", "stk", "tag", 15.00);
        
        RoofType type = new RoofType(2,"Betontagsten - rød", "slope", roof_materialOne, roof_materialTwo);
        
        Roof SlopeRoof = new Roof(1,15, type);
        
        Shed shed = new Shed(1, 200, 200);
        
        Carport carport = new Carport(450, 450, SlopeRoof, shed);
        FunctionManager instance = new FunctionManager();
       
        //calcCarport calls 2 methods one that puts 3 elements in the list and one that puts one elements in the list therefor there should be 4 elements in the list
        int expResult = 4; 
        instance.calcCarport(carport);
        int result = carport.getParts().size();
        
        assertEquals(expResult, result);
    }
    
       
     /**
     * Test of calcCarport method, of class FunctionManager, test's if the carport's partlist is null if the mesurments is too big.
     */
    @Test
    public void testTooBigCalcCarport() throws Exception {
        System.out.println("calcCarport with wrong mesurments");
        
        Material roof_materialOne = new Material(39, "B & C rygsten rød", "stk", "tag", 15.00);
        Material roof_materialTwo = new Material(36, "B & C dobbelt -s rød", "stk", "tag", 15.00);
        RoofType type = new RoofType(2,"Betontagsten - rød", "slope", roof_materialOne, roof_materialTwo);
        
        Roof SlopeRoof = new Roof(1,15, type);
        
        Shed shed = new Shed(1, 200, 200);
        
        Carport carport = new Carport(800, 800, SlopeRoof, shed);
        FunctionManager instance = new FunctionManager();
        int expResult = carport.getParts().size();
        instance.calcCarport(carport);
        int result = carport.getParts().size();
        
        assertEquals(expResult, result);
        

    }

    /**
     * Test of calcShed method, of class FunctionManager.
     */
    @Test
    public void testCalcShed() throws Exception {
        System.out.println("calcShed");
        
        Material roof_materialOne = new Material(39, "B & C rygsten rød", "stk", "tag", 15.00);
        Material roof_materialTwo = new Material(36, "B & C dobbelt -s rød", "stk", "tag", 15.00);
        RoofType type = new RoofType(2,"Betontagsten - rød", "slope", roof_materialOne, roof_materialTwo);
        
        Roof SlopeRoof = new Roof(1,15, type);
        
        Shed shed = new Shed(1, 200, 200);
        
        Carport carport = new Carport(650, 650, SlopeRoof, shed);
        
        FunctionManager instance = new FunctionManager();
        
         int expResult = 13;
      
        instance.calcShed(carport);
          
        int result = carport.getShed().getParts().size();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of getSlopedRoofs method, of class FunctionManager.
     */
    @Test
    public void testGetSlopedRoofsListSize() throws Exception {
        System.out.println("getSlopedRoofs");
        FunctionManager instance = new FunctionManager();
        int expResult = 5;
        int result = instance.getSlopedRoofs().size();
        assertEquals(expResult, result);
        
        
    }
    
  

    /**
     * Test of getFlatRoofs method, of class FunctionManager.
     */
    @Test
    public void testGetFlatRoofs() throws Exception {
        System.out.println("getFlatRoofs");
        FunctionManager instance = new FunctionManager();
        int expResult = 1;
        int result = instance.getFlatRoofs().size();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getRoofType method, of class FunctionManager.
     */
    @Test
    public void testGetRoofType() throws Exception {
        System.out.println("getRoofType");
        
        Material roof_materialOne = new Material(39, "B & C rygsten rød", "stk", "tag", 15.00);
        Material roof_materialTwo = new Material(36, "B & C dobbelt -s rød", "stk", "tag", 15.00);
        RoofType type = new RoofType(2,"Betontagsten - rød", "slope", roof_materialOne, roof_materialTwo);
        
        String roofkind = "slope";
        int typeId = 2;
        FunctionManager instance = new FunctionManager();
        RoofType expResult = null;
        RoofType result = instance.getRoofType(roofkind, typeId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }


    
}
