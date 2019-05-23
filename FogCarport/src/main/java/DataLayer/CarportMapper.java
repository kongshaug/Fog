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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author sofieamalielandt
 */
public class CarportMapper
{
    private DBConnector dbc = new DBConnector();
    /**
     *
     * @param dbc
     * @throws DataException
     */
    public CarportMapper(DBConnector dbc) throws DataException
    {
        this.dbc = dbc;
    }

    private Carport getCarport(int carport_id) throws DataException
    {
        try
        {
            Carport carport = null;

            String query = "SELECT * FROM Fog.`carport`"
                    + "WHERE (`carport_id` = ?);";

            PreparedStatement statement = dbc.preparedStatement(query);
            statement.setInt(1, carport_id);
            ResultSet rs = statement.executeQuery();

            if (rs.next())
            {
                int id = rs.getInt("carport_id");
                int depth = rs.getInt("depth");
                int width = rs.getInt("width");
                int roof_id = rs.getInt("roof_id");
                int shed_id = rs.getInt("shed_id");
                Roof roof = getRoof(roof_id);
                Shed shed = getShed(shed_id);

                if (shed != null)
                {
                    carport = new Carport(id, width, depth, roof, shed);
                } else
                {
                    carport = new Carport(id, width, depth, roof);
                }
            }
            return carport;

        } catch (SQLException ex)
        {
            throw new DataException(ex.getMessage());
        }
    }

    private Roof getRoof(int roof_id) throws DataException
    {
        try
        {
            Roof roof = null;
            
            dbc.open();
            String query = "SELECT * FROM Fog.`roof`"
                    + "WHERE (`roof_id` = ?);";

            PreparedStatement statement = dbc.preparedStatement(query);
            statement.setInt(1, roof_id);
            ResultSet rs = statement.executeQuery();

            if (rs.next())
            {
                int id = rs.getInt("roof_id");
                int roof_slope = rs.getInt("roof_slope");
                int roof_type_id = rs.getInt("roof_type");
                RoofType roof_type = getRoofType(roof_type_id);

                roof = new Roof(id, roof_slope, roof_type);
            }

            return roof;

        } catch (SQLException ex)
        {
            throw new DataException(ex.getMessage());
        }

    }

    private Shed getShed(int shed_id) throws DataException
    {
        try
        {
            Shed shed = null;

            dbc.open();
            String query = "SELECT * FROM Fog.`shed`"
                    + "WHERE (`shed_id` = ?);";

            PreparedStatement statement = dbc.preparedStatement(query);
            statement.setInt(1, shed_id);
            ResultSet rs = statement.executeQuery();

            if (rs.next())
            {
                int id = rs.getInt("shed_id");
                int width = rs.getInt("width");
                int depth = rs.getInt("depth");

                shed = new Shed(id, width, depth);
            }

            return shed;

        } catch (SQLException ex)
        {
            throw new DataException(ex.getMessage());
        }
    }

    private Material getMaterial(int material_id) throws DataException
    {
        try
        {
            Material material = null;

            dbc.open();
            String query = "SELECT * FROM Fog.`materials`"
                    + "WHERE (`material_id` = ?);";

            PreparedStatement statement = dbc.preparedStatement(query);
            statement.setInt(1, material_id);
            ResultSet rs = statement.executeQuery();

            if (rs.next())
            {
                int id = rs.getInt("material_id");
                String name = rs.getString("material_name");
                String unit = rs.getString("unit");
                String material_class = rs.getString("material_class");
                double price = rs.getDouble("price");

                material = new Material(id, name, unit, material_class, price);
            }

            return material;

        } catch (SQLException ex)
        {
            throw new DataException(ex.getMessage());
        }
    }

    /**
     * when a customer places an order for a carpot with shed it is put in the
     * database with this method
     *
     * @param carport
     * @throws DataException
     */
    public void orderCarportWithShed(Carport carport) throws DataException
    {
        try
        {
            dbc.open();

            String query = "INSERT INTO Fog.carport"
                    + "(`depth`,`width`, `roof_id`, `shed_id`)"
                    + "VALUES (?,?,?,?);";

            addRoof(carport.getRoof());
            addShed(carport.getShed());

            int depth = carport.getDepth();
            int width = carport.getWidth();

            int roof_id = carport.getRoof().getId();
            int shed_id = carport.getShed().getId();

            PreparedStatement statement = dbc.preparedStatement(query, Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, depth);
            statement.setInt(2, width);
            statement.setInt(3, roof_id);
            statement.setInt(4, shed_id);
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next())
            {
                int id = rs.getInt(1);
                carport.setId(id);
            }

            dbc.close();

        } catch (SQLException ex)
        {
            throw new DataException(ex.getMessage());
        }
    }

    /**
     * puts a order for a carport without a shed in the database
     *
     * @param carport
     * @throws DataException
     */
    public void orderCarportWithoutShed(Carport carport) throws DataException
    {
        try
        {
            dbc.open();

            String query = "INSERT INTO Fog.carport"
                    + "(`depth`,`width`, `roof_id`)"
                    + "VALUES (?,?,?);";

            addRoof(carport.getRoof());

            int id = 0;
            int depth = carport.getDepth();
            int width = carport.getWidth();

            int roof_id = carport.getRoof().getId();

            PreparedStatement statement = dbc.preparedStatement(query, Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, depth);
            statement.setInt(2, width);
            statement.setInt(3, roof_id);
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next())
            {
                id = rs.getInt(1);
                carport.setId(id);
            }

            dbc.close();

        } catch (SQLException ex)
        {
            throw new DataException(ex.getMessage());
        }
    }

    private void addRoof(Roof roof) throws DataException
    {
        try
        {
            dbc.open();
            String query = "INSERT INTO Fog.roof"
                    + "(`roof_slope`,`roof_type`)"
                    + "VALUES (?,?);";

            int id = 0;
            int roof_slope = roof.getSlope();
            int roof_type = roof.getType().getId();

            PreparedStatement statement = dbc.preparedStatement(query, Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, roof_slope);
            statement.setInt(2, roof_type);
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next())
            {
                id = rs.getInt(1);
                roof.setId(id);
            }

        } catch (SQLException ex)
        {
            throw new DataException(ex.getMessage());
        }
    }

    /**
     * removes a roof from a carport in the database
     *
     * @param roof object
     * @throws DataException
     */
    public void removeRoof(Roof roof) throws DataException
    {
        try
        {
            dbc.open();
            String query = "DELETE FROM Fog.`roof`"
                    + "WHERE `roof_id` = ?;";

            PreparedStatement statement = dbc.preparedStatement(query);

            int roof_id = roof.getId();
            statement.setInt(1, roof_id);
            statement.executeUpdate();

            dbc.close();

        } catch (SQLException ex)
        {
            throw new DataException(ex.getMessage());
        }
    }

    /**
     * removes a shed form a carport in the database
     *
     * @param shed
     * @throws DataException
     */
    public void removeShed(Shed shed) throws DataException
    {
        try
        {
            dbc.open();
            String query = "DELETE FROM Fog.`shed`"
                    + "WHERE `shed_id` = ?;";

            PreparedStatement statement = dbc.preparedStatement(query);

            int shed_id = shed.getId();
            statement.setInt(1, shed_id);
            statement.executeUpdate();

            dbc.close();

        } catch (SQLException ex)
        {
            throw new DataException(ex.getMessage());
        }
    }

    /**
     *
     * @param carport
     * @throws DataException
     */
    public void removeShedId(Carport carport) throws DataException
    {
        try
        {
            dbc.open();

            String query = "UPDATE Fog.`carport`"
                    + "SET `shed_id` = null WHERE `carport_id` = ?;";

            PreparedStatement statement = dbc.preparedStatement(query);
            statement.setInt(1, carport.getId());
            statement.executeUpdate();

            dbc.close();

        } catch (SQLException ex)
        {
            throw new DataException(ex.getMessage());
        }
    }

    /**
     * deletes a carport from the database
     *
     * @param carport
     * @throws DataException
     */
    public void removeCarport(Carport carport) throws DataException
    {
        try
        {
            dbc.open();
            String query = "DELETE FROM Fog.`carport`"
                    + "WHERE `carport_id` = ?;";

            PreparedStatement statement = dbc.preparedStatement(query);

            int carport_id = carport.getId();
            statement.setInt(1, carport_id);
            statement.executeUpdate();

            dbc.close();

        } catch (SQLException ex)
        {
            throw new DataException(ex.getMessage());
        }
    }

    /**
     * updates the specifications of a carport in the database
     *
     * @param carport
     * @throws DataException
     */
    public void updateCarport(Carport carport) throws DataException
    {
        try
        {
            dbc.open();

            String queryNoShed = "UPDATE Fog.`carport`"
                    + "SET `depth` = ?, `width` = ? WHERE `carport_id` = ?;";

            String queryWithShed = "UPDATE Fog.`carport`"
                    + "SET `depth` = ?, `width` = ?, `shed_id` = ? WHERE `carport_id` = ?;";

            int carport_id = carport.getId();
            int depth = carport.getDepth();
            int width = carport.getWidth();
            if (carport.getShed() != null)
            {
                int shed_id = carport.getShed().getId();

                if (shed_id == 0)
                {
                    addShed(carport.getShed());
                    shed_id = carport.getShed().getId();
                }

                PreparedStatement statement = dbc.preparedStatement(queryWithShed);
                statement.setInt(1, depth);
                statement.setInt(2, width);
                statement.setInt(3, shed_id);
                statement.setInt(4, carport_id);
                statement.executeUpdate();
            } else
            {
                PreparedStatement statement = dbc.preparedStatement(queryNoShed);
                statement.setInt(1, depth);
                statement.setInt(2, width);
                statement.setInt(3, carport_id);
                statement.executeUpdate();
            }

            dbc.close();

        } catch (SQLException e)
        {
            throw new DataException(e.getMessage());
        }

    }

    /**
     * opdates a roof on a carport in the database
     *
     * @param roof
     * @throws DataException
     */
    public void updateRoof(Roof roof) throws DataException
    {
        try
        {
            dbc.open();

            String query = "UPDATE Fog.`roof`"
                    + "SET `roof_slope` = ?, `roof_type` = ? WHERE `roof_id` = ?;";

            int roof_id = roof.getId();
            int roof_slope = roof.getSlope();
            int roof_type = roof.getType().getId();

            PreparedStatement statement = dbc.preparedStatement(query);

            statement.setInt(1, roof_slope);
            statement.setInt(2, roof_type);
            statement.setInt(3, roof_id);
            statement.executeUpdate();

            dbc.close();

        } catch (SQLException e)
        {
            throw new DataException(e.getMessage());
        }
    }

    /**
     * updates the mesurments of a shed in the database
     *
     * @param shed
     * @throws DataException
     */
    public void updateShed(Shed shed) throws DataException
    {
        try
        {
            dbc.open();

            String query = "UPDATE Fog.`shed`"
                    + "SET `width` = ?, `depth` = ? WHERE `shed_id` = ?;";

            int shed_id = shed.getId();
            int width = shed.getWidth();
            int depth = shed.getDepth();

            PreparedStatement statement = dbc.preparedStatement(query);

            statement.setInt(1, width);
            statement.setInt(2, depth);
            statement.setInt(3, shed_id);
            statement.executeUpdate();

            dbc.close();

        } catch (SQLException e)
        {
            throw new DataException(e.getMessage());
        }
    }

    private RoofType getRoofType(int roof_type_id) throws DataException
    {
        try
        {
            RoofType rooftype = null;

            String query = "SELECT * FROM Fog.`roof_type`"
                    + "WHERE (`roof_type_id` = ?);";

            PreparedStatement statement = dbc.preparedStatement(query);
            statement.setInt(1, roof_type_id);
            ResultSet rs = statement.executeQuery();

            if (rs.next())
            {
                int id = rs.getInt("roof_type_id");
                String name = rs.getString("roof_type_name");
                String roof_class = rs.getString("roof_type_class");
                int m1_id = rs.getInt("roof_material1");
                int m2_id = rs.getInt("roof_material2");
                Material m1 = getMaterial(m1_id);
                Material m2 = getMaterial(m2_id);

                if (m2 == null)
                {
                    rooftype = new RoofType(id, name, roof_class, m1);
                } else
                {
                    rooftype = new RoofType(id, name, roof_class, m1, m2);
                }
            }

            return rooftype;

        } catch (SQLException ex)
        {
            throw new DataException(ex.getMessage());
        }
    }

    private void addShed(Shed shed) throws DataException
    {
        try
        {
            String query = "INSERT INTO Fog.shed"
                    + "(`width`,`depth`)"
                    + "VALUES (?,?);";

            int id = 0;
            int width = shed.getWidth();
            int depth = shed.getDepth();

            PreparedStatement statement = dbc.preparedStatement(query, Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, width);
            statement.setInt(2, depth);
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next())
            {
                id = rs.getInt(1);
                shed.setId(id);
            }

        } catch (SQLException ex)
        {
            throw new DataException(ex.getMessage());
        }
    }

    /**
     *
     * @param order
     * @throws DataException
     */
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

    /**
     * chages the status of a order to shipped in the database
     *
     * @param order_id
     * @return
     * @throws DataException
     */
    public String orderShipped(int order_id) throws DataException
    {
        try
        {
            dbc.open();

            String query = "UPDATE Fog.`order`"
                    + "SET shipped = CURRENT_TIMESTAMP WHERE (order_id = ?);";

            String query2 = "SELECT `shipped` FROM Fog.`order`"
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

    /**
     * findes orders in the database
     *
     * @param order_id
     * @return order from database connected to the id
     * @throws DataException
     */
    public Order getOrder(int order_id) throws DataException
    {
        try
        {
            Order order = null;

            dbc.open();

            String query = "SELECT * FROM Fog.`order`"
                    + "WHERE `order_id` = ?;";

            PreparedStatement statement = dbc.preparedStatement(query);
            statement.setInt(1, order_id);

            ResultSet rs = statement.executeQuery();
            if (rs.next())
            {
                int id = rs.getInt("order_id");
                int user_id = rs.getInt("user_id");
                User user = getUser(user_id);
                int carport_id = rs.getInt("carport_id");
                Carport carport = getCarport(carport_id);
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

    /**
     *
     * @return all the orders in the database
     * @throws DataException
     */
    public List<Order> getOrders() throws DataException
    {
        try
        {
            List<Order> orders = new ArrayList<>();

            dbc.open();

            String query = "SELECT * FROM Fog.`order`;";

            PreparedStatement statement = dbc.preparedStatement(query);
            ResultSet rs = statement.executeQuery();

            while (rs.next())
            {
                int id = rs.getInt("order_id");
                int user_id = rs.getInt("user_id");
                User user = getUser(user_id);
                int carport_id = rs.getInt("carport_id");
                Carport carport = getCarport(carport_id);
                String order_date = rs.getString("order_date");
                String order_status = rs.getString("order_status");
                String shipped = rs.getString("shipped");
                String paid = rs.getString("paid");
                double sales_price = rs.getDouble("sales_price");

                Status s = Status.valueOf(order_status.toUpperCase());
                Paid p = Paid.valueOf(paid.toUpperCase());

                Order o = new Order(id, user, carport, order_date, s, shipped, p, sales_price);
                orders.add(o);
            }

            dbc.close();
            return orders;

        } catch (SQLException e)
        {
            throw new DataException(e.getMessage());
        }

    }

    /**
     *
     * @param email
     * @return all the orders in the database connected to a client
     * @throws DataException
     */
    public List<Order> getOrdersByEmail(String email) throws DataException
    {
        try
        {
            List<Order> orders = new ArrayList<>();

            dbc.open();
            String query = "SELECT * FROM Fog.`order` JOIN `user` ON (`order`.user_id = `user`.user_id) WHERE email = ?;";

            PreparedStatement statement = dbc.preparedStatement(query);
            statement.setString(1, email);

            ResultSet rs = statement.executeQuery();
            while (rs.next())
            {
                int id = rs.getInt("order_id");
                int user_id = rs.getInt("user_id");
                User user = getUser(user_id);
                int carport_id = rs.getInt("carport_id");
                Carport carport = getCarport(carport_id);
                String order_date = rs.getString("order_date");
                String order_status = rs.getString("order_status");
                String shipped = rs.getString("shipped");
                String paid = rs.getString("paid");
                double sales_price = rs.getDouble("sales_price");

                Status s = Status.valueOf(order_status.toUpperCase());
                Paid p = Paid.valueOf(paid.toUpperCase());

                Order o = new Order(id, user, carport, order_date, s, shipped, p, sales_price);
                orders.add(o);
            }

            dbc.close();
            return orders;

        } catch (SQLException e)
        {
            throw new DataException(e.getMessage());
        }

    }

    private User getUser(int user_id) throws DataException
    {
        try
        {
            User user = null;
            String query = "SELECT * FROM Fog.`user`"
                    + "WHERE (`user_id` = ?);";

            PreparedStatement statement = dbc.preparedStatement(query);
            statement.setInt(1, user_id);
            ResultSet rs = statement.executeQuery();

            if (rs.next())
            {
                int id = rs.getInt("user_id");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String name = rs.getString("user_name");
                String address = rs.getString("address");
                String zipcode = rs.getString("zipcode");
                String phonenumber = rs.getString("phone_number");
                String role = rs.getString("role");

                Role r = Role.valueOf(role.toUpperCase());

                user = new User(id, email, password, name, address, zipcode, phonenumber, r);
            }

            return user;

        } catch (SQLException e)
        {
            throw new DataException(e.getMessage());
        }

    }

    /**
     * changes the price of a order in the database
     *
     * @param order_id
     * @param salesprice
     * @throws DataException
     */
    public void updateSalesPrice(int order_id, double salesprice) throws DataException
    {
        try
        {
            dbc.open();

            String query = "UPDATE Fog.`order`"
                    + "SET `sales_price` = ?"
                    + "WHERE `order_id` = ?;";

            PreparedStatement statement = dbc.preparedStatement(query);
            statement.setDouble(1, salesprice);
            statement.setInt(2, order_id);

            statement.executeUpdate();

            dbc.close();

        } catch (SQLException e)
        {
            throw new DataException(e.getMessage());
        }

    }

    /**
     *
     * @param order_id
     * @param status
     * @param paid
     * @throws DataException
     */
    public void updateStatusAndPaid(int order_id, Status status, Paid paid) throws DataException
    {
        try
        {
            dbc.open();

            String query = "UPDATE Fog.`order`"
                    + "SET `order_status` = ?, `paid` = ?"
                    + "WHERE `order_id` = ?;";

            PreparedStatement statement = dbc.preparedStatement(query);

            String order_status = status.toString();
            String order_paid = paid.toString();

            statement.setString(1, order_status);
            statement.setString(2, order_paid);
            statement.setInt(3, order_id);
            statement.executeUpdate();

            dbc.close();

        } catch (SQLException e)
        {
            throw new DataException(e.getMessage());
        }
    }

    /**
     * deletes a order in the database
     *
     * @param order
     * @throws DataException
     */
    public void removeOrder(Order order) throws DataException
    {
        try
        {
            dbc.open();

            String query = "DELETE FROM Fog.`order`"
                    + "WHERE order_id = ?;";

            int order_id = order.getOrder_id();

            PreparedStatement statement = dbc.preparedStatement(query);
            statement.setInt(1, order_id);
            statement.executeUpdate();

            dbc.close();

        } catch (SQLException e)
        {
            throw new DataException(e.getMessage());
        }
    }
}
