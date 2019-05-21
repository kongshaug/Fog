/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;

/**
 *
 * @author aamandajuhl
 */
public class DBConnector
{
    private DataSource dataSource;
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    /**
     * Used for ininitializing parameter dataSource as dataSource.
     *
     * @param dataSource withholds information for connection to the database
     */
    public void setDataSource(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    /**
     * Used to open connection to database.
     *
     * @throws java.sql.SQLException if connection not possible
     */
    public void open() throws SQLException
    {
        if (connection == null || connection.isClosed())
        {
            connection = dataSource.getConnection();
        }
    }

    /**
     * Used to close connection to database.
     *
     * @throws java.sql.SQLException if ending connection not possible
     */
    public void close() throws SQLException
    {
        if (resultSet != null)
        {
            resultSet.close();
        }

        if (statement != null)
        {
            resultSet.close();
        }

        if (connection != null || !connection.isClosed())
        {
            connection.close();
            connection = null;
        }
    }

    /**
     * Used to convert a string to a prepared statement for the database.
     *
     * @param sql withholds specific data manipulation language, to withdraw
     * specified data from database
     * @return the string converted to a prepared statement
     * @throws java.sql.SQLException if conversion not possible
     */
    public PreparedStatement preparedStatement(String sql) throws SQLException
    {
        return connection.prepareStatement(sql);
    }

    /**
     * Used to convert a string to a prepared statement for the database as well
     * as making data avaible for retrieval.
     *
     * @param sql withholds specific data manipulation language, to withdraw
     * specified data from database
     * @param indicator indicating what data should be made available for
     * retrieval
     * @return the string converted to a prepared statement
     * @throws java.sql.SQLException if conversion not possible
     */
    public PreparedStatement preparedStatement(String sql, int indicator) throws SQLException
    {
        return connection.prepareStatement(sql, indicator);
    }
}
