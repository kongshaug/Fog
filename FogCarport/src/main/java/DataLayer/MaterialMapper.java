/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer;

import FunctionLayer.Material;
import FunctionLayer.RoofType;
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

            int id = 0;
            String name = "";
            String unit = "";

            PreparedStatement statement = dbc.preparedStatement(query);
            statement.setString(1, material_name);
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

    private Material retrieveMaterial(int material_id) throws DataException
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
                id = rs.getInt("material_id");
                name = rs.getString("material_name");
                unit = rs.getString("unit");

                materials.add(new Material(id, name, unit));
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

            int id = 0;
            String name = "";
            int m1_id = 0;
            int m2_id = 0;
            Material m1 = null;
            Material m2 = null;

            PreparedStatement statment = dbc.preparedStatement(query);
            ResultSet rs = statment.executeQuery();

            while (rs.next())
            {
                id = rs.getInt("roof_type_id");
                name = rs.getString("roof_type_name");
                m1_id = rs.getInt("roof_material1");
                m2_id = rs.getInt("roof_material2");
                m1 = retrieveMaterial(m1_id);
                m2 = retrieveMaterial(m2_id);

                if (m2 == null)
                {
                    rooftypes.add(new RoofType(id, name, m1));
                } else
                {
                    rooftypes.add(new RoofType(id, name, m1, m2));
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
