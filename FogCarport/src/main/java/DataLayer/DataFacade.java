/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer;

import FunctionLayer.Enum.Paid;
import FunctionLayer.Enum.Status;
import FunctionLayer.HelpingClasses.Carport;
import FunctionLayer.HelpingClasses.Material;
import FunctionLayer.HelpingClasses.Order;
import FunctionLayer.HelpingClasses.Roof;
import FunctionLayer.HelpingClasses.RoofType;
import FunctionLayer.HelpingClasses.User;
import java.util.List;

/**
 *
 * @author sofieamalielandt
 */
public class DataFacade
{

    private static DataFacade instance = null;
    private DBConnector dbc = new DBConnector();
    private DataSourceMysql dataSource = new DataSourceMysql();
    private MaterialMapper mm = new MaterialMapper(dbc);
    private CarportMapper cm = new CarportMapper(dbc);
    private UserMapper um = new UserMapper(dbc);

    /**
     *
     * @throws DataException if initializing not possible
     */
    public DataFacade() throws DataException
    {
        dbc.setDataSource(dataSource.getDataSource());
    }

    /**
     * Creates and initializes an instance of DataFacade
     *
     * @return an instance of DataFacade as a singleton
     * @throws DataException if initializing not possible
     */
    public static DataFacade getInstance() throws DataException
    {
        if (instance == null)
        {
            instance = new DataFacade();
        }
        return instance;
    }

    /**
     *
     * @param user_id is used to detect the specific User
     * @return an object from the class User with the specific user_id
     * @throws DataException if retrieval not possible
     * @see DataLayer.UserMapper#getUser(int) 
     */
    public User getUser(int user_id) throws DataException
    {
        return um.getUser(user_id);
    }

    /**
     * @return a list of object from the class User
     * @throws DataException if retrieval not possible
     * @see DataLayer.UserMapper#getUsers()
     */
    public List<User> getUsers() throws DataException
    {
        return um.getUsers();
    }

    /**
     * 
     * @param email is used to detect the specific User
     * @return an object from the class User with the specific email
     * @throws DataException if retrieval not possible
     * @see DataLayer.UserMapper#getEmployeeByEmail(java.lang.String)
     */
    public User getEmployeeByEmail(String email) throws DataException
    {
        return um.getEmployeeByEmail(email);
    }

    /**
     *
     * @return a list of object from the class User, with all the employees and admins
     * @throws DataException if retrieval not possible
     * @see DataLayer.UserMapper#getEmployeesAndAdmins()
     */
    public List<User> getEmployeesAndAdmins() throws DataException
    {
        return um.getEmployeesAndAdmins();
    }

    /**
     * Retrieve a User associated with the specific email and password in database
     *
     * @param email is used to detect the specific User
     * @param password a String
     * @return an object from the class User
     * @throws DataException if retrieval not possible
     * @see DataLayer.UserMapper#login(java.lang.String, java.lang.String)
     */
    public User login(String email, String password) throws DataException
    {
        return um.login(email, password);
    }

    /**
     *
     * @param newUser is used to detect the specific User
     * @throws DataException if insert is not possible
     * @see DataLayer.UserMapper#addUser(FunctionLayer.HelpingClasses.User)
     */
    public void newUser(User newUser) throws DataException
    {
        um.addUser(newUser);
    }

    /**
     * @param user is used to detect the specific User, and remove from the database
     * @throws DataException if execute is not possible
     */
    public void removeUser(User user) throws DataException
    {
        um.removeUser(user);
    }

    /**
     * Updates the information about a user in the database
     *
     * @param user is used to detect the specific User - the User to be updated
     * @throws DataException if retrieval or update of user is not possible
     * @see DataLayer.UserMapper#updateUser(FunctionLayer.HelpingClasses.User)
     */
    public void updateUser(User user) throws DataException
    {
        um.updateUser(user);
    }

    /**
     * Updates the password of a specific User
     *
     * @param user_id is used to detect the specific User 
     * @param password a String to be updated
     * @throws DataException if retrieval or update of user is not possible
     * @see DataLayer.UserMapper#updatePassword(int, java.lang.String)
     */
    public void updatePassword(int user_id, String password) throws DataException
    {
        um.updatePassword(user_id, password);
    }

    /**
     * Retrieve a specific order from the database 
     *
     * @param order_id is used to detect the specific order
     * @return an object from the class Order
     * @throws DataException if retrieval not possible
     */
    public Order getOrder(int order_id) throws DataException
    {
        return cm.getOrder(order_id);
    }

    /**
     * Retrieves all orders from the database
     *
     * @return a list of object from the class Order
     * @throws DataException if retrieval not possible
     */
    public List<Order> getOrders() throws DataException
    {
        return cm.getOrders();
    }

    /**
     *
     * @param email a String - is used to detect a list of order made by a user with the specific email
     * @return a list of object from the class Order
     * @throws DataException if retrieval not possible
     * @see DataLayer.CarportMapper#getOrdersByEmail(java.lang.String)
     */
    public List<Order> getOrdersByEmail(String email) throws DataException
    {
        return cm.getOrdersByEmail(email);
    }

    /**
     *
     * @param order the Order to be inserted in database 
     * @throws DataException if insert or update is not possible
     * @see DataLayer.CarportMapper#placeOrder(FunctionLayer.HelpingClasses.Order)
     */
    public void placeOrder(Order order) throws DataException
    {
        cm.placeOrder(order);
    }

    /**
     *
     * @param order is used to detect the specific Order, and remove from the database
     * @throws DataException if execute is not possible
     * @see DataLayer.CarportMapper#removeOrder(FunctionLayer.HelpingClasses.Order)
     */
    public void removeOrder(Order order) throws DataException
    {
        cm.removeOrder(order);
    }

    /**
     * @param order_id an integer - is used to detect the specific Order
     * @return a String with status of shipping
     * @throws DataException if initializing not possible
     * @see DataLayer.CarportMapper#orderShipped(int)
     */
    public String orderShipped(int order_id) throws DataException
    {
        return cm.orderShipped(order_id);
    }

    /**
     * Updates salesprice in the database
     * 
     * @param order_id an integer - is used to detect the specific Order
     * @param salesprice a double - the new value to insert on salesprice
     * @throws DataException if retrieval or update of order is not possible
     * @see DataLayer.CarportMapper#updateSalesPrice(int, double)
     */
    public void updateSalesPrice(int order_id, double salesprice) throws DataException
    {
        cm.updateSalesPrice(order_id, salesprice);
    }

    /**
     * Updates Status and Paid in the database
     *
     * @param order_id an integer - is used to detect the specific Order
     * @param status enum - the new value to insert on Status
     * @param paid enum - the new value to insert on Paid
     * @throws DataException if retrieval or update of order is not possible
     * @see DataLayer.CarportMapper#updateStatusAndPaid(int, FunctionLayer.Enum.Status, FunctionLayer.Enum.Paid)
     */
    public void updateStatusAndPaid(int order_id, Status status, Paid paid) throws DataException
    {
        cm.updateStatusAndPaid(order_id, status, paid);
    }

    /**
     * The carport is inserted in the database with or without a shed
     *
     * @param carport - the carport to be inserted in database 
     * @throws DataException if initializing not possible
     * @see DataLayer.CarportMapper#orderCarportWithShed(FunctionLayer.HelpingClasses.Carport)
     * @see DataLayer.CarportMapper#orderCarportWithoutShed(FunctionLayer.HelpingClasses.Carport)
     */
    public void orderCarport(Carport carport) throws DataException
    {
        if (carport.getShed() == null)
        {
            cm.orderCarportWithoutShed(carport);
        } else
        {
            cm.orderCarportWithShed(carport);
        }
    }

    /**
     *
     * @param carport the carport to be removed in database 
     * @throws DataException if execute is not possible
     * @see DataLayer.CarportMapper#removeCarport(FunctionLayer.HelpingClasses.Carport)
     */
    public void removeCarport(Carport carport) throws DataException
    {
        cm.removeCarport(carport);
    }

    /**
     * Removes the shed_id in the database from the carport,
     * for thereafter to be able to remove the shed in database
     *
     * @param carport - the carport where shed_id and shed are removed from in database 
     * @throws DataException if execute is not possible
     * @see DataLayer.CarportMapper#removeShed(FunctionLayer.HelpingClasses.Shed)
     * @see DataLayer.CarportMapper#removeShedId(FunctionLayer.HelpingClasses.Carport)
     */
    public void removeShed(Carport carport) throws DataException
    {
        cm.removeShedId(carport);
        cm.removeShed(carport.getShed());
    }

    /**
     *
     * @param roof the carport to be removed in database 
     * @throws DataException if execute is not possible
     * @see DataLayer.CarportMapper#removeRoof(FunctionLayer.HelpingClasses.Roof)
     */
    public void removeRoof(Roof roof) throws DataException
    {
        cm.removeRoof(roof);
    }

    /**
     * Updates information on a carport in the database also updates information
     * on the roof and shed connected with the carport
     *
     * @param carport an object from the class Carport - the Carport to be updated
     * @throws DataException if retrieval or update of carport is not possible
     * @see DataLayer.CarportMapper#updateCarport(FunctionLayer.HelpingClasses.Carport)
     */
    public void updateCarport(Carport carport) throws DataException
    {
        cm.updateCarport(carport);
        cm.updateRoof(carport.getRoof());
        if (carport.getShed() != null && carport.getShed().getId() != 0)
        {
            cm.updateShed(carport.getShed());
        }
    }

    /**
     *
     * @param id an integer - is used to detect the specific RoofType
     * @return an object from the class RoofType
     * @throws DataException if retrieval not possible
     * @see DataLayer.MaterialMapper#getRoof(int)
     */
    public RoofType getRoof(int id) throws DataException
    {
        return mm.getRoof(id);
    }

    /**
     *
     * @return a list of objects from the class RoofType
     * @throws DataException if retrieval not possible
     * @see DataLayer.MaterialMapper#getRoofs()
     */
    public List<RoofType> getRoofs() throws DataException
    {
        return mm.getRoofs();
    }

    /**
     *
     * @param rooftype the RoofType to insert in database
     * @throws DataException if insert is not possible
     * @see DataLayer.MaterialMapper#addRoofType(FunctionLayer.HelpingClasses.RoofType)
     */
    public void addRoofType(RoofType rooftype) throws DataException
    {
        mm.addRoofType(rooftype);
    }

    /**
     * @param rooftype the RoofType to remove in database
     * @throws DataException if execute is not possible
     * @see DataLayer.MaterialMapper#deleteRooftype(FunctionLayer.HelpingClasses.RoofType)
     */
    public void deleteRoofType(RoofType rooftype) throws DataException
    {
        mm.deleteRooftype(rooftype);
    }

    /**
     * Checks if the rooftype has one or two materials, 
     * and updates information on a rooftype in the database.
     * 
     * @param rooftype an object from the class RoofType - the RoofType to be updated
     * @throws DataException if retrieval or update of rooftype is not possible
     * @see DataLayer.MaterialMapper#updateRoofType(FunctionLayer.HelpingClasses.RoofType)
     */
    public void updateRoofType(RoofType rooftype) throws DataException
    {
        if (rooftype.getM2() != null)
        {
            mm.updateRoofType(rooftype);
        } else
        {
            mm.updateRoofTypeWith1Material(rooftype);
        }
    }

    /**
     *
     * @param material_id an integer - is used to detect the specific Material
     * @return an object from the class Material
     * @throws DataException if retrieval not possible
     * @see DataLayer.MaterialMapper#getMaterial(int)
     */
    public Material getMaterial(int material_id) throws DataException
    {
        return mm.getMaterial(material_id);
    }

    /**
     *
     * @param material_name a String - is used to detect the specific Material
     * @return an object from the class Material
     * @throws DataException if retrieval not possible
     * @see DataLayer.MaterialMapper#getMaterial(java.lang.String)
     */
    public Material getMaterial(String material_name) throws DataException
    {
        return mm.getMaterial(material_name);
    }

    /**
     * 
     * @return a list of objects from the class Material
     * @throws DataException if retrieval not possible
     * @see DataLayer.MaterialMapper#getMaterials()
     */
    public List<Material> getMaterials() throws DataException
    {
        return mm.getMaterials();
    }

    /**
     *
     * @param newMaterial the Material to insert in database
     * @throws DataException if insert is not possible
     * @see DataLayer.MaterialMapper#addMaterial(FunctionLayer.HelpingClasses.Material)
     */
    public void addMaterial(Material newMaterial) throws DataException
    {
        mm.addMaterial(newMaterial);
    }

    /**
     *
     * @param material the Material to delete in database
     * @throws DataException if execute is not possible
     * @see DataLayer.MaterialMapper#deleteMaterial(FunctionLayer.HelpingClasses.Material)
     */
    public void deleteMaterial(Material material) throws DataException
    {
        mm.deleteMaterial(material);
    }

    /**
     * 
     * @param material an object from the class Material - the Material to be updated
     * @throws DataException if retrieval or update of material is not possible
     * @see DataLayer.MaterialMapper#updateMaterial(FunctionLayer.HelpingClasses.Material)
     */
    public void updateMaterial(Material material) throws DataException
    {
        mm.updateMaterial(material);
    }
}
