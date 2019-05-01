/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

import FunctionLayer.HelpingClasses.Carport;
import FunctionLayer.HelpingClasses.Material;
import FunctionLayer.HelpingClasses.Order;
import FunctionLayer.HelpingClasses.RoofType;
import FunctionLayer.HelpingClasses.User;
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
    private CalculateShed cs;

    public FunctionManager() throws DataException
    {
        db = DataFacade.getInstance();
        cp = new CalculatePackages();
        cr = new CalculateRoof(cp);
        cs = new CalculateShed(cp);
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

    public User login(String email, String password) throws DataException
    {
        return db.login(email, password);
    }

    public String newUser(User user) throws DataException
    {
        String res = "";
        List<User> users = db.getUsers();

        for (User u : users)
        {
            if (u.getEmail().equals(user.getEmail()))
            {
                res = "Email er allerede i brug";
            }
        }

        if (!user.getEmail().contains("@") && !user.getEmail().contains("."))
        {
            res = "Venligst indtast en gyldig email";
        }
        if (user.getName().isEmpty() || user.getName() == null || isCaracter(user.getName()) == false)
        {
            res = "Venligst indtast dit navn (må kun indeholde bogstaver)";
        }
        if (user.getPassword().isEmpty() || user.getPassword() == null || user.getPassword().length() < 4)
        {
            res = "Venligst indtast en adgangskode med en minimumslængde på 4";
        }
        if (user.getAddress().isEmpty() || user.getAddress() == null)
        {
            res = "Venligst indtast din adresse";
        }
        if (user.getZipcode().isEmpty() || user.getZipcode() == null || user.getZipcode().length() != 4 || isNumber(user.getZipcode()) == false)
        {
            res = "Venligst indtast et gyldigt postnummer på 4 cifre";
        }

        if (user.getPhone().isEmpty() || user.getPhone() == null || user.getPhone().length() != 8 || isNumber(user.getPhone()) == false)
        {
            res = "Venligst indtast et gyldigt 8-cifret telefonnummer";
        }

        if (res.isEmpty())
        {
            db.newUser(user);
            res = "Din bruger er nu oprettet";
        }

        return res;
    }

    private boolean isNumber(String str)
    {
        for (char c : str.toCharArray())
        {
            if (!Character.isDigit(c))
            {
                return false;
            }
        }
        return true;
    }

    private boolean isCaracter(String str)
    {
        for (char c : str.toCharArray())
        {
            if (Character.isDigit(c))
            {
                return false;
            }
        }
        return true;
    }

    public String placeOrder(Order order) throws DataException
    {
        String res = "";

        if (order != null)
        {
            db.orderCarport(order.getCarport());
            db.placeOrder(order);
            res = "Tak for din forespørgsel. Vi vil behandle den hurtigst muligt";
        } else
        {
            res = "Din forespørgsel kunne ikke blive sendt";
        }
        return res;
    }

    public void isShipped(Order order) throws DataException
    {
        String shipped = db.orderShipped(order.getOrder_id());
        order.setShipped(shipped);
    }

    public void calcCarport(Carport carport) throws DataException
    {
        if (carport.getWidth() <= 750 && carport.getDepth() <= 800)
        {
            Material pole = getMaterial(2);
            Material rem = getMaterial(3);
            Material bolts = getMaterial(26);
            Material discs = getMaterial(27);

            c.calculatepoles(carport, pole, bolts, discs);
            c.calculateRem(carport, rem);
        }
    }

    private void calcFlatroof(Carport carport) throws DataException
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

    private void calcSlopeRoof(Carport carport) throws DataException
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

    public void calcShed(Carport carport) throws DataException
    {
        if (carport.getShed().getWidth() <= carport.getWidth() - 30 && carport.getShed().getDepth() <= carport.getDepth() - 30)
        {
            Material stolpe = getMaterial(2);
            Material bræt = getMaterial(4);
            Material vinkelbeslag = getMaterial(22);
            Material skruer = getMaterial(32);
            Material beklædning = getMaterial(5);
            Material skrue1 = getMaterial(33);
            Material skrue2 = getMaterial(34);
            Material lægte = getMaterial(10);
            Material stalddørsgrebene = getMaterial(20);
            Material hængselet = getMaterial(21);
            Material planker = getMaterial(5);

            if (carport.getRoof().getSlope() == 0)
            {
                cs.calcShedFlatRoof(carport, stolpe, bræt, vinkelbeslag, skruer, beklædning, skrue1, skrue2, lægte, stalddørsgrebene, hængselet, planker);

            } else
            {
                cs.calcShedSlopeRoof(carport, stolpe, bræt, vinkelbeslag, skruer, beklædning, skrue1, skrue2, lægte, stalddørsgrebene, hængselet, planker);
            }
        }
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

    public RoofType getRoofType(String roofkind, int typeId) throws DataException
    {
        RoofType type = null;

        if (roofkind.equals("flat"))
        {
            List<RoofType> rooftypes = getFlatRoofs();
            for (RoofType rooftype : rooftypes)
            {
                if (rooftype.getId() == typeId)
                {
                    type = rooftype;
                }
            }
        } else
        {
            List<RoofType> rooftypes = getSlopedRoofs();
            for (RoofType rooftype : rooftypes)
            {
                if (rooftype.getId() == typeId)
                {
                    type = rooftype;
                }
            }
        }

        return type;
    }

    public void calcRoof(Carport carport) throws DataException
    {
        if (carport.getRoof().getType().getRoof_class().equals("flat"))
        {
            calcFlatroof(carport);
        } else
        {
            calcSlopeRoof(carport);
        }
    }

}
