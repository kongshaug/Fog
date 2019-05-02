/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer;

import FunctionLayer.HelpingClasses.Material;
import FunctionLayer.HelpingClasses.RoofType;
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

    public Material getMaterial(String material_name) throws DataException
    {
        try
        {
            Material material = null;

            dbc.open();
            String query = "SELECT * FROM Fog.`materials`"
                    + "WHERE (`material_name` = ?);";

            PreparedStatement statement = dbc.preparedStatement(query);
            statement.setString(1, material_name);
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

            dbc.close();
            return material;

        } catch (SQLException ex)
        {
            throw new DataException(ex.getMessage());
        }
    }

    public Material getMaterial(int material_id) throws DataException
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

            dbc.close();
            return material;

        } catch (SQLException ex)
        {
            throw new DataException(ex.getMessage());
        }
    }

    private Material retrieveMaterial(int material_id) throws DataException
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

    public List<Material> getMaterials() throws DataException
    {
        try
        {
            List<Material> materials = new ArrayList();

            dbc.open();
            String query = "SELECT * FROM Fog.`materials`;";

            PreparedStatement statment = dbc.preparedStatement(query);
            ResultSet rs = statment.executeQuery();

            while (rs.next())
            {
                int id = rs.getInt("material_id");
                String name = rs.getString("material_name");
                String unit = rs.getString("unit");
                String material_class = rs.getString("material_class");
                double price = rs.getDouble("price");

                materials.add(new Material(id, name, unit, material_class, price));
            }

            dbc.close();
            return materials;

        } catch (SQLException ex)
        {
            throw new DataException(ex.getMessage());
        }
    }

    public List<RoofType> getRoofs() throws DataException
    {
        try
        {
            List<RoofType> rooftypes = new ArrayList();

            dbc.open();
            String query = "SELECT * FROM Fog.`roof_type`;";

            PreparedStatement statment = dbc.preparedStatement(query);
            ResultSet rs = statment.executeQuery();

            while (rs.next())
            {
                int id = rs.getInt("roof_type_id");
                String name = rs.getString("roof_type_name");
                String roof_class = rs.getString("roof_type_class");
                int m1_id = rs.getInt("roof_material1");
                int m2_id = rs.getInt("roof_material2");
                Material m1 = retrieveMaterial(m1_id);
                Material m2 = retrieveMaterial(m2_id);

                if (m2 == null)
                {
                    rooftypes.add(new RoofType(id, name, roof_class, m1));
                } else
                {
                    rooftypes.add(new RoofType(id, name, roof_class, m1, m2));
                }

            }

            dbc.close();
            return rooftypes;

        } catch (SQLException ex)
        {
            throw new DataException(ex.getMessage());
        }
    }

    public RoofType getRoof(int theId) throws DataException
    {
        try
        {
            RoofType rooftypes = null;

            dbc.open();
            String query = "SELECT * FROM Fog.`roof_type` where roof_type_id =" + theId + ";";

            PreparedStatement statment = dbc.preparedStatement(query);
            ResultSet rs = statment.executeQuery();

            while (rs.next())
            {
                int id = rs.getInt("roof_type_id");
                String name = rs.getString("roof_type_name");
                String roof_class = rs.getString("roof_type_class");
                int m1_id = rs.getInt("roof_material1");
                int m2_id = rs.getInt("roof_material2");
                Material m1 = retrieveMaterial(m1_id);
                Material m2 = retrieveMaterial(m2_id);

                if (m2 == null)
                {
                    rooftypes = new RoofType(id, name, roof_class, m1);
                } else
                {
                    rooftypes = new RoofType(id, name, roof_class, m1, m2);
                }

            }

            dbc.close();
            return rooftypes;

        } catch (SQLException ex)
        {
            throw new DataException(ex.getMessage());
        }
    }

}
