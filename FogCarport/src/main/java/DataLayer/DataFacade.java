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
     * @throws DataException
     */
    public DataFacade() throws DataException
    {
        dbc.setDataSource(dataSource.getDataSource());
    }

    /**
     * call to get a instance of DataFacade
     *
     * @return an instance of DataFacade as a singelton
     * @throws DataException
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
     * @param user_id
     * @return
     * @throws DataException
     */
    public User getUser(int user_id) throws DataException
    {
        return um.getUser(user_id);
    }

    /**
     * gets a list of all the users in the database
     *
     * @return all users in the database
     * @throws DataException
     * @see DataLayer.UserMapper#getUsers()
     */
    public List<User> getUsers() throws DataException
    {
        return um.getUsers();
    }

    /**
     *
     * @param email String
     * @return employee with the email passed
     * @throws DataException
     * @see DataLayer.UserMapper#getEmployeeByEmail(java.lang.String)
     */
    public User getEmployeeByEmail(String email) throws DataException
    {
        return um.getEmployeeByEmail(email);
    }

    /**
     * gets all the employees and admins from the database
     *
     * @return list of all the employees and admins
     * @throws DataException
     * @see DataLayer.UserMapper#getEmployeesAndAdmins()
     */
    public List<User> getEmployeesAndAdmins() throws DataException
    {
        return um.getEmployeesAndAdmins();
    }

    /**
     * this method is used to check if a user enters the correct information
     * finds the user associated with the information passed
     *
     * @param email String
     * @param password String
     * @return a user if the correct email and password is passed otherwise it
     * returns null
     * @throws DataException
     * @see DataLayer.UserMapper#login(java.lang.String, java.lang.String)
     */
    public User login(String email, String password) throws DataException
    {
        return um.login(email, password);
    }

    /**
     * puts a new user in the database
     *
     * @param newUser
     * @throws DataException
     * @see DataLayer.UserMapper#addUser(FunctionLayer.HelpingClasses.User)
     */
    public void newUser(User newUser) throws DataException
    {
        um.addUser(newUser);
    }

    /**
     * removes a user from the database
     *
     * @param user object
     * @throws DataException
     */
    public void removeUser(User user) throws DataException
    {
        um.removeUser(user);
    }

    /**
     * updates the information about a user in the database
     *
     * @param user object
     * @throws DataException
     * @see DataLayer.UserMapper#updateUser(FunctionLayer.HelpingClasses.User)
     */
    public void updateUser(User user) throws DataException
    {
        um.updateUser(user);
    }

    /**
     * updates the password of a user assosiated with the id passed
     *
     * @param user_id int
     * @param password String
     * @throws DataException
     * @see DataLayer.UserMapper#updatePassword(int, java.lang.String)
     */
    public void updatePassword(int user_id, String password) throws DataException
    {
        um.updatePassword(user_id, password);
    }

    /**
     * findes and gets the order in the database with the passed id
     *
     * @param order_id int
     * @return order associated with the id passed
     * @throws DataException
     */
    public Order getOrder(int order_id) throws DataException
    {
        return cm.getOrder(order_id);
    }

    /**
     * gets all the orders in the database
     *
     * @return a list with all the orders in the database
     * @throws DataException
     */
    public List<Order> getOrders() throws DataException
    {
        return cm.getOrders();
    }

    /**
     *
     * @param email String
     * @return all the orders with the passed email
     * @throws DataException
     * @see DataLayer.CarportMapper#getOrdersByEmail(java.lang.String)
     */
    public List<Order> getOrdersByEmail(String email) throws DataException
    {
        return cm.getOrdersByEmail(email);
    }

    /**
     * puts an order in the database
     *
     * @param order object
     * @throws DataException
     * @see
     * DataLayer.CarportMapper#placeOrder(FunctionLayer.HelpingClasses.Order)
     */
    public void placeOrder(Order order) throws DataException
    {
        cm.placeOrder(order);
    }

    /**
     * Deletes an order from the database
     *
     * @param order
     * @throws DataException
     * @see
     * DataLayer.CarportMapper#removeOrder(FunctionLayer.HelpingClasses.Order)
     */
    public void removeOrder(Order order) throws DataException
    {
        cm.removeOrder(order);
    }

    /**
     * findes a order based on the id passed and returns the shipping status
     *
     * @param order_id integer
     * @return status of shipping
     * @throws DataException
     * @see DataLayer.CarportMapper#orderShipped(int)
     */
    public String orderShipped(int order_id) throws DataException
    {
        return cm.orderShipped(order_id);
    }

    /**
     * update the price of an order in the database
     *
     * @param order_id int
     * @param salesprice int
     * @throws DataException
     * @see DataLayer.CarportMapper#updateSalesPrice(int, double)
     */
    public void updateSalesPrice(int order_id, double salesprice) throws DataException
    {
        cm.updateSalesPrice(order_id, salesprice);
    }

    /**
     * updates the enum status and enum paid in the database
     *
     * @param order_id int
     * @param status enum
     * @param paid enum
     * @throws DataException
     * @see DataLayer.CarportMapper#updateStatusAndPaid(int,
     * FunctionLayer.Enum.Status, FunctionLayer.Enum.Paid)
     */
    public void updateStatusAndPaid(int order_id, Status status, Paid paid) throws DataException
    {
        cm.updateStatusAndPaid(order_id, status, paid);
    }

    /**
     * places an order in the database the method findes out if the order has a
     * shed or not and puts it in the database accordingly
     *
     * @param carport object
     * @throws DataException
     * @see
     * DataLayer.CarportMapper#orderCarportWithShed(FunctionLayer.HelpingClasses.Carport)
     * @see
     * DataLayer.CarportMapper#orderCarportWithoutShed(FunctionLayer.HelpingClasses.Carport)
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
     * Removes a carport from the database
     *
     * @param carport object
     * @throws DataException
     * @see
     * DataLayer.CarportMapper#removeCarport(FunctionLayer.HelpingClasses.Carport)
     */
    public void removeCarport(Carport carport) throws DataException
    {
        cm.removeCarport(carport);
    }

    /**
     * *deletes the id of a shed in the database from the caport this method is
     * used to remove the forain key in order to be able to delete the shed
     * afterwards, and removes a shed from the database
     *
     * @param carport
     * @throws DataException
     * @see
     * DataLayer.CarportMapper#removeShed(FunctionLayer.HelpingClasses.Shed)
     * @see
     * DataLayer.CarportMapper#removeShedId(FunctionLayer.HelpingClasses.Carport)
     */
    public void removeShed(Carport carport) throws DataException
    {
        cm.removeShedId(carport);
        cm.removeShed(carport.getShed());
    }

    /**
     * removes a roof in the database
     *
     * @param roof object
     * @throws DataException
     * @see
     * DataLayer.CarportMapper#removeRoof(FunctionLayer.HelpingClasses.Roof)
     */
    public void removeRoof(Roof roof) throws DataException
    {
        cm.removeRoof(roof);
    }

    /**
     * updates information on a carport in the database also updates information
     * on the roof and shead associated with the carport
     *
     * @param carport object
     * @throws DataException
     * @see
     * DataLayer.CarportMapper#updateCarport(FunctionLayer.HelpingClasses.Carport)
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
     * Gets a single roof from the database Finds the roof with the same id as
     * the paramater entered
     *
     * @param id integer
     * @return a single roof with a specific id
     * @throws DataException
     * @see DataLayer.MaterialMapper#getRoof(int)
     */
    public RoofType getRoof(int id) throws DataException
    {
        return mm.getRoof(id);
    }

    /**
     * gets all the roofs from the database
     *
     * @return list of roofs from the database
     * @throws DataException
     * @see DataLayer.MaterialMapper#getRoofs()
     */
    public List<RoofType> getRoofs() throws DataException
    {
        return mm.getRoofs();
    }

    /**
     * adds a new roof type in the database
     *
     * @param rooftype Object
     * @throws DataException
     * @see
     * DataLayer.MaterialMapper#addRoofType(FunctionLayer.HelpingClasses.RoofType)
     */
    public void addRoofType(RoofType rooftype) throws DataException
    {
        mm.addRoofType(rooftype);
    }

    /**
     * deletes a roof type in the database
     *
     * @param rooftype Object
     * @throws DataException
     * @see
     * DataLayer.MaterialMapper#deleteRooftype(FunctionLayer.HelpingClasses.RoofType)
     */
    public void deleteRoofType(RoofType rooftype) throws DataException
    {
        mm.deleteRooftype(rooftype);
    }

    /**
     * Updates information on a roof rype in the database
     *
     * @param rooftype Object
     * @throws DataException
     * @see
     * DataLayer.MaterialMapper#updateRoofType(FunctionLayer.HelpingClasses.RoofType)
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
     * Findes and returns a material from the database that has the same id as
     * the paramater id passed when calling the method
     *
     * @param material_id
     * @return a singel material from the database
     * @throws DataException
     * @see DataLayer.MaterialMapper#getMaterial(int)
     */
    public Material getMaterial(int material_id) throws DataException
    {
        return mm.getMaterial(material_id);
    }

    /**
     * findes and returns a material from the database that has the same name as
     * the paramater name passed when calling the method
     *
     * @param material_name String
     * @return a singel material from the database
     * @throws DataException
     * @see DataLayer.MaterialMapper#getMaterial(java.lang.String)
     */
    public Material getMaterial(String material_name) throws DataException
    {
        return mm.getMaterial(material_name);
    }

    /**
     * Gets all the materials in the database in a list
     *
     * @return All materials from the database as a list
     * @throws DataException
     * @see DataLayer.MaterialMapper#getMaterials()
     */
    public List<Material> getMaterials() throws DataException
    {
        return mm.getMaterials();
    }

    /**
     * adds a new material in the database
     *
     * @param newMaterial
     * @throws DataException
     * @see
     * DataLayer.MaterialMapper#addMaterial(FunctionLayer.HelpingClasses.Material)
     */
    public void addMaterial(Material newMaterial) throws DataException
    {
        mm.addMaterial(newMaterial);
    }

    /**
     * Removes a material from the database
     *
     * @param material object
     * @throws DataException
     * @see
     * DataLayer.MaterialMapper#deleteMaterial(FunctionLayer.HelpingClasses.Material)
     */
    public void deleteMaterial(Material material) throws DataException
    {
        mm.deleteMaterial(material);
    }

    /**
     * updates information on the material passed in the database
     *
     * @param material object
     * @throws DataException
     * @see
     * DataLayer.MaterialMapper#updateMaterial(FunctionLayer.HelpingClasses.Material)
     */
    public void updateMaterial(Material material) throws DataException
    {
        mm.updateMaterial(material);
    }
}
