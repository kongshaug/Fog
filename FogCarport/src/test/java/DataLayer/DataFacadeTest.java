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
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
        DataFacade result = DataFacade.getInstance();
        assertNotNull(result);
    }

    /**
     * Test of newUser and removeUser method, of class DataFacade.
     *
     * @throws DataLayer.DataException
     */
    @Test
    public void testNewUserAndRemoveUser() throws DataException
    {
        String email = "test@hotmail.com";
        String password = "1234";
        String name = "test";
        String address = "testvej 1";
        String zipcode = "1234";
        String phone = "88888888";
        Role role = Role.CUSTOMER;

        User newUser = new User(email, password, name, address, zipcode, phone, role);
        assertEquals(0, newUser.getId());
        df.newUser(newUser);
        assertTrue(newUser.getId() != 0);

        int before = df.getUsers().size();
        df.removeUser(newUser);
        int after = df.getUsers().size();
        assertNull(df.getUser(newUser.getId()));
        assertEquals(before - 1, after);
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
        User user = df.login(email, password);

        assertNotNull(user);
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
    }

    /**
     * Negative test of login method, of class DataFacade.
     *
     * @throws DataLayer.DataException
     */
    @Test
    public void NegativetestLogin() throws DataException
    {
        String email = "customer@hotmail.dk";
        String password = "4321";
        User user = df.login(email, password);

        //method returns null, when no user with the email and password combination is found in the database
        assertNull(user);
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
     * Negative test of getUser method, of class DataFacade.
     *
     * @throws DataLayer.DataException
     */
    @Test
    public void testNegativeGetUser() throws DataException
    {
        User user = df.getUser(222222222);
        assertNull(user);
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
        assertTrue(users.size() >= 4);
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
        assertNotNull(m);
        assertEquals("træ", m.getMaterial_class());

        Material m2 = df.getMaterial("1x20 mm hulbånd 10 mtr");
        assertNotNull(m2);
        assertEquals("beslag og skruer", m2.getMaterial_class());

        //Negative test
        Material m3 = df.getMaterial("TestMateriale");
        assertNull(m3);

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
        assertNotNull(m);
        assertEquals("tag", m.getMaterial_class());

        Material m2 = df.getMaterial(22);
        assertNotNull(m2);
        assertEquals("beslag og skruer", m2.getMaterial_class());

        //Negative test
        Material m3 = df.getMaterial(22222222);
        assertNull(m3);
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
        assertNotNull(materials);
        assertTrue(materials.size() >= 45);
    }

    /**
     * Test of getRoofs method, of class DataFacade.
     *
     * @throws DataLayer.DataException
     */
    @Test
    public void testGetRoofs() throws DataException
    {
        List<RoofType> rooftypes = df.getRoofs();
        assertNotNull(rooftypes);
        assertTrue(rooftypes.size() >= 5);
    }

    @Test
    public void testGetRoof() throws Exception
    {
        RoofType rooftype = df.getRoof(1);
        assertNotNull(rooftype);
        assertEquals("Plasttrapezplader", rooftype.getName());
        assertEquals("flat", rooftype.getRoof_class());

        //Negative test
        RoofType roof = df.getRoof(2222222);
        assertNull(roof);
    }

    /**
     * Test of orderCarport, placeOrder, orderShipped, removeOrder,
     * removeCarport, removeRoof and removeShed method, of class DataFacade.
     *
     * @throws DataLayer.DataException
     */
    @Test
    public void testOrderCarportAndPlaceOrderAndOrderShipped() throws DataException
    {
        RoofType rooftype = df.getRoof(1);

        //test orderCarport with roof and without shed
        Roof roof = new Roof(0, rooftype);
        Carport carport = new Carport(300, 400, roof);

        assertTrue(carport.getId() == 0);
        assertTrue(carport.getRoof().getId() == 0);

        df.orderCarport(carport);

        assertTrue(carport.getId() != 0);
        assertTrue(carport.getRoof().getId() != 0);

        //test orderCarport with roof and shed
        RoofType rooftype1 = df.getRoof(2);
        Roof roof1 = new Roof(15, rooftype1);
        Shed shed = new Shed(470, 270);
        Carport carport1 = new Carport(500, 500, roof1, shed);

        assertTrue(carport1.getId() == 0);
        assertTrue(carport1.getRoof().getId() == 0);
        assertTrue(carport1.getShed().getId() == 0);

        df.orderCarport(carport1);

        assertTrue(carport1.getId() != 0);
        assertTrue(carport1.getRoof().getId() != 0);
        assertTrue(carport1.getShed().getId() != 0);

        //test placeOrder
        User user = df.getUser(1);
        Order order = new Order(user, carport);
        Order order1 = new Order(user, carport1);

        assertTrue(order.getOrder_id() == 0);
        assertNull(order.getOrder_date());

        assertTrue(order1.getOrder_id() == 0);
        assertNull(order1.getOrder_date());

        df.placeOrder(order);
        df.placeOrder(order1);

        assertTrue(order.getOrder_id() != 0);
        assertNotNull(order.getOrder_date());

        assertTrue(order1.getOrder_id() != 0);
        assertNotNull(order1.getOrder_date());

        //test orderShipped
        assertEquals("Ordren er endnu ikke afsendt", order.getShipped());
        df.orderShipped(order.getOrder_id());
        assertNotEquals("Ordren er endnu ikke afsendt", df.getOrder(order.getOrder_id()).getShipped());

        //test removeOrder, test removeCarport, test removeRoof and test removeShed
        df.removeOrder(order);
        df.removeCarport(order.getCarport());
        df.removeRoof(order.getCarport().getRoof());
        assertNull(df.getOrder(order.getOrder_id()));

        df.removeOrder(order1);
        df.removeCarport(order1.getCarport());
        df.removeRoof(order1.getCarport().getRoof());
        df.removeShed(order1.getCarport().getShed());
        assertNull(df.getOrder(order1.getOrder_id()));
    }

    /**
     * Test of updateCarport method, of class DataFacade.
     *
     * @throws DataLayer.DataException
     */
    @Test
    public void testUpdateCarport() throws DataException
    {
        Order order = df.getOrder(2);
        int width = order.getCarport().getWidth();
        RoofType rooftype = order.getCarport().getRoof().getType();

        Shed shed = new Shed(244, 244);
        assertTrue(shed.getId() == 0);
        order.getCarport().setShed(shed);

        assertTrue(order.getCarport().getWidth() != 444);
        order.getCarport().setWidth(444);
        assertTrue(order.getCarport().getWidth() == 444);

        assertTrue(order.getCarport().getRoof().getType().getId() != 2);
        order.getCarport().getRoof().setType(df.getRoof(2));
        assertTrue(order.getCarport().getRoof().getType().getId() == 2);

        df.updateCarport(order.getCarport());

        //checking if the update is in the database
        Order Result = df.getOrder(order.getOrder_id());

        assertEquals(444, Result.getCarport().getWidth());
        assertEquals(2, Result.getCarport().getRoof().getType().getId());
        assertTrue(order.getCarport().getShed().getId() != 0);
        assertEquals(244, Result.getCarport().getShed().getWidth());

        //test deleteShedId and removeShed
        df.deleteShedId(order.getCarport());
        df.removeShed(order.getCarport().getShed());
        order.getCarport().setShed(null);
        assertNull(df.getOrder(order.getOrder_id()).getCarport().getShed());

        //changing attributes back to original
        assertTrue(order.getCarport().getWidth() != width);
        assertTrue(order.getCarport().getRoof().getType() != rooftype);

        order.getCarport().setWidth(width);
        order.getCarport().getRoof().setType(rooftype);

        assertTrue(order.getCarport().getWidth() == width);
        assertTrue(order.getCarport().getRoof().getType() == rooftype);

        df.updateCarport(order.getCarport());
    }

    /**
     * Test of getOrder method, of class DataFacade.
     *
     * @throws DataLayer.DataException
     */
    @Test
    public void testGetOrder() throws DataException
    {
        Order order = df.getOrder(1);
        assertNotNull(order);
        assertEquals(2, order.getUser().getId());
        assertEquals(Status.MODTAGET, order.getStatus());
    }

    /**
     * Test of getAllOrders method, of class DataFacade.
     *
     * @throws DataLayer.DataException
     */
    @Test
    public void testGetOrders() throws DataException
    {
        List<Order> orders = df.getOrders();
        assertNotNull(orders);
        assertTrue(orders.size() >= 4);
    }

    /**
     * Test of getAllOrdersByEmail method, of class DataFacade.
     *
     * @throws DataLayer.DataException
     */
    @Test
    public void testGetOrdersByEmail_String() throws DataException
    {
        String email = "hans@hotmail.dk";
        List<Order> orders = df.getOrdersByEmail(email);
        assertNotNull(orders);
        assertTrue(orders.size() >= 2);
    }

    /**
     * Test of addMaterial and deleteMaterial method, of class DataFacade.
     *
     * @throws DataLayer.DataException
     */
    @Test
    public void testAddMaterialAndDeleteMaterial() throws DataException
    {
        List<Material> MaterialList = df.getMaterials();
        Material material = new Material("testMaterial", "stk", "træ", 10.00);

        assertTrue(material.getId() == 0);
        df.addMaterial(material);
        assertTrue(material.getId() != 0);

        List MaterialListAfter = df.getMaterials();
        assertEquals(1 + MaterialList.size(), MaterialListAfter.size());
        df.deleteMaterial(material);
        assertEquals(MaterialList.size(), df.getMaterials().size());
    }

    /**
     * Test of updateSalesPrice method, of class DataFacade.
     *
     * @throws DataLayer.DataException
     */
    @Test
    public void testUpdateSalesPrice() throws DataException
    {
        Order order = df.getOrder(2);
        double salesprice = order.getSales_price();

        assertTrue(order.getSales_price() != 10000.0);
        df.updateSalesPrice(order.getOrder_id(), 10000.0);
        order = df.getOrder(2);
        assertEquals(10000.0, order.getSales_price(), 0.01);

        df.updateSalesPrice(order.getOrder_id(), salesprice);
    }

    /**
     * Test of updateStatusAndPaid method, of class DataFacade.
     *
     * @throws DataLayer.DataException
     */
    @Test
    public void testUpdateStatusAndPaid() throws DataException
    {
        Order order = df.getOrder(1);
        assertTrue(order.getPaid() == Paid.IKKE_BETALT);
        assertTrue(order.getStatus() == Status.MODTAGET);
        df.updateStatusAndPaid(order.getOrder_id(), Status.BEHANDLES, Paid.BETALT);
        order = df.getOrder(1);
        assertTrue(order.getPaid() == Paid.BETALT);
        assertTrue(order.getStatus() == Status.BEHANDLES);

        df.updateStatusAndPaid(order.getOrder_id(), Status.MODTAGET, Paid.IKKE_BETALT);
    }

    /**
     * Test of getEmployeeByEmail method, of class DataFacade.
     *
     * @throws DataLayer.DataException
     */
    @Test
    public void testGetEmployeeByEmail() throws DataException
    {
        String email = "hans@hotmail.dk";
        User user = df.getEmployeeByEmail(email);
        assertNotNull(user);
        assertEquals(email, user.getEmail());
        assertEquals("Hans", user.getName());
    }

    /**
     * Test of getEmployeesAndAdmins method, of class DataFacade.
     *
     * @throws DataLayer.DataException
     */
    @Test
    public void testGetEmployeesAndAdmins() throws DataException
    {
        List<User> users = df.getEmployeesAndAdmins();

        for (User user : users)
        {
            assertTrue(user.getRole() == Role.EMPLOYEE || user.getRole() == Role.ADMIN);
        }
    }

    /**
     * Test of updateUser method, of class DataFacade.
     *
     * @throws DataLayer.DataException
     */
    @Test
    public void testUpdateUser() throws DataException
    {
        User user = df.getUser(3);

        assertEquals("hans@hotmail.dk", user.getEmail());
        assertEquals("Hans", user.getName());

        user.setName("Henrik");
        user.setEmail("henrik@hotmail.dk");
        df.updateUser(user);

        assertEquals("henrik@hotmail.dk", user.getEmail());
        assertEquals("Henrik", user.getName());

        user.setName("Hans");
        user.setEmail("hans@hotmail.dk");
        df.updateUser(user);
    }

    /**
     * Test of updatePassword method, of class DataFacade.
     *
     * @throws DataLayer.DataException
     */
    @Test
    public void testUpdatePassword() throws DataException
    {
        User user = df.getUser(1);
        assertEquals("1234", user.getPassword());
        df.updatePassword(1, "4321");

        user = df.getUser(1);
        assertEquals("4321", user.getPassword());

        df.updatePassword(1, "1234");
    }

    /**
     * Test of updateMaterial method, of class DataFacade.
     *
     * @throws DataLayer.DataException
     */
    @Test
    public void testUpdateMaterial() throws DataException
    {
        Material material = df.getMaterial(1);
        assertEquals("25x150 mm trykimp. bræt", material.getName());
        assertEquals(1, material.getId());
        assertEquals(50.00, material.getPrice(), 0.01);

        material.setPrice(25.00);
        df.updateMaterial(material);

        material = df.getMaterial(1);
        assertEquals(25.00, material.getPrice(), 0.01);

        material.setPrice(50.00);
        df.updateMaterial(material);
    }

    /**
     * Test of addRoofType and deleteRoofType method, of class DataFacade.
     *
     * @throws DataLayer.DataException
     */
    @Test
    public void testAddRoofTypeAndDeleteRoofType() throws DataException
    {
        List<RoofType> rooftypes = df.getRoofs();
        RoofType roof = new RoofType("TestRoof", "slope", df.getMaterial(1), df.getMaterial(2));

        assertTrue(roof.getId() == 0);
        df.addRoofType(roof);
        assertTrue(roof.getId() != 0);

        //test deleteRoofType
        List<RoofType> rooftypesAfter = df.getRoofs();
        assertEquals(1 + rooftypes.size(), rooftypesAfter.size());
        df.deleteRoofType(roof);
        assertEquals(rooftypes.size(), df.getRoofs().size());
    }

    /**
     * Test of updateRoofType method, of class DataFacade.
     *
     * @throws DataLayer.DataException
     */
    @Test
    public void testUpdateRoofType() throws DataException
    {
        RoofType rooftype = df.getRoof(2);
        assertEquals("Betontagsten - rød", rooftype.getName());
        assertEquals(39, rooftype.getM1().getId());
        assertEquals(36, rooftype.getM2().getId());

        rooftype.setName("test");
        rooftype.setM1(df.getMaterial(1));
        rooftype.setM2(df.getMaterial(2));
        df.updateRoofType(rooftype);

        rooftype = df.getRoof(2);
        assertEquals("test", rooftype.getName());
        assertEquals(1, rooftype.getM1().getId());
        assertEquals(2, rooftype.getM2().getId());

        rooftype.setName("Betontagsten - rød");
        rooftype.setM1(df.getMaterial(39));
        rooftype.setM2(df.getMaterial(36));
        df.updateRoofType(rooftype);
    }

    /**
     * Test of updateRoofTypeWith1Material method, of class DataFacade.
     *
     * @throws DataLayer.DataException
     */
    @Test
    public void testUpdateRoofTypeWith1Material() throws DataException
    {
        RoofType rooftype = df.getRoof(1);
        assertEquals("Plasttrapezplader", rooftype.getName());
        assertEquals(12, rooftype.getM1().getId());

        rooftype.setName("test");
        rooftype.setM1(df.getMaterial(2));
        df.updateRoofTypeWith1Material(rooftype);

        rooftype = df.getRoof(1);
        assertEquals("test", rooftype.getName());
        assertEquals(2, rooftype.getM1().getId());

        rooftype.setName("Plasttrapezplader");
        rooftype.setM1(df.getMaterial(12));
        df.updateRoofTypeWith1Material(rooftype);
    }
}
