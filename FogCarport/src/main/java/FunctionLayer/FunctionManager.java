/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

import DataLayer.DataException;
import DataLayer.DataFacade;
import java.util.List;

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

    public void calFlatroof(Carport carport) throws DataException
    {
        Material spær = db.getMaterial(4);
        Material beslagH = db.getMaterial(19);
        Material beslagV = db.getMaterial(20);
        Material BeslagSkruer,
        Material lægte
        Material RoofScrews
        
        
    }

    public List<RoofType> getRoofs() throws DataException
    {
        RoofType result = null;
        List<RoofType> rooftypes = db.getRoofs();

        for (RoofType rooftype : rooftypes)
        {
            if (rooftype.getId() == 2)
            {
                result = rooftype;
            }

        }
        rooftypes.remove(result);

        return rooftypes;
    }

    public RoofType getRoof() throws DataException
    {
        List<RoofType> rooftypes = db.getRoofs();

        for (RoofType rooftype : rooftypes)
        {
            if (rooftype.getId() == 2)
            {
                return rooftype;
            }
        }

        return null;
    }

}
