/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer;

import FunctionLayer.Carport;
import FunctionLayer.Material;
import java.util.List;

/**
 *
 * @author sofieamalielandt
 */
public class DataFacade
{

    private static DataFacade instance = null;
    private DBConnector dbc = new DBConnector();
    private DataSourceMysql dataSource = new DataSourceMysql();
    private MaterialMapper mm = new MaterialMapper(dbc);
    private CarportMapper cm = new CarportMapper(dbc);
    
    private DataFacade() throws DataException
    {
        dbc.setDataSource(dataSource.getDataSource());
    }
    
    public static DataFacade getInstance() throws DataException
    {
        if (instance == null)
        {
            instance = new DataFacade();
        }
        return instance;
    }
    
    public Material getMaterial(String material_name) throws DataException
    {
        return mm.getMaterial(material_name);
    }
    
    public Material getMaterial(int material_id) throws DataException
    {
        return mm.getMaterial(material_id);
    }
    
    public List<Material> getMaterials() throws DataException
    {
        return mm.getMaterials();
    }
    
    public Carport getCarport(int carport_id) throws DataException
    {
        return cm.getCarport(carport_id);
    }
    
    public String orderCarport(Carport carport) throws DataException
    {
        String res = "";
        cm.orderCarport(carport);
        
        res = "Tak for din ordre, nummer: " + carport.getId();
        return res;
    }
    
}
