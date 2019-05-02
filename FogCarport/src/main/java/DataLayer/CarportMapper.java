/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer;

import FunctionLayer.HelpingClasses.Carport;
import FunctionLayer.HelpingClasses.Material;
import FunctionLayer.HelpingClasses.Roof;
import FunctionLayer.HelpingClasses.RoofType;
import FunctionLayer.HelpingClasses.Shed;
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

    private RoofType getRoofType(int roof_type_id) throws DataException
    {
        try
        {
            RoofType rooftype = null;

            dbc.open();
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

            //dbc.close();
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
            dbc.open();
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
}
