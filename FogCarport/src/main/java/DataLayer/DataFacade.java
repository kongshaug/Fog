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

    public DataFacade() throws DataException
    {
        dbc.setDataSource(dataSource.getDataSource());
    }

    public static DataFacade getInstance() throws DataException
    {
        if (instance == null)
        {
            instance = new DataFacade();
        }
        return instance;
    }

    public Material getMaterial(String material_name) throws DataException
    {
        return mm.getMaterial(material_name);
    }

    public Material getMaterial(int material_id) throws DataException
    {
        return mm.getMaterial(material_id);
    }

    public List<Material> getMaterials() throws DataException
    {
        return mm.getMaterials();
    }

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

    public List<RoofType> getRoofs() throws DataException
    {
        return mm.getRoofs();
    }

    public RoofType getRoof(int id) throws DataException
    {
        return mm.getRoof(id);
    }

    public User getUser(int user_id) throws DataException
    {
        return um.getUser(user_id);
    }

    public List<User> getUsers() throws DataException
    {
        return um.getUsers();
    }

    public void newUser(User newUser) throws DataException
    {
        um.addUser(newUser);
    }

    public User login(String email, String password) throws DataException
    {
        return um.login(email, password);
    }

    public void placeOrder(Order order) throws DataException
    {
        cm.placeOrder(order);
    }

    public String orderShipped(int order_id) throws DataException
    {
        return cm.orderShipped(order_id);
    }

    public void removeUser(User user) throws DataException
    {
        um.removeUser(user);
    }

    public Order getOrder(int order_id) throws DataException
    {
        return cm.getOrder(order_id);
    }

    public List<Order> getOrders() throws DataException
    {
        return cm.getOrders();
    }

    public List<Order> getOrdersByEmail(String email) throws DataException
    {
        return cm.getOrdersByEmail(email);
    }

    public void updateSalesPrice(int order_id, double salesprice) throws DataException
    {
        cm.updateSalesPrice(order_id, salesprice);
    }

    public void updateStatusAndPaid(int order_id, Status status, Paid paid) throws DataException
    {
        cm.updateStatusAndPaid(order_id, status, paid);
    }
    
    public void removeOrder(Order order) throws DataException
    {
        cm.removeOrder(order);
    }
}
