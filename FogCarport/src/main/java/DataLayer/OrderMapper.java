/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer;

import FunctionLayer.HelpingClasses.Order;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author aamandajuhl
 */
public class OrderMapper
{

    private DBConnector dbc;

    public OrderMapper(DBConnector dbc)
    {
        this.dbc = dbc;
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

}
