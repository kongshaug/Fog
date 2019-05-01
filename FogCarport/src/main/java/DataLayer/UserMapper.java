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

    public UserMapper(DBConnector dbc)
    {
        this.dbc = dbc;

    }

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

    public User getUser(int user_id) throws DataException
    {
        try
        {
            dbc.open();

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

            dbc.close();

            return user;

        } catch (SQLException e)
        {
            throw new DataException(e.getMessage());
        }

    }

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

}