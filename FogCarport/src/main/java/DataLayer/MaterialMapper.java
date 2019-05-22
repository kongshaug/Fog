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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author sofieamalielandt
 */
public class MaterialMapper
{
    private DBConnector dbc;

    /**
     *
     * @param dbc
     * @throws DataException
     */
    public MaterialMapper(DBConnector dbc) throws DataException
    {
        this.dbc = dbc;
    }

    /**
     *
     * @param material_name
     * @return
     * @throws DataException
     */
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

    /**
     *
     * @param material_id
     * @return
     * @throws DataException
     */
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

    /**
     *
     * @return
     * @throws DataException
     */
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

    /**
     *
     * @param newMaterial
     * @throws DataException
     */
    public void addMaterial(Material newMaterial) throws DataException
    {
        try
        {
            dbc.open();

            String query = "INSERT INTO Fog.`materials`"
                    + "(`material_name`, `unit`, `material_class`, `price`) VALUES (?,?,?,?);";

            int material_id;
            String material_name = newMaterial.getName();
            String unit = newMaterial.getUnit();
            String material_class = newMaterial.getMaterial_class();
            Double price = newMaterial.getPrice();

            PreparedStatement statement = dbc.preparedStatement(query, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, material_name);
            statement.setString(2, unit);
            statement.setString(3, material_class);
            statement.setDouble(4, price);
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next())
            {
                material_id = rs.getInt(1);
                newMaterial.setId(material_id);
            }

            dbc.close();
        } catch (SQLException e)
        {
            throw new DataException(e.getMessage());
        }

    }

    /**
     *
     * @param material
     * @throws DataException
     */
    public void deleteMaterial(Material material) throws DataException
    {
        try
        {
            dbc.open();

            String query = "DELETE FROM Fog.`materials`"
                    + "WHERE `material_id` = ?;";

            int material_id = material.getId();

            PreparedStatement statement = dbc.preparedStatement(query);
            statement.setInt(1, material_id);
            statement.executeUpdate();

            dbc.close();

        } catch (SQLException e)
        {
            throw new DataException(e.getMessage());
        }
    }

    /**
     *
     * @param material
     * @throws DataException
     */
    public void updateMaterial(Material material) throws DataException
    {
        try
        {
            dbc.open();

            String query = "UPDATE Fog.`materials`"
                    + "SET `material_name` = ?, `unit` = ?, `material_class` = ?, `price` = ? "
                    + "WHERE `material_id` = ?;";

            String material_name = material.getName();
            String unit = material.getUnit();
            String material_class = material.getMaterial_class();
            Double price = material.getPrice();
            int material_id = material.getId();

            PreparedStatement statement = dbc.preparedStatement(query);

            statement.setString(1, material_name);
            statement.setString(2, unit);
            statement.setString(3, material_class);
            statement.setDouble(4, price);
            statement.setInt(5, material_id);
            statement.executeUpdate();

            dbc.close();

        } catch (SQLException e)
        {
            throw new DataException(e.getMessage());
        }
    }

    /**
     *
     * @param rooftype
     * @throws DataException
     */
    public void updateRoofType(RoofType rooftype) throws DataException
    {
        try
        {
            dbc.open();

            String query = "UPDATE Fog.`roof_type`"
                    + "SET `roof_type_name` = ?, `roof_material1` = ?, `roof_material2` = ?"
                    + " WHERE `roof_type_id` = ?;";

            String roof_type_name = rooftype.getName();
            int material1 = rooftype.getM1().getId();
            int material2 = rooftype.getM2().getId();
            int rooftype_id = rooftype.getId();

            PreparedStatement statement = dbc.preparedStatement(query);

            statement.setString(1, roof_type_name);
            statement.setInt(2, material1);
            statement.setInt(3, material2);
            statement.setInt(4, rooftype_id);
            statement.executeUpdate();

            dbc.close();

        } catch (SQLException e)
        {
            throw new DataException(e.getMessage());
        }
    }

    /**
     *
     * @param rooftype
     * @throws DataException
     */
    public void updateRoofTypeWith1Material(RoofType rooftype) throws DataException
    {
        try
        {
            dbc.open();

            String query = "UPDATE Fog.`roof_type`"
                    + "SET `roof_type_name` = ?, `roof_material1` = ?"
                    + " WHERE `roof_type_id` = ?;";

            String roof_type_name = rooftype.getName();
            int material1 = rooftype.getM1().getId();
            int rooftype_id = rooftype.getId();

            PreparedStatement statement = dbc.preparedStatement(query);

            statement.setString(1, roof_type_name);
            statement.setInt(2, material1);
            statement.setInt(3, rooftype_id);
            statement.executeUpdate();

            dbc.close();

        } catch (SQLException e)
        {
            throw new DataException(e.getMessage());
        }
    }

    /**
     *
     * @param rooftype
     * @throws DataException
     */
    public void deleteRooftype(RoofType rooftype) throws DataException
    {
        try
        {
            dbc.open();

            String query = "DELETE FROM Fog.`roof_type`"
                    + "WHERE `roof_type_id` = ?;";

            int rooftypeId = rooftype.getId();

            PreparedStatement statement = dbc.preparedStatement(query);
            statement.setInt(1, rooftypeId);
            statement.executeUpdate();

            dbc.close();

        } catch (SQLException e)
        {
            throw new DataException(e.getMessage());
        }
    }

    /**
     *
     * @return
     * @throws DataException
     */
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

    /**
     *
     * @param theId
     * @return
     * @throws DataException
     */
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

    /**
     *
     * @param rooftype
     * @throws DataException
     */
    public void addRoofType(RoofType rooftype) throws DataException
    {
        try
        {
            dbc.open();

            String query = "INSERT INTO Fog.`roof_type`"
                    + "(`roof_type_name`, `roof_material1`, `roof_material2`, `roof_type_class`) VALUES (?,?,?,?);";

            String query2 = "INSERT INTO Fog.`roof_type`"
                    + "(`roof_type_name`, `roof_material1`, `roof_type_class`) VALUES (?,?,?);";

            int roof_type_id;
            String roof_type_name = rooftype.getName();
            int roofmaterial1 = rooftype.getM1().getId();
            String rooftype_class = rooftype.getRoof_class();

            PreparedStatement statement;

            if (rooftype.getM2() != null)
            {
                int roofmaterial2 = rooftype.getM2().getId();
                statement = dbc.preparedStatement(query, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, roof_type_name);
                statement.setInt(2, roofmaterial1);
                statement.setInt(3, roofmaterial2);
                statement.setString(4, rooftype_class);
                statement.executeUpdate();
            } else
            {
                statement = dbc.preparedStatement(query2, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, roof_type_name);
                statement.setInt(2, roofmaterial1);
                statement.setString(3, rooftype_class);
                statement.executeUpdate();
            }

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next())
            {
                roof_type_id = rs.getInt(1);
                rooftype.setId(roof_type_id);
            }

            dbc.close();
        } catch (SQLException e)
        {
            throw new DataException(e.getMessage());
        }
    }
}
