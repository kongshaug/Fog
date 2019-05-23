/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

import DataLayer.DataException;
import DataLayer.DataFacadeTest;
import FunctionLayer.Enum.Paid;
import FunctionLayer.Enum.Role;
import FunctionLayer.Enum.Status;
import FunctionLayer.HelpingClasses.Carport;
import FunctionLayer.HelpingClasses.RoofType;
import FunctionLayer.HelpingClasses.User;
import FunctionLayer.HelpingClasses.Material;
import FunctionLayer.HelpingClasses.Order;
import FunctionLayer.HelpingClasses.Roof;
import FunctionLayer.HelpingClasses.Shed;
import java.text.SimpleDateFormat;
import java.util.Date;
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
     *
     * @throws DataLayer.DataException
     */
    @Test
    public void testGetInstance() throws DataException
    {
        FunctionManager result = FunctionManager.getInstance();
        assertNotNull(result);

    }

    /**
     * Test of login method, of class FunctionManager.
     *
     * @throws DataLayer.DataException
     */
    @Test
    public void testLogin() throws DataException
    {
        String email = "customer@hotmail.dk";
        String password = "1234";
        User user = fm.login(email, password);

        assertNotNull(user);
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
    }

    /**
     * Test of newUser and removeUser method, of class FunctionManager.
     *
     * @throws DataLayer.DataException
     */
    @Test
    public void testNewUserAndRemoveUser() throws DataException
    {
        String email = "newUser@hotmail.com";
        String password = "1234";

        User user = new User(email, password, "Navn", "Adresse", "2200", "11111111", Role.CUSTOMER);

        String expResult = "Din bruger er nu oprettet";
        String result = fm.newUser(user);
        assertEquals(expResult, result);

        expResult = "Din bruger er nu slettet";
        result = fm.removeUser(user);
        assertEquals(expResult, result);
    }

    /**
     * Test of newUser method, of class FunctionManager.
     *
     * @throws DataLayer.DataException
     */
    @Test
    public void testNegativeNewUser() throws DataException
    {
        //test email allready in use
        String email = "test@hotmail.dk";
        String password = "1234";
        String name = "Navn";
        String adress = "Adresse";
        String zipcode = "2200";
        String phone = "11111111";

        User user = new User("customer@hotmail.dk", password, name, adress, zipcode, phone, Role.CUSTOMER);

        String expResult = "Email er allerede i brug\n";
        String result = fm.newUser(user);
        assertEquals(expResult, result);

        //test email must contain "." and "@"
        user = new User("customerhotmaildk", password, name, adress, zipcode, phone, Role.CUSTOMER);
        expResult = "Venligst indtast en gyldig email\n";
        result = fm.newUser(user);
        assertEquals(expResult, result);

        //test name must only contain letters
        user = new User(email, password, "Navn1234", adress, zipcode, phone, Role.CUSTOMER);
        expResult = "Venligst indtast dit navn (må kun indeholde bogstaver)\n";
        result = fm.newUser(user);
        assertEquals(expResult, result);

        //test password must be 4 characters
        user = new User(email, "123", name, adress, zipcode, phone, Role.CUSTOMER);
        expResult = "Venligst indtast en adgangskode med en minimumslængde på 4\n";
        result = fm.newUser(user);
        assertEquals(expResult, result);

        //test adress not empty
        user = new User(email, password, name, "", zipcode, phone, Role.CUSTOMER);
        expResult = "Venligst indtast din adresse\n";
        result = fm.newUser(user);
        assertEquals(expResult, result);

        //test zipcode must be 4 characters
        user = new User(email, password, name, adress, "123", phone, Role.CUSTOMER);
        expResult = "Venligst indtast et gyldigt postnummer på 4 cifre\n";
        result = fm.newUser(user);
        assertEquals(expResult, result);

        //test zipcode must be digits
        user = new User(email, password, name, adress, "abcd", phone, Role.CUSTOMER);
        expResult = "Venligst indtast et gyldigt postnummer på 4 cifre\n";
        result = fm.newUser(user);
        assertEquals(expResult, result);

        //test phonenumber must be 8 characters
        user = new User(email, password, name, adress, zipcode, "123456789", Role.CUSTOMER);
        expResult = "Venligst indtast et gyldigt 8-cifret telefonnummer\n";
        result = fm.newUser(user);
        assertEquals(expResult, result);

        //test phonenumber must be digits
        user = new User(email, password, name, adress, zipcode, "acbdefghi", Role.CUSTOMER);
        expResult = "Venligst indtast et gyldigt 8-cifret telefonnummer\n";
        result = fm.newUser(user);
        assertEquals(expResult, result);

    }

    /**
     * Test of placeOrder, isShipped and removeOrder method, of class
     * FunctionManager.
     *
     * @see DataFacadeTest#testOrderCarportAndPlaceOrderAndOrderShipped()
     * @throws DataLayer.DataException
     */
    @Test
    public void testPlaceOrderAndIsShipped() throws DataException
    {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        RoofType rooftype = fm.getRoofTypeById(2);
        Roof roof = new Roof(15, rooftype);
        Shed shed = new Shed(470, 270);
        Carport carport = new Carport(500, 500, roof, shed);

        assertTrue(carport.getId() == 0);
        assertTrue(carport.getRoof().getId() == 0);
        assertTrue(carport.getShed().getId() == 0);

        User user = fm.getUser(1);
        Order order = new Order(user, carport);

        assertTrue(order.getOrder_id() == 0);
        assertNull(order.getOrder_date());

        fm.placeOrder(order);

        assertTrue(order.getOrder_id() != 0);
        assertNotNull(order.getOrder_date());

        assertTrue(carport.getId() != 0);
        assertTrue(carport.getRoof().getId() != 0);
        assertTrue(carport.getShed().getId() != 0);

        //test isShipped
        assertEquals("Ordren er endnu ikke afsendt", order.getShipped());
        fm.isShipped(order);
        Date today = new Date();
        assertEquals(dateFormatter.format(today), order.getShipped());

        //test removeOrder
        fm.removeOrder(order);
        assertNull(fm.getOrder(order.getOrder_id()));
    }

    /**
     * Negative test of placeOrder method, of class FunctionManager.
     *
     * @see DataFacadeTest#testOrderCarportAndPlaceOrderAndOrderShipped()
     * @throws DataLayer.DataException
     */
    @Test
    public void negativeTestPlaceOrder() throws DataException
    {
        Order order = null;
        assertEquals("Din forespørgsel kunne ikke blive sendt", fm.placeOrder(order));
    }

    /**
     * Test of calcCarport method, of class FunctionManager.
     *
     * @throws DataLayer.DataException
     */
    @Test
    public void testCalcCarport() throws DataException
    {
        Roof roof = new Roof(15, fm.getRoofTypeById(2));
        Shed shed = new Shed(200, 200);
        Carport carport = new Carport(500, 450, roof, shed);

        //calcCarport calls 2 methods one that puts 3 elements in the partlist,
        //and one that puts 1 element in the partlist therefor there should be 4 elements in the list
        fm.calcCarport(carport);
        assertEquals(4, carport.getParts().size());

        //a pole is placed for each 2 meter on the right and left side of the carport
        assertEquals(6, carport.getParts().get(0).getQuantity());

        //there are used 4 bolts for each pole
        assertEquals(24, carport.getParts().get(1).getQuantity());

        //each rem on the carport is as long as the depth of the carport
        assertEquals(carport.getDepth(), carport.getParts().get(3).getLength());
    }

    /**
     * Test of calcCarport method, of class FunctionManager, test's if the
     * carport's partlist is null if the mesurments is too big.
     *
     * @throws DataLayer.DataException
     */
    @Test
    public void testTooBigCalcCarport() throws DataException
    {
        Roof roof = new Roof(15, fm.getRoofTypeById(2));
        Shed shed = new Shed(200, 200);
        Carport carport = new Carport(800, 800, roof, shed);

        fm.calcCarport(carport);
        assertEquals(0, carport.getParts().size());
    }

    /**
     * Test of calcRoof method, of class FunctionManager.
     *
     * @throws DataLayer.DataException
     */
    @Test
    public void testCalcRoof() throws DataException
    {
        Roof roof = new Roof(15, fm.getRoofTypeById(2));
        Carport carport = new Carport(500, 450, roof);

        //test calcSlopedRoof
        //Because the roof is sloped, with calcRoof calls a method that puts 19 elements in the partlist,
        fm.calcRoof(carport);
        assertEquals(21, carport.getRoof().getParts().size());

        assertEquals("Taglægterne monteres på spærene", carport.getRoof().getParts().get(0).getDescription());
        assertEquals(18, carport.getRoof().getParts().get(0).getQuantity());
        assertEquals(450, carport.getRoof().getParts().get(0).getLength());

        assertEquals("Bundstykker til spær", carport.getRoof().getParts().get(1).getDescription());
        assertEquals(7, carport.getRoof().getParts().get(1).getQuantity());
        assertEquals(500, carport.getRoof().getParts().get(1).getLength());

        assertEquals("Sidestykker til spær", carport.getRoof().getParts().get(3).getDescription());
        assertEquals(14, carport.getRoof().getParts().get(3).getQuantity());
        assertEquals(258, carport.getRoof().getParts().get(3).getLength());

        roof = new Roof(0, fm.getRoofTypeById(1));
        carport = new Carport(500, 450, roof);

        //test calcFlatRoof
        //Because the roof is flat, with calcRoof calls 2 methods that puts 19 elements in the partlist,
        fm.calcRoof(carport);
        assertEquals(16, carport.getRoof().getParts().size());

        assertEquals("Spær monteres på tværs af de 2 remme", carport.getRoof().getParts().get(0).getDescription());
        assertEquals(9, carport.getRoof().getParts().get(0).getQuantity());
        assertEquals(500, carport.getRoof().getParts().get(0).getLength());

        assertEquals("Lægges på tværs af spærene", carport.getRoof().getParts().get(4).getDescription());
        assertEquals(9, carport.getRoof().getParts().get(4).getQuantity());
        assertEquals(450, carport.getRoof().getParts().get(4).getLength());

        assertEquals("Tagplader monteres på lægter", carport.getRoof().getParts().get(14).getDescription());
        assertEquals(6, carport.getRoof().getParts().get(14).getQuantity());
        assertEquals(450, carport.getRoof().getParts().get(14).getLength());
    }

    /**
     * Test of calcShed method, of class FunctionManager.
     *
     * @throws DataLayer.DataException
     */
    @Test
    public void testCalcShed() throws DataException
    {
        Roof roof = new Roof(15, fm.getRoofTypeById(2));
        Shed shed = new Shed(200, 200);
        Carport carport = new Carport(500, 450, roof, shed);

        fm.calcShed(carport);
        assertEquals(13, carport.getShed().getParts().size());
    }

//    /**
//     * Test of getSlopedRoofs method, of class FunctionManager.
//     * @throws DataLayer.DataException
//     */
//    @Test
//    public void testGetSlopedRoofsListSize() throws DataException
//    {
//        int result = fm.getSlopedRoofs().size();
//        assertNotNull(result);
//    }
//
//    /**
//     * Test of getRoofType method, of class FunctionManager.
//     * @throws DataLayer.DataException
//     */
//    @Test
//    public void testGetRoofType() throws DataException
//    {
//        int typeId = 2;
//        RoofType result = fm.getRoofTypeById(typeId);
//        assertEquals("Betontagsten - rød", result.getName());
//
//    }
    /**
     * Test of getFlatRoofs method, of class FunctionManager.
     *
     * @throws DataLayer.DataException
     */
    @Test
    public void testGetFlatRoofs() throws DataException
    {
        List<RoofType> flatroofs = fm.getFlatRoofs();

        for (RoofType flatroof : flatroofs)
        {
            assertTrue(flatroof.getRoof_class().equals("flat"));
        }
    }

//    /**
//     * Test of getEmployeesAndAdmins method, of class FunctionManager.
//     */
//    @Test
//    public void testGetEmployeesAndAdmins() throws Exception
//    {
//        System.out.println("getEmployeesAndAdmins");
//        FunctionManager instance = new FunctionManager();
//        List<User> expResult = null;
//        List<User> result = instance.getEmployeesAndAdmins();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getSlopedRoofs method, of class FunctionManager.
//     */
//    @Test
//    public void testGetSlopedRoofs() throws Exception
//    {
//        System.out.println("getSlopedRoofs");
//        FunctionManager instance = new FunctionManager();
//        List<RoofType> expResult = null;
//        List<RoofType> result = instance.getSlopedRoofs();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getRoofTypeById method, of class FunctionManager.
//     */
//    @Test
//    public void testGetRoofTypeById() throws Exception
//    {
//        System.out.println("getRoofTypeById");
//        int typeId = 0;
//        FunctionManager instance = new FunctionManager();
//        RoofType expResult = null;
//        RoofType result = instance.getRoofTypeById(typeId);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of calcRoof method, of class FunctionManager.
//     */
//    @Test
//    public void testCalcRoof() throws Exception
//    {
//        System.out.println("calcRoof");
//        Carport carport = null;
//        FunctionManager instance = new FunctionManager();
//        instance.calcRoof(carport);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getOrder method, of class FunctionManager.
//     */
//    @Test
//    public void testGetOrder() throws Exception
//    {
//        System.out.println("getOrder");
//        int order_id = 0;
//        FunctionManager instance = new FunctionManager();
//        Order expResult = null;
//        Order result = instance.getOrder(order_id);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getOrders method, of class FunctionManager.
//     */
//    @Test
//    public void testGetOrders() throws Exception
//    {
//        System.out.println("getOrders");
//        FunctionManager instance = new FunctionManager();
//        List<Order> expResult = null;
//        List<Order> result = instance.getOrders();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getOrdersByEmail method, of class FunctionManager.
//     */
//    @Test
//    public void testGetOrdersByEmail() throws Exception
//    {
//        System.out.println("getOrdersByEmail");
//        String email = "";
//        FunctionManager instance = new FunctionManager();
//        List<Order> expResult = null;
//        List<Order> result = instance.getOrdersByEmail(email);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of drawingOfRoof method, of class FunctionManager.
//     */
//    @Test
//    public void testDrawingOfRoof() throws DataException
//    {
//        System.out.println("drawingOfRoof");
//        Carport carport = null;
//        FunctionManager instance = new FunctionManager();
//        String expResult = "";
//        String result = instance.drawingOfRoof(carport);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of updateSalesPrice method, of class FunctionManager.
//     */
//    @Test
//    public void testUpdateSalesPrice() throws Exception
//    {
//        System.out.println("updateSalesPrice");
//        int order_id = 0;
//        double salesprice = 0.0;
//        FunctionManager instance = new FunctionManager();
//        instance.updateSalesPrice(order_id, salesprice);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of updateStatusAndPaid method, of class FunctionManager.
//     */
//    @Test
//    public void testUpdateStatusAndPaid() throws Exception
//    {
//        System.out.println("updateStatusAndPaid");
//        int order_id = 0;
//        Status status = null;
//        Paid paid = null;
//        FunctionManager instance = new FunctionManager();
//        instance.updateStatusAndPaid(order_id, status, paid);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getEmployeeByEmail method, of class FunctionManager.
//     */
//    @Test
//    public void testGetEmployeeByEmail() throws Exception
//    {
//        System.out.println("getEmployeeByEmail");
//        String email = "";
//        FunctionManager instance = new FunctionManager();
//        User expResult = null;
//        User result = instance.getEmployeeByEmail(email);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of updateEmployee method, of class FunctionManager.
//     */
//    @Test
//    public void testUpdateEmployee() throws Exception
//    {
//        System.out.println("updateEmployee");
//        User user = null;
//        String email = "";
//        String name = "";
//        String address = "";
//        String zipcode = "";
//        String phone = "";
//        FunctionManager instance = new FunctionManager();
//        String expResult = "";
//        String result = instance.updateEmployee(user, email, name, address, zipcode, phone);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of updateCustomer method, of class FunctionManager.
//     */
//    @Test
//    public void testUpdateCustomer() throws Exception
//    {
//        System.out.println("updateCustomer");
//        User user = null;
//        String email = "";
//        String name = "";
//        String oldpassword = "";
//        String newpassword = "";
//        String address = "";
//        String zipcode = "";
//        String phone = "";
//        FunctionManager instance = new FunctionManager();
//        String expResult = "";
//        String result = instance.updateCustomer(user, email, name, oldpassword, newpassword, address, zipcode, phone);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of updatePassword method, of class FunctionManager.
//     */
//    @Test
//    public void testUpdatePassword() throws Exception
//    {
//        System.out.println("updatePassword");
//        User user = null;
//        String oldpassword = "";
//        String newpassword = "";
//        FunctionManager instance = new FunctionManager();
//        String expResult = "";
//        String result = instance.updatePassword(user, oldpassword, newpassword);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getUser method, of class FunctionManager.
//     */
//    @Test
//    public void testGetUser() throws Exception
//    {
//        System.out.println("getUser");
//        int user_id = 0;
//        FunctionManager instance = new FunctionManager();
//        User expResult = null;
//        User result = instance.getUser(user_id);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of updateMaterial method, of class FunctionManager.
//     */
//    @Test
//    public void testUpdateMaterial() throws Exception
//    {
//        System.out.println("updateMaterial");
//        Material material = null;
//        String material_name = "";
//        String unit = "";
//        String material_class = "";
//        double price = 0.0;
//        FunctionManager instance = new FunctionManager();
//        String expResult = "";
//        String result = instance.updateMaterial(material, material_name, unit, material_class, price);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of deleteMaterial method, of class FunctionManager.
//     */
//    @Test
//    public void testDeleteMaterial() throws Exception
//    {
//        System.out.println("deleteMaterial");
//        Material material = null;
//        FunctionManager instance = new FunctionManager();
//        String expResult = "";
//        String result = instance.deleteMaterial(material);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of addMaterial method, of class FunctionManager.
//     */
//    @Test
//    public void testAddMaterial() throws Exception
//    {
//        System.out.println("addMaterial");
//        Material newMaterial = null;
//        FunctionManager instance = new FunctionManager();
//        String expResult = "";
//        String result = instance.addMaterial(newMaterial);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getAllMaterials method, of class FunctionManager.
//     */
//    @Test
//    public void testGetAllMaterials() throws Exception
//    {
//        System.out.println("getAllMaterials");
//        FunctionManager instance = new FunctionManager();
//        List<Material> expResult = null;
//        List<Material> result = instance.getAllMaterials();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getMaterial method, of class FunctionManager.
//     */
//    @Test
//    public void testGetMaterial() throws Exception
//    {
//        System.out.println("getMaterial");
//        int material_id = 0;
//        FunctionManager instance = new FunctionManager();
//        Material expResult = null;
//        Material result = instance.getMaterial(material_id);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of updateCarport method, of class FunctionManager.
//     */
//    @Test
//    public void testUpdateCarport() throws Exception
//    {
//        System.out.println("updateCarport");
//        Carport carport = null;
//        int carport_depth = 0;
//        int carport_width = 0;
//        RoofType rooftype = null;
//        int roofslope = 0;
//        int shed_width = 0;
//        int shed_depth = 0;
//        FunctionManager instance = new FunctionManager();
//        String expResult = "";
//        String result = instance.updateCarport(carport, carport_depth, carport_width, rooftype, roofslope, shed_width, shed_depth);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of addRoofType method, of class FunctionManager.
//     */
//    @Test
//    public void testAddRoofType() throws Exception
//    {
//        System.out.println("addRoofType");
//        RoofType rooftype = null;
//        FunctionManager instance = new FunctionManager();
//        String expResult = "";
//        String result = instance.addRoofType(rooftype);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getRoofs method, of class FunctionManager.
//     */
//    @Test
//    public void testGetRoofs() throws Exception
//    {
//        System.out.println("getRoofs");
//        FunctionManager instance = new FunctionManager();
//        List<RoofType> expResult = null;
//        List<RoofType> result = instance.getRoofs();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of deleteRoofType method, of class FunctionManager.
//     */
//    @Test
//    public void testDeleteRoofType() throws Exception
//    {
//        System.out.println("deleteRoofType");
//        RoofType rooftype = null;
//        FunctionManager instance = new FunctionManager();
//        String expResult = "";
//        String result = instance.deleteRoofType(rooftype);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of updateRoofType method, of class FunctionManager.
//     */
//    @Test
//    public void testUpdateRoofType() throws Exception
//    {
//        System.out.println("updateRoofType");
//        RoofType rooftype = null;
//        String name = "";
//        Material m1 = null;
//        Material m2 = null;
//        FunctionManager instance = new FunctionManager();
//        String expResult = "";
//        String result = instance.updateRoofType(rooftype, name, m1, m2);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of updateRoofTypeWith1Material method, of class FunctionManager.
//     */
//    @Test
//    public void testUpdateRoofTypeWith1Material() throws Exception
//    {
//        System.out.println("updateRoofTypeWith1Material");
//        RoofType rooftype = null;
//        String name = "";
//        Material m1 = null;
//        FunctionManager instance = new FunctionManager();
//        String expResult = "";
//        String result = instance.updateRoofTypeWith1Material(rooftype, name, m1);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of GDPRCheck method, of class FunctionManager.
//     */
//    @Test
//    public void testGDPRCheck() throws Exception
//    {
//        System.out.println("GDPRCheck");
//        List<Order> orders = null;
//        FunctionManager instance = new FunctionManager();
//        instance.GDPRCheck(orders);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
}
