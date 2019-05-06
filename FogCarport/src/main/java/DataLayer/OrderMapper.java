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
import FunctionLayer.HelpingClasses.Order;
import FunctionLayer.HelpingClasses.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aamandajuhl
 */
public class OrderMapper
{

    private DBConnector dbc;
    private UserMapper um = null;
    private CarportMapper cm = null;

    public OrderMapper(DBConnector dbc) throws DataException
    {
        this.dbc = dbc;
        um = new UserMapper(dbc);
        cm = new CarportMapper(dbc);
    }

    public void placeOrder(Order order) throws DataException
    {
        try
        {
            dbc.open();

            String query = "INSERT INTO Fog.`order`"
                    + "(`user_id`, `carport_id`, `order_status`, `paid`, `sales_price`)"
                    + "VALUES (?,?,?,?,?);";

            int order_id;
            String order_date;
            int user_id = order.getUser().getId();
            int carport_id = order.getCarport().getId();
            String order_status = order.getStatus().toString();
            String paid = order.getPaid().toString();
            Double sales_price = order.getSales_price();

            PreparedStatement statement = dbc.preparedStatement(query, Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, user_id);
            statement.setInt(2, carport_id);
            statement.setString(3, order_status);
            statement.setString(4, paid);
            statement.setDouble(5, sales_price);
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next())
            {
                order_id = rs.getInt(1);
                order_date = getOrder_date(order_id);
                order.setOrder_id(order_id);
                order.setOrder_date(order_date);
            }

            dbc.close();

        } catch (SQLException e)
        {
            throw new DataException(e.getMessage());
        }

    }

    private String getOrder_date(int order_id) throws DataException
    {
        try
        {
            String query = "SELECT `order_date` FROM Fog.`order`"
                    + " WHERE (`order_id` = ?);";

            String order_date = "";

            PreparedStatement statement = dbc.preparedStatement(query);
            statement.setInt(1, order_id);
            ResultSet rs = statement.executeQuery();

            while (rs.next())
            {
                order_date = rs.getString("order_date");
            }

            return order_date;

        } catch (SQLException e)
        {
            throw new DataException(e.getMessage());
        }
    }

    public String orderShipped(int order_id) throws DataException
    {
        try
        {
            dbc.open();

            String query = "UPDATE Fog.`order`"
                    + "SET shipped = CURRENT_TIMESTAMP WHERE (order_id = ?);";

            String query2 = "SELECT shipped FROM order"
                    + " WHERE (`order_id` = ?);";

            String shipped = "";

            PreparedStatement statement = dbc.preparedStatement(query);
            statement.setInt(1, order_id);

            statement.executeUpdate();

            PreparedStatement statement2 = dbc.preparedStatement(query2);
            statement2.setInt(1, order_id);

            ResultSet rs = statement2.executeQuery();
            if (rs.next())
            {
                shipped = rs.getString("shipped");
            }

            dbc.close();

            return shipped;

        } catch (SQLException e)
        {
            throw new DataException(e.getMessage());
        }
    }

    public Order getOrder(int order_id) throws DataException
    {
        try
        {
            Order order = null;

            String query = "SELECT * FROM Fog.order"
                    + "WHERE `order_id` = ?;";

            PreparedStatement statement = dbc.preparedStatement(query);
            statement.setInt(1, order_id);

            ResultSet rs = statement.executeQuery();
            if (rs.next())
            {
                int id = rs.getInt("order_id");
                int user_id = rs.getInt("user_id");
                User user = um.getUser(user_id);
                int carport_id = rs.getInt("carport_id");
                Carport carport = cm.getCarport(carport_id);
                String order_date = rs.getString("order_date");
                String order_status = rs.getString("order_status");
                String shipped = rs.getString("shipped");
                String paid = rs.getString("paid");
                double sales_price = rs.getDouble("sales_price");

                Status s = Status.valueOf(order_status.toUpperCase());
                Paid p = Paid.valueOf(paid.toUpperCase());

                order = new Order(id, user, carport, order_date, s, shipped, p, sales_price);
            }

            dbc.close();

            return order;

        } catch (SQLException e)
        {
            throw new DataException(e.getMessage());
        }
    }

    public List<Order> getOrders() throws DataException
    {
        try
        {
            List<Order> orders = new ArrayList<>();

            dbc.open();

            String query = "SELECT * FROM Fog.order;";

            PreparedStatement statement = dbc.preparedStatement(query);
            ResultSet rs = statement.executeQuery();

            while (rs.next())
            {
                int id = rs.getInt("order_id");
                int user_id = rs.getInt("user_id");
                User user = um.getUser(user_id);
                int carport_id = rs.getInt("carport_id");
                Carport carport = cm.getCarport(carport_id);
                String order_date = rs.getString("order_date");
                String order_status = rs.getString("order_status");
                String shipped = rs.getString("shipped");
                String paid = rs.getString("paid");
                double sales_price = rs.getDouble("sales_price");

                Status s = Status.valueOf(order_status.toUpperCase());
                Paid p = Paid.valueOf(paid.toUpperCase());

                Order o = new Order(user_id, user, carport, order_date, s, shipped, p, sales_price);
                orders.add(o);
            }

            dbc.close();
            return orders;

        } catch (SQLException e)
        {
            throw new DataException(e.getMessage());
        }

//    }
//
//    public List<Order> getOrdersByEmail(String email) throws DataException
//    {
//        try
//        {
//            List<Order> orders = new ArrayList<>();
//
//            dbc.open();
//
//            String query = "SELECT * FROM Fog.order"
//                    + "INNER JOIN `user`"
//                    + "ON `order`.user_id = `user`.user_id"
//                    + "WHERE email = ?;";
//
//            PreparedStatement statement = dbc.preparedStatement(query);
//            ResultSet rs = statement.executeQuery();
//            
//            
//            
//            
//        } catch (SQLException e)
//        {
//            throw new DataException(e.getMessage());
//        }
//        
//        
//
//    }
    }
}
