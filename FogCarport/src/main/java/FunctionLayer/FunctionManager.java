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
    private CalculateRoof cr;
    private CalculatePackages cp;

    public FunctionManager() throws DataException
    {
        db = DataFacade.getInstance();
        cp = new CalculatePackages();
        cr = new CalculateRoof(cp);
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
        Material pole = getMaterial(2);
        Material rem = getMaterial(3);
        Material bolts = getMaterial(26);
        Material discs = getMaterial(27);

        c.calculatepoles(carport, pole, bolts, discs);
        c.calculateRem(carport, rem);

    }

    public void calFlatroof(Carport carport) throws DataException
    {
        Material spær = getMaterial(3);
        Material universalV = getMaterial(19);
        Material universalH = getMaterial(18);
        Material beslagSkruer = getMaterial(32);
        Material lægte = getMaterial(7);
        Material tagskruer = getMaterial(30);
        Material understern = getMaterial(8);
        Material overstern = getMaterial(9);
        Material vandbræt = getMaterial(5);
        Material skruer = getMaterial(23);
        Material plastmo = getMaterial(12);
        Material plastmotætning = getMaterial(42);

        cr.calculateFlatRoof(carport, spær, universalV, universalH, beslagSkruer, lægte, tagskruer, understern, overstern, vandbræt, skruer);
        cr.calculatePlatsmo(carport, plastmo, plastmotætning);
    }

    public void calSlopeRoof(Carport carport) throws DataException
    {
        Material spær = getMaterial(3);
        Material taglægter = getMaterial(7);
        Material spærbeslag = getMaterial(43);
        Material beslagSkruerSpær = getMaterial(24);
        Material skruer = getMaterial(25);
        Material universalV = getMaterial(19);
        Material universalH = getMaterial(18);
        Material toplægteholder = getMaterial(15);
        Material tegl = carport.getRoof().getType().getM2();
        Material rygsten = carport.getRoof().getType().getM1();
        Material rygstensbeslag = getMaterial(16);
        Material beklædning = getMaterial(5);
        Material vandbræt = getMaterial(5);
        Material trykimpbræt = getMaterial(1);
        Material skruerTotal = getMaterial(23);
        Material skrue1 = getMaterial(28);
        Material skrue2 = getMaterial(29);
                
        cr.calculateSlopeRoof(carport, spær, taglægter, spærbeslag, beslagSkruerSpær, skruer, universalV, universalH, toplægteholder, tegl, rygsten, rygstensbeslag, beklædning, vandbræt, trykimpbræt, skruerTotal, skrue1, skrue2);

    }

    private Material getMaterial(int id) throws DataException
    {
        Material result = null;
        List<Material> materials = db.getMaterials();
        for (Material material : materials)
        {
            if (material.getId() == id)
            {
                result = material;
            }
        }
        return result;
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
