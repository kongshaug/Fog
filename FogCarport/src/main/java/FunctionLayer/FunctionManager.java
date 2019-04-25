/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

import DataLayer.DataException;
import DataLayer.DataFacade;
import java.util.ArrayList;
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
        Material BeslagSkruer;
        Material lægte;
        //Material RoofScrews

    }

    public void calSlopeRoof(Carport carport) throws DataException
    {
        Material spær = db.getMaterial(4);
        Material taglægter = db.getMaterial(8);
        Material spærbeslag = db.getMaterial(44);
        Material beslagSkruerSpær = db.getMaterial(25);
        Material skruer = db.getMaterial(26);
        Material universalV = db.getMaterial(20);
        Material universalH = db.getMaterial(19);
        Material toplægteholder = db.getMaterial(16);
        Material tegl = carport.getRoof().getType().getM2();
        Material rygsten = carport.getRoof().getType().getM1();
        Material rygstensbeslag = db.getMaterial(17);
        Material beklædning = db.getMaterial(6);
        Material vandbræt = db.getMaterial(6);
        Material trykimpbræt = db.getMaterial(1);
        Material skruerTotal = db.getMaterial(24);

        c.caluclatSlopeRoof(carport, spær, taglægter, spærbeslag, beslagSkruerSpær, skruer, universalV, universalH, toplægteholder, tegl, rygsten, rygstensbeslag, beklædning, vandbræt, trykimpbræt, skruerTotal);

    }

    public List<RoofType> getSlopedRoofs() throws DataException
    {
        List<RoofType> rooftypes = db.getRoofs();
        List<RoofType> slopedRoofs = new ArrayList<>();

        for (RoofType rooftype : rooftypes)
        {
            if (rooftype.getRoof_class().equals("slope"))
            {
                slopedRoofs.add(rooftype);
            }

        }

        return slopedRoofs;
    }

    public List<RoofType> getFlatRoofs() throws DataException
    {
        List<RoofType> rooftypes = db.getRoofs();
        List<RoofType> flatRoofs = new ArrayList<>();

        for (RoofType rooftype : rooftypes)
        {
            if (rooftype.getRoof_class().equals("flat"))
            {
                flatRoofs.add(rooftype);
            }

        }

        return flatRoofs;
    }

}
