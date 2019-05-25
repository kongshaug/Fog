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
import FunctionLayer.HelpingClasses.Material;
import FunctionLayer.HelpingClasses.RoofType;
import FunctionLayer.HelpingClasses.User;
import FunctionLayer.HelpingClasses.Order;
import FunctionLayer.HelpingClasses.Roof;
import FunctionLayer.HelpingClasses.Shed;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

        User user = new User(email, password, name, adress, zipcode, phone, Role.CUSTOMER);

        user.setEmail("customer@hotmail.dk");
        String expResult = "Email er allerede i brug\n";
        String result = fm.newUser(user);
        assertEquals(expResult, result);

        //test email must contain "." and "@"
        user.setEmail("customerhotmaildk");
        expResult = "Venligst indtast en gyldig email\n";
        result = fm.newUser(user);
        assertEquals(expResult, result);
        user.setEmail(email);

        //test name must only contain letters
        user.setName("Navn1234");
        expResult = "Venligst indtast dit navn (må kun indeholde bogstaver)\n";
        result = fm.newUser(user);
        assertEquals(expResult, result);
        user.setName(name);

        //test password must be 4 characters
        user.setPassword("123");
        expResult = "Venligst indtast en adgangskode med en minimumslængde på 4\n";
        result = fm.newUser(user);
        assertEquals(expResult, result);
        user.setPassword(password);

        //test adress not empty
        user.setAddress("");
        expResult = "Venligst indtast din adresse\n";
        result = fm.newUser(user);
        assertEquals(expResult, result);
        user.setAddress(adress);

        //test zipcode must be 4 characters
        user.setZipcode("123");
        expResult = "Venligst indtast et gyldigt postnummer på 4 cifre\n";
        result = fm.newUser(user);
        assertEquals(expResult, result);

        //test zipcode must be digits
        user.setZipcode("abcd");
        expResult = "Venligst indtast et gyldigt postnummer på 4 cifre\n";
        result = fm.newUser(user);
        assertEquals(expResult, result);
        user.setZipcode(zipcode);

        //test phonenumber must be 8 characters
        user.setPhone("123456789");
        expResult = "Venligst indtast et gyldigt 8-cifret telefonnummer\n";
        result = fm.newUser(user);
        assertEquals(expResult, result);

        //test phonenumber must be digits
        user.setPhone("acbdefghi");
        expResult = "Venligst indtast et gyldigt 8-cifret telefonnummer\n";
        result = fm.newUser(user);
        assertEquals(expResult, result);

    }

    /**
     * Test of updateCustomer method, of class FunctionManager.
     *
     * @throws DataLayer.DataException
     */
    @Test
    public void testUpdateCustomer() throws DataException
    {
        User user = fm.getUser(1);

        //test updateCustomer
        String result = fm.updateCustomer(user, user.getEmail(), "Bo", user.getPassword(), "4321", user.getAddress(), user.getZipcode(), "22222222");
        assertEquals("Dine information er opdateret", result);
        assertEquals("Bo", user.getName());
        assertEquals("22222222", user.getPhone());

        fm.updateCustomer(user, user.getEmail(), "Bent", user.getPassword(), "1234", user.getAddress(), user.getZipcode(), "11111111");
    }

    /**
     * Test of updateCustomer method, of class FunctionManager.
     *
     * @throws DataLayer.DataException
     */
    @Test
    public void negativeTestUpdateCustomer() throws DataException
    {
        //test email allready in use
        User user = fm.getUser(1);

        String email = user.getEmail();
        String password = user.getPassword();
        String name = user.getName();
        String adress = user.getAddress();
        String zipcode = user.getZipcode();
        String phone = user.getPhone();

        String expResult = "Email er allerede i brug\n";
        String result = fm.updateCustomer(user, "customer@hotmail.dk", name, password, password, adress, zipcode, phone);
        assertEquals(expResult, result);

        //test email must contain "." and "@"
        expResult = "Venligst indtast en gyldig email\n";
        result = fm.updateCustomer(user, "testhotmaildk", name, password, password, adress, zipcode, phone);
        assertEquals(expResult, result);

        //test must enter current password 
        expResult = "Venligst indtast din nuværende adgangskode, for at ændre adgangskoden\n";
        result = fm.updateCustomer(user, email, name, "4321", "5555", adress, zipcode, phone);
        assertEquals(expResult, result);

        //test password must be 4 characters
        expResult = "Venligst indtast en adgangskode med en minimumslængde på 4\n";
        result = fm.updateCustomer(user, email, name, "1234", "123", adress, zipcode, phone);
        assertEquals(expResult, result);

        //test name must only contain letters
        expResult = "Venligst indtast dit navn (må kun indeholde bogstaver)\n";
        result = fm.updateCustomer(user, email, "Test123", password, password, adress, zipcode, phone);
        assertEquals(expResult, result);

        //test adress not empty
        expResult = "Venligst indtast din adresse\n";
        result = fm.updateCustomer(user, email, name, password, password, "", zipcode, phone);
        assertEquals(expResult, result);

        //test zipcode must be 4 characters
        expResult = "Venligst indtast et gyldigt postnummer på 4 cifre\n";
        result = fm.updateCustomer(user, email, name, password, password, adress, "123", phone);
        assertEquals(expResult, result);

        //test zipcode must be digits
        expResult = "Venligst indtast et gyldigt postnummer på 4 cifre\n";
        result = fm.updateCustomer(user, email, name, password, password, adress, "abcd", phone);
        assertEquals(expResult, result);

        //test phonenumber must be 8 characters
        expResult = "Venligst indtast et gyldigt 8-cifret telefonnummer\n";
        result = fm.updateCustomer(user, email, name, password, password, adress, zipcode, "1234567");
        assertEquals(expResult, result);

        //test phonenumber must be digits
        expResult = "Venligst indtast et gyldigt 8-cifret telefonnummer\n";
        result = fm.updateCustomer(user, email, name, password, password, adress, zipcode, "1111A111");
        assertEquals(expResult, result);
    }

    /**
     * Test of updateEmployee and updatePassword method, of class
     * FunctionManager.
     *
     * @throws DataLayer.DataException
     */
    @Test
    public void testUpdateEmployeeAndUpdatePassword() throws DataException
    {
        User employee = new User("test@hotmail.com", "1234", "Test", "Adresse", "2200", "11111111", Role.EMPLOYEE);
        fm.newUser(employee);

        //test updateEmployee
        String result = fm.updateEmployee(employee, employee.getEmail(), "Nyt navn", employee.getAddress(), employee.getZipcode(), "22222222");
        assertEquals("Medarbejderens information er opdateret", result);
        assertEquals("Nyt navn", employee.getName());
        assertEquals("22222222", employee.getPhone());

        //test updatePassword
        result = fm.updatePassword(employee, employee.getPassword(), "4321");
        assertEquals("Din adgangskode er ændret", result);
        assertEquals("4321", employee.getPassword());
    }

    /**
     * Test of updateEmployee and updatePassword method, of class
     * FunctionManager.
     *
     * @throws DataLayer.DataException
     */
    @Test
    public void negativeTestUpdateEmployeeAndUpdatePassword() throws DataException
    {
        //test email allready in use
        User employee = fm.getEmployeeByEmail("test@hotmail.com");

        String email = employee.getEmail();
        String name = employee.getName();
        String adress = employee.getPassword();
        String zipcode = employee.getZipcode();
        String phone = employee.getPhone();

        String expResult = "Email er allerede i brug\n";
        String result = fm.updateEmployee(employee, "customer@hotmail.dk", name, adress, zipcode, phone);
        assertEquals(expResult, result);

        //test email must contain "." and "@"
        expResult = "Venligst indtast en gyldig email\n";
        result = fm.updateEmployee(employee, "testhotmaildk", name, adress, zipcode, phone);
        assertEquals(expResult, result);

        //test name must only contain letters
        expResult = "Venligst indtast dit navn (må kun indeholde bogstaver)\n";
        result = fm.updateEmployee(employee, email, "Test123", adress, zipcode, phone);
        assertEquals(expResult, result);

        //test adress not empty
        expResult = "Venligst indtast din adresse\n";
        result = fm.updateEmployee(employee, email, name, "", zipcode, phone);
        assertEquals(expResult, result);

        //test zipcode must be 4 characters
        expResult = "Venligst indtast et gyldigt postnummer på 4 cifre\n";
        result = fm.updateEmployee(employee, email, name, adress, "123", phone);
        assertEquals(expResult, result);

        //test zipcode must be digits
        expResult = "Venligst indtast et gyldigt postnummer på 4 cifre\n";
        result = fm.updateEmployee(employee, email, name, adress, "abcd", phone);
        assertEquals(expResult, result);

        //test phonenumber must be 8 characters
        expResult = "Venligst indtast et gyldigt 8-cifret telefonnummer\n";
        result = fm.updateEmployee(employee, email, name, adress, zipcode, "1234567");
        assertEquals(expResult, result);

        //test phonenumber must be digits
        expResult = "Venligst indtast et gyldigt 8-cifret telefonnummer\n";
        result = fm.updateEmployee(employee, email, name, adress, zipcode, "111A1111");
        assertEquals(expResult, result);

        //test updatePassword
        expResult = "Venligst indtast din nuværende adgangskode, for at ændre adgangskoden\n";
        result = fm.updatePassword(employee, "1234", "5555");
        assertEquals(expResult, result);

        expResult = "Venligst indtast en adgangskode med en minimumslængde på 4\n";
        result = fm.updatePassword(employee, employee.getPassword(), "123");
        assertEquals(expResult, result);

        fm.removeUser(employee);
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
     * Test of GDPRCheck method, of class FunctionManager.
     *
     * @throws DataLayer.DataException
     * @throws java.text.ParseException
     */
    @Test
    public void testGDPRCheck() throws DataException, ParseException
    {
        //creating todays date from 3 years ago
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -3); 
        Date date = cal.getTime();
        List<Order> orders = fm.getOrders();
 
        User user = fm.getUser(1);
        RoofType rooftype = fm.getRoofTypeById(2);
        Roof roof = new Roof(15, rooftype);
        Carport carport = new Carport(500, 500, roof);
        Order order = new Order(user, carport);
        order.setOrder_date(dateFormatter.format(date));
        orders.add(order);
        
        int expResult = orders.size() - 1;
        fm.GDPRCheck(orders);
        assertEquals(expResult, orders.size());
    }

    /**
     * Test of updateCarport method, of class FunctionManager.
     * @throws DataLayer.DataException
     */
    @Test
    public void testUpdateCarport() throws DataException
    {
        
        Order order = fm.getOrder(1);

        int width = order.getCarport().getWidth();
        int depth = order.getCarport().getDepth();
        RoofType rooftype = order.getCarport().getRoof().getType();
        int slope = order.getCarport().getRoof().getSlope();
        Shed shed = order.getCarport().getShed();
        
        //test updateCarport 
        String result = fm.updateCarport(order.getCarport(), 400, width, fm.getRoofTypeById(5), slope, 200, shed.getDepth());
        assertEquals("Carporten er opdateret", result);
        assertEquals(400, order.getCarport().getDepth());
        assertEquals(5, order.getCarport().getRoof().getType().getId());
        
        //test updateCarport removing shed
        assertNotNull(order.getCarport().getShed());
        result = fm.updateCarport(order.getCarport(), depth, width, rooftype, slope, 0, 0);
        assertEquals("Carporten er opdateret og skuret er fjernet", result);
        assertNull(order.getCarport().getShed());
        
        //test updateCarport 
        result = fm.updateCarport(order.getCarport(), 400, width, fm.getRoofTypeById(5),slope, 0, 0);
        assertEquals("Carporten er opdateret", result);
        assertEquals(400, order.getCarport().getDepth());
        assertEquals(5, order.getCarport().getRoof().getType().getId());
        
        //test updateCarport adding shed
        result = fm.updateCarport(order.getCarport(), depth, width, rooftype, slope, shed.getWidth(), shed.getDepth());
        assertEquals("Carporten er opdateret og skuret er tilføjet", result);
        assertNotNull(order.getCarport().getShed());
    }
    
    /**
     * Test of updateCarport method, of class FunctionManager.
     * @throws DataLayer.DataException
     */
    @Test
    public void negativeTestUpdateCarport() throws DataException
    {  
        Order order = fm.getOrder(1);

        int width = order.getCarport().getWidth();
        int depth = order.getCarport().getDepth();
        RoofType rooftype = order.getCarport().getRoof().getType();
        int slope = order.getCarport().getRoof().getSlope();
        Shed shed = order.getCarport().getShed();
        
        //test depth must be < 800 and width must be < 750
        String result = fm.updateCarport(order.getCarport(), 900, 800, rooftype, slope, shed.getWidth(), shed.getDepth());
        assertEquals("Carporten må maksimum være 750 cm bred og 800 cm dyb\n", result);
        
        //test slope can only be between 15 - 45 and divisible by 5
        result = fm.updateCarport(order.getCarport(), depth, width,rooftype, 10 , shed.getWidth(), shed.getDepth());
        assertEquals("Vælg venligst en hældning fra menuen\n", result);
        
        result = fm.updateCarport(order.getCarport(), depth, width,rooftype, 50 , shed.getWidth(), shed.getDepth());
        assertEquals("Vælg venligst en hældning fra menuen\n", result);
        
        result = fm.updateCarport(order.getCarport(), depth, width,rooftype, 26 , shed.getWidth(), shed.getDepth());
        assertEquals("Vælg venligst en hældning fra menuen\n", result);
        
        //test shedWidth and shedDepth must be least 30 less that the size of the carport 
        result = fm.updateCarport(order.getCarport(), depth, width,rooftype, slope, width, depth);
        assertEquals("Skuret må minimum være 30 cm kortere end selve carporten på begge led\n", result);
    }
    
    /**
     * Test of getSlopedRoofs method, of class FunctionManager.
     *
     * @throws DataLayer.DataException
     */
    @Test
    public void testGetSlopedRoofs() throws DataException
    {
        List<RoofType> slopedroofs = fm.getSlopedRoofs();

        for (RoofType roof : slopedroofs)
        {
            assertTrue(roof.getRoof_class().equals("slope"));
        }
    }

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

    /**
     * Test of addRoofType, addMaterial, deleteMaterial and deleteRoofType method, of class FunctionManager.
     * @throws DataLayer.DataException
     */
    @Test
    public void testAddAndDeleteRoofTypeAndMaterial() throws DataException
    {
        //test addMaterial
        Material m1 = new Material("Testtagsten", "stk", "tag", 8);
        Material m2 = new Material("Testvinkelrygning", "stk", "tag", 8);
        
        String expResult = "Materialet er tilføjet til listen";
        String result = fm.addMaterial(m1);
        assertEquals(expResult, result);
        
        expResult = "Materialet er tilføjet til listen";
        result = fm.addMaterial(m2);
        assertEquals(expResult, result);
        
        //test addRoofType
        RoofType rooftype = new RoofType("Testtag", "slope", m1, m2);
        expResult = "Tagtypen er tilføjet";
        result = fm.addRoofType(rooftype);
        assertEquals(expResult, result);

        //test deleteRoofType
        expResult = "Tagtypen er slettet";
        result = fm.deleteRoofType(rooftype);
        assertEquals(expResult, result);
        
        //test deleteMaterial
        expResult = "Materialet er slettet";
        result = fm.deleteMaterial(m1);
        assertEquals(expResult, result);
        
        expResult = "Materialet er slettet";
        result = fm.deleteMaterial(m2);
        assertEquals(expResult, result);
     
    }
    
     /**
     * Test of addRoofType method, of class FunctionManager.
     * @throws DataLayer.DataException
     */
    @Test
    public void negativeTestAddARoofType() throws DataException
    {
        Material m1 = new Material("Testtagsten", "stk", "tag", 8);
        Material m2 = new Material("Testvinkelrygning", "stk", "tag", 8);
        
        //test RoofType name allready in use
        RoofType rooftype = new RoofType("Plasttrapezplader", "slope", m1, m2);
        String expResult = "Tagtype med samme navn eksisterer allerede\n";
        String result = fm.addRoofType(rooftype);
        assertEquals(expResult, result);

        //test material allready in use (Material 1)
        rooftype = new RoofType("Testtag", "flat", fm.getMaterial(12));
        expResult = "Materialet er allerede tilknyttet en anden tagtype\n";
        result = fm.addRoofType(rooftype);
        assertEquals(expResult, result);
        
        //test material allready in use (Material 2)
        rooftype = new RoofType("Testtag", "flat", m1, fm.getMaterial(12));
        expResult = "Materialet er allerede tilknyttet en anden tagtype\n";
        result = fm.addRoofType(rooftype);
        assertEquals(expResult, result);
        
        //test roof class can only be "slope" or "flat"
        rooftype = new RoofType("Testtag", "test", m1, m2);
        expResult = "Vælg venligst imellem de to kategorier (fladt tag, tag med rejsning)\n";
        result = fm.addRoofType(rooftype);
        assertEquals(expResult, result);
        
        //test must have two different materials, or just one
        rooftype = new RoofType("Testtag", "flat", m1, m1);
        expResult = "Vælg venligst to forskellige materialer eller kun en enkelt\n";
        result = fm.addRoofType(rooftype);
        assertEquals(expResult, result);
     
    }


//    /**
//     * Test of updateRoofType method, of class FunctionManager.
//     */
//    @Test
//    public void testUpdateRoofType() throws Exception
//    {
//       
//    }
//
    //

//    /**
//     * Test of updateMaterial method, of class FunctionManager.
//     */
//    @Test
//    public void testUpdateMaterial() throws Exception
//    {
//    
//    }
//
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
        //shed with sloped roof
        Roof roof = new Roof(15, fm.getRoofTypeById(2));
        Shed shed = new Shed(200, 200);
        Carport carport = new Carport(500, 450, roof, shed);

        //The method calcShed has two methods, one for sloped roof and one for flat roof, each method puts 14 elements in the partlist,
        fm.calcShed(carport);
        assertEquals(13, carport.getShed().getParts().size());

        assertEquals("Stolper til hjørner af skur og siderne af døren til skuret", carport.getShed().getParts().get(4).getDescription());
        assertEquals(6, carport.getShed().getParts().get(4).getQuantity());
        assertEquals(300, carport.getShed().getParts().get(4).getLength());

        // the beklædning uses  19x100 mm. trykimp. Bræt
        // each bræt for BackFront and Sides is 10 cm, and each with a 3 cm overlay to the next one, therefor we divide with 7
        // 42 is retracted because the material for the door has already been added, a door is 84 cm therefor half is removed 
        assertEquals("Beklædning til skurets for- og bagside", carport.getShed().getParts().get(9).getDescription());
        assertEquals(44, carport.getShed().getParts().get(9).getQuantity());

        //bekældning is same height as carport, so that the customer can choose where to place it
        assertEquals("Beklædning til skurets sider (skal skæres til efter ønsket placering)", carport.getShed().getParts().get(10).getDescription());
        assertEquals(56, carport.getShed().getParts().get(10).getQuantity());
        assertEquals(263, carport.getShed().getParts().get(10).getLength());
        
        //shed with flat roof
        roof = new Roof(0, fm.getRoofTypeById(1));
        shed = new Shed(200, 200);
        carport = new Carport(500, 450, roof, shed);
        
        //The method calcShed has two methods, one for sloped roof and one for flat roof, each method puts 13 elements in the partlist,
        fm.calcShed(carport);
        assertEquals(13, carport.getShed().getParts().size());

        //bekældning is same height as carport, so that the customer can choose where to place it
        assertEquals("Beklædning til skurets sider", carport.getShed().getParts().get(10).getDescription());
        assertEquals(56, carport.getShed().getParts().get(10).getQuantity());
        assertEquals(200, carport.getShed().getParts().get(10).getLength());
    }

    /**
     * Test of drawingOfRoof method, of class FunctionManager.
     *
     * @throws DataLayer.DataException
     */
    @Test
    public void testDrawingOfRoof() throws DataException
    {
        Roof roof = new Roof(15, fm.getRoofTypeById(2));
        Shed shed = new Shed(200, 200);
        Carport carportWithShed = new Carport(500, 450, roof, shed);

        String drawingWithShed = "";
        assertEquals("", drawingWithShed);
        drawingWithShed = fm.drawingOfRoof(carportWithShed);
        assertTrue(drawingWithShed.length() > 200);

        roof = new Roof(0, fm.getRoofTypeById(1));
        Carport carportWithoutShed = new Carport(500, 450, roof);

        String drawingWithoutShed = "";
        assertEquals("", drawingWithoutShed);
        drawingWithoutShed = fm.drawingOfRoof(carportWithoutShed);
        assertTrue(drawingWithoutShed.length() < drawingWithShed.length());
    }
}
