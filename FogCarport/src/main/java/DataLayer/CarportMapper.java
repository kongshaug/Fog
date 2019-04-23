/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer;

import FunctionLayer.Carport;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author sofieamalielandt
 */
public class CarportMapper
{

    private DBConnector dbc;

    public CarportMapper(DBConnector dbc) throws DataException
    {
        this.dbc = dbc;
    }

    public Carport getCarport(int carport_id) throws DataException
    {
        try
        {
            Carport carport = null;

            dbc.open();
            String query = "SELECT * FROM Fog.`carport`"
                    + "WHERE (`carport_id` = ?);";

            int id = 0;
            int depth = 0;
            int width = 0;
            int shed = 0;
            int roof = 0;

            PreparedStatement statement = dbc.preparedStatement(query);
            statement.setInt(1, carport_id);
            ResultSet rs = statement.executeQuery();

            if (rs.next())
            {
                id = rs.getInt("carport_id");
                depth = rs.getInt("depth");
                width = rs.getInt("width");
                shed = rs.getInt("shed");
                roof = rs.getInt("roof");

                if (shed == 0)
                {
                    carport = new Carport(id, width, depth, null);
                } else
                {
                    carport = new Carport(id, width, depth, null, null);
                }
            }

            dbc.close();
            return carport;

        } catch (SQLException ex)
        {
            throw new DataException(ex.getMessage());
        }
    }

    public void orderCarport(Carport carport) throws DataException
    {
        try
        {
            dbc.open();
            String query = "INSERT INTO Fog.carport"
                    + "(`depth`,`width`,`roof`,`shed`)"
                    + "VALUES (?,?,?,?);";

            int id = 0;
            int depth = carport.getDepth();
            int width = carport.getWidth();
            int shed = 0;
            int roof = 0;

            PreparedStatement statement = dbc.preparedStatement(query, Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, depth);
            statement.setInt(2, width);
            statement.setInt(3, shed);
            statement.setInt(4, roof);
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
}
