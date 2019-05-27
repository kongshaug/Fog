/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer;

import FunctionLayer.Enum.Role;
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
public class UserMapper
{
    private DBConnector dbc;

    /**
     * Creates and initializes a newly created DBConnector for further use
     * in methods.
     * 
     * @param dbc an object of the class DBConnector
     * @throws DataException if initializing not possible
     */
    public UserMapper(DBConnector dbc) throws DataException
    {
        this.dbc = dbc;
    }

    /**
     * Executes the query in the database, 
     * to varify the identity of a user when logging in
     *
     * @param email String - is used to detect the specific User in the database
     * @param password String
     * @return the user with the associated email and password
     * @throws DataException executing of query is not possible
     */
    public User login(String email, String password) throws DataException
    {
        try
        {
            dbc.open();
            String query
                    = "SELECT * "
                    + "FROM user "
                    + "WHERE email = ? AND password = ?;";

            PreparedStatement statement = dbc.preparedStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();

            if (rs.next())
            {
                int id = rs.getInt("user_id");
                String name = rs.getString("user_name");
                String address = rs.getString("address");
                String zipcode = rs.getString("zipcode");
                String phonenumber = rs.getString("phone_number");
                String role = rs.getString("role");

                Role r = Role.valueOf(role.toUpperCase());

                User user = new User(id, email, password, name, address, zipcode, phonenumber, r);

                dbc.close();
                return user;
            } else
            {
                dbc.close();
                return null;
            }

        } catch (SQLException ex)
        {
            throw new DataException(ex.getMessage());
        }
    }

    /**
     * Executes the query in the database to collect an object from the class
     * User with a specific user_id
     *
     * @param user_id is used to detect the specific User in the database
     * @return an object from the class User
     * @throws DataException if executing of query is not possible
     */
    public User getUser(int user_id) throws DataException
    {
        try
        {
            User user = null;
            dbc.open();

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

            dbc.close();

            return user;

        } catch (SQLException e)
        {
            throw new DataException(e.getMessage());
        }
    }

    /**
     * Executes the query in the database to collect a list of objects from the
     * class User.
     * 
     * @return an arraylist of object from the class User
     * @throws DataException if executing of query is not possible
     */
    public List<User> getUsers() throws DataException
    {
        try
        {
            dbc.open();

            List<User> users = new ArrayList<>();

            String query
                    = "SELECT * FROM Fog.`user`;";

            PreparedStatement statement = dbc.preparedStatement(query);
            ResultSet rs = statement.executeQuery();

            while (rs.next())
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

                User u = new User(id, email, password, name, address, zipcode, phonenumber, r);
                users.add(u);
            }

            dbc.close();
            return users;

        } catch (SQLException e)
        {
            throw new DataException(e.getMessage());
        }
    }

    /**
     * Executes the query in the database to collect a list of objects from the
     * class User, where role is either ADMIN or EMPLOYEE
     * 
     * @return an arraylist of object from the class User, which role is either ADMIN or EMPLOYEE
     * @throws DataException if executing of query is not possible
     */
    public List<User> getEmployeesAndAdmins() throws DataException
    {
        try
        {
            List<User> users = new ArrayList<>();

            dbc.open();

            String query = "SELECT * FROM Fog.`user`"
                    + "WHERE `role` = ? OR `role` = ?;";

            PreparedStatement statement = dbc.preparedStatement(query);
            String employee_role = Role.EMPLOYEE.toString();
            String admin_role = Role.ADMIN.toString();

            statement.setString(1, employee_role);
            statement.setString(2, admin_role);

            ResultSet rs = statement.executeQuery();

            while (rs.next())
            {
                int id = rs.getInt("user_id");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String name = rs.getString("user_name");
                String address = rs.getString("address");
                String zipcode = rs.getString("zipcode");
                String phonenumber = rs.getString("phone_number");
                String role = rs.getString("role");

                Role r = Role.valueOf(role);

                User u = new User(id, email, password, name, address, zipcode, phonenumber, r);
                users.add(u);
            }

            dbc.close();

            return users;

        } catch (SQLException e)
        {
            throw new DataException(e.getMessage());
        }
    }

    /**
     * Executes the query in the database to collect an object from the class
     * User with a specific email.
     *
     * @param email a String - is used to detect the specific User in the database
     * @return an object from the class User
     * @throws DataException if executing of query is not possible
     */
    public User getEmployeeByEmail(String email) throws DataException
    {
        try
        {
            User user = null;

            dbc.open();

            String query = "SELECT * FROM Fog.`user`"
                    + "WHERE `email` = ?;";

            PreparedStatement statement = dbc.preparedStatement(query);
            statement.setString(1, email);

            ResultSet rs = statement.executeQuery();
            if (rs.next())
            {
                int user_id = rs.getInt("user_id");
                String user_email = rs.getString("email");
                String password = rs.getString("password");
                String name = rs.getString("user_name");
                String address = rs.getString("address");
                String zipcode = rs.getString("zipcode");
                String phonenumber = rs.getString("phone_number");
                String role = rs.getString("role");

                Role r = Role.valueOf(role.toUpperCase());

                user = new User(user_id, user_email, password, name, address, zipcode, phonenumber, r);
            }

            dbc.close();

            return user;

        } catch (SQLException e)
        {
            throw new DataException(e.getMessage());
        }
    }

    /**
     * Executes the query in the database to insert an object from the class
     * User.
     *
     * @param newUser the User to insert in database
     * @throws DataException if executing update is not possible
     */
    public void addUser(User newUser) throws DataException
    {
        try
        {
            dbc.open();
            String query = "INSERT INTO Fog.`user`"
                    + "(`email`, `password`, `user_name`, `address`, `zipcode`, `phone_number`, `role`)"
                    + "VALUES (?,?,?,?,?,?,?);";

            int user_id = 0;
            String email = newUser.getEmail();
            String password = newUser.getPassword();
            String user_name = newUser.getName();
            String address = newUser.getAddress();
            String zipcode = newUser.getZipcode();
            String phonenumber = newUser.getPhone();
            String role = newUser.getRole().toString();

            PreparedStatement statement = dbc.preparedStatement(query, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, email);
            statement.setString(2, password);
            statement.setString(3, user_name);
            statement.setString(4, address);
            statement.setString(5, zipcode);
            statement.setString(6, phonenumber);
            statement.setString(7, role);
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next())
            {
                user_id = rs.getInt(1);
                newUser.setId(user_id);
            }

            dbc.close();

        } catch (SQLException e)
        {
            throw new DataException(e.getMessage());
        }
    }

    /**
     * Executes the query in the database to update the attributes 
     * email, password, user_name, address, zipcode and phone_number on a specific User.
     *
     * @param user is used to detect the specific User in the database
     * @throws DataException if executing update is not possible
     */
    public void updateUser(User user) throws DataException
    {
        try
        {
            dbc.open();

            String query = "UPDATE `Fog`.`user`"
                    + "SET `email` = ?,`password` = ?,`user_name` = ?,`address` = ?,`zipcode` = ?,`phone_number` = ? WHERE `user_id` = ?;";

            String email = user.getEmail();
            String password = user.getPassword();
            String user_name = user.getName();
            String address = user.getAddress();
            String zipcode = user.getZipcode();
            String phonenumber = user.getPhone();
            int user_id = user.getId();

            PreparedStatement statement = dbc.preparedStatement(query);

            statement.setString(1, email);
            statement.setString(2, password);
            statement.setString(3, user_name);
            statement.setString(4, address);
            statement.setString(5, zipcode);
            statement.setString(6, phonenumber);
            statement.setInt(7, user_id);
            statement.executeUpdate();

            dbc.close();
        } catch (SQLException e)
        {
            throw new DataException(e.getMessage());
        }
    }

    /**
     * Executes the query in the database to update the attribute password on a specific User.
     *
     * @param user_id is used to detect the specific User in the database
     * @param password the new value to insert on password
     * @throws DataException if executing update is not possible
     */
    public void updatePassword(int user_id, String password) throws DataException
    {
        try
        {
            dbc.open();

            String query = "UPDATE Fog.`user`"
                    + "SET `password` = ? WHERE `user_id` = ?;";

            PreparedStatement statement = dbc.preparedStatement(query);

            statement.setString(1, password);
            statement.setInt(2, user_id);
            statement.executeUpdate();

            dbc.close();

        } catch (SQLException e)
        {
            throw new DataException(e.getMessage());
        }
    }

    /**
     * Executes the query in the database to remove an object from the class
     * User from the database.
     *
     * @param user an object - to remove from the database
     * @throws DataException if executing update is not possible
     */
    public void removeUser(User user) throws DataException
    {
        try
        {
            dbc.open();

            String query = "DELETE FROM Fog.`user`"
                    + "WHERE user_id = ?;";

            int user_id = user.getId();

            PreparedStatement statement = dbc.preparedStatement(query);
            statement.setInt(1, user_id);
            statement.executeUpdate();

            dbc.close();

        } catch (SQLException e)
        {
            throw new DataException(e.getMessage());
        }
    }
}
