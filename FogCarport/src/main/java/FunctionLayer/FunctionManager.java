/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

import DataLayer.DataException;
import DataLayer.DataFacade;

/**
 *
 * @author sofieamalielandt
 */
public class FunctionManager
{
    private static FunctionManager instance = null;
    private DataFacade db;
    private Calculate c;

    public FunctionManager() throws DataException
    {
        db = DataFacade.getInstance();
        c = new Calculate();
    }

    public static FunctionManager getInstance() throws DataException
    {
        if (instance == null)
        {
            instance = new FunctionManager();
        }
        return instance;
    }
    
    public void calCarport(Carport carport) throws DataException
    {
        Material pole = db.getMaterial(3);
        Material rem = db.getMaterial(4);
        Material bolts = db.getMaterial(27);
        Material discs = db.getMaterial(28);
        
        c.caluclatepoles(carport, pole, bolts, discs);
        c.caluclatRem(carport, rem);
        
    }
    
    
  
}
