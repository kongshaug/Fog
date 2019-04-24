/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer;

import FunctionLayer.Carport;
import FunctionLayer.Material;
import FunctionLayer.Roof;
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
                    + "INNER JOIN Fog.carport_has_roof"
                    + "WHERE (`carport_id` = ?);";
            
            int id = 0;
            int depth = 0;
            int width = 0;
            int roof_id = 0;
            Roof roof = null;
            
            PreparedStatement statement = dbc.preparedStatement(query);
            statement.setInt(1, carport_id);
            ResultSet rs = statement.executeQuery();
            
            if (rs.next())
            {
                id = rs.getInt("carport_id");
                depth = rs.getInt("depth");
                width = rs.getInt("width");
                roof_id = rs.getInt("roof_id");
                roof = getRoof(roof_id);

//                if (shed == 0)
//                {
                carport = new Carport(id, width, depth, roof);
//                } else
//                {
//                    carport = new Carport(id, width, depth, null, null);
//                }
            }
            
            dbc.close();
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
            String query = "SELECT * FROM Fog.roof"
                    + "WHERE (roof_id = ?);";
            
            int id = 0;
            int roof_slope = 0;
            int roof_type_id = 0;
            Material roof_type = null;
            
            PreparedStatement statement = dbc.preparedStatement(query);
            statement.setInt(1, roof_id);
            ResultSet rs = statement.executeQuery();
            
            if (rs.next())
            {
                id = rs.getInt("roof_id");
                roof_slope = rs.getInt("roof_slope");
                roof_type_id = rs.getInt("roof_type");
                roof_type = getMaterial(roof_type_id);
                
                roof = new Roof(id, roof_slope, roof_type);
                
            }
            
            return roof;
            
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
            
            int id = 0;
            String name = "";
            String unit = "";
            
            PreparedStatement statement = dbc.preparedStatement(query);
            statement.setInt(1, material_id);
            ResultSet rs = statement.executeQuery();
            
            if (rs.next())
            {
                id = rs.getInt("material_id");
                name = rs.getString("material_name");
                unit = rs.getString("unit");
                
                material = new Material(id, name, unit);
            }

            //dbc.close();
            return material;
            
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
                    + "(`depth`,`width`)"
                    + "VALUES (?,?);";
            
            int id = 0;
            int depth = carport.getDepth();
            int width = carport.getWidth();
            
            PreparedStatement statement = dbc.preparedStatement(query, Statement.RETURN_GENERATED_KEYS);
            
            statement.setInt(1, depth);
            statement.setInt(2, width);
            statement.executeUpdate();
            
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next())
            {
                id = rs.getInt(1);
                carport.setId(id);
            }
            addRoof(carport.getRoof());
            
            
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
}
