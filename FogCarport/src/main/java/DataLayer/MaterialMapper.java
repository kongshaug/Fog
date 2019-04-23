/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer;

import FunctionLayer.Material;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sofieamalielandt
 */
public class MaterialMapper
{

    private DBConnector dbc;

    public MaterialMapper(DBConnector dbc) throws DataException
    {
        this.dbc = dbc;
    }

    public Material getMaterial(int material_id) throws DataException
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
            
            dbc.close();
            return material;
            
        } catch (SQLException ex)
        {
            throw new DataException(ex.getMessage());
        }
    }
    
    public List<Material> getMaterials() throws DataException
    {
        try
        {
            List<Material> materials = new ArrayList();
            
            dbc.open();
            String query = "SELECT * FROM Fog.`materials`;";
            
            int id = 0;
            String name = "";
            String unit = "";

            
            PreparedStatement statment = dbc.preparedStatement(query);
            ResultSet rs = statment.executeQuery();
            
            while (rs.next())
            {
                id = rs.getInt("order_id");
                name = rs.getString("order_date");
                unit = rs.getString("shipped");
                
                materials.add(new Material(id, name, unit));
            }
            
            dbc.close();
            return materials;
            
        } catch (SQLException ex)
        {
            throw new DataException(ex.getMessage());
        }
    }
}
