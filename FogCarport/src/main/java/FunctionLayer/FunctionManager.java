/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

import DataLayer.DataException;
import DataLayer.DataFacade;
import FunctionLayer.HelpingClasses.Carport;
import FunctionLayer.HelpingClasses.Material;
import FunctionLayer.HelpingClasses.Order;
import FunctionLayer.HelpingClasses.RoofType;
import FunctionLayer.HelpingClasses.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private GenerateDrawing GD;
    

    public FunctionManager() throws DataException
    {
        db = DataFacade.getInstance();
        cp = new CalculatePackages();
        cr = new CalculateRoof(cp);
        cs = new CalculateShed(cp);
        c = new Calculate();
        GD = new GenerateDrawing();
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

    public String removeUser(User user) throws DataException
    {
        String res = "";
        if (user != null)
        {
            db.removeUser(user);
            res = "Brugeren er nu slettet";
        } else
        {
            res = "Brugeren kunne ikke slettes";
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
            Map<Integer, Material> map = getMaterials();

            Material pole = map.get(2);
            Material rem = map.get(3);
            Material bolts = map.get(26);
            Material discs = map.get(27);

            c.calculatepoles(carport, pole, bolts, discs);
            c.calculateRem(carport, rem);
        }
    }

    private void calcFlatroof(Carport carport) throws DataException
    {
        Map<Integer, Material> map = getMaterials();

        Material spær = map.get(3);
        Material universalV = map.get(19);
        Material universalH = map.get(18);
        Material beslagSkruer = map.get(32);
        Material lægte = map.get(7);
        Material tagskruer = map.get(30);
        Material understern = map.get(8);
        Material overstern = map.get(9);
        Material vandbræt = map.get(5);
        Material skruer = map.get(23);
        Material plastmo = map.get(12);
        Material plastmotætning = map.get(42);

        cr.calculateFlatRoof(carport, spær, universalV, universalH, beslagSkruer, lægte, tagskruer, understern, overstern, vandbræt, skruer);
        cr.calculatePlatsmo(carport, plastmo, plastmotætning);
    }

    private void calcSlopeRoof(Carport carport) throws DataException
    {
        Map<Integer, Material> map = getMaterials();

        Material spær = map.get(3);
        Material taglægter = map.get(7);
        Material spærbeslag = map.get(43);
        Material beslagSkruerSpær = map.get(24);
        Material skruer = map.get(25);
        Material universalV = map.get(19);
        Material universalH = map.get(18);
        Material toplægteholder = map.get(15);
        Material tegl = carport.getRoof().getType().getM2();
        Material rygsten = carport.getRoof().getType().getM1();
        Material rygstensbeslag = map.get(16);
        Material beklædning = map.get(5);
        Material vandbræt = map.get(5);
        Material trykimpbræt = map.get(1);
        Material skruerTotal = map.get(23);
        Material skrue1 = map.get(28);
        Material skrue2 = map.get(29);

        cr.calculateSlopeRoof(carport, spær, taglægter, spærbeslag, beslagSkruerSpær, skruer, universalV, universalH, toplægteholder, tegl, rygsten, rygstensbeslag, beklædning, vandbræt, trykimpbræt, skruerTotal, skrue1, skrue2);

    }

    public void calcShed(Carport carport) throws DataException
    {
        if (carport.getShed().getWidth() <= carport.getWidth() - 30 && carport.getShed().getDepth() <= carport.getDepth() - 30)
        {

            Map<Integer, Material> map = getMaterials();

            Material stolpe = map.get(2);
            Material bræt = map.get(4);
            Material vinkelbeslag = map.get(22);
            Material skruer = map.get(32);
            Material beklædning = map.get(5);
            Material skrue1 = map.get(33);
            Material skrue2 = map.get(34);
            Material lægte = map.get(10);
            Material stalddørsgrebene = map.get(20);
            Material hængselet = map.get(21);
            Material planker = map.get(5);

            if (carport.getRoof().getSlope() == 0)

            {
                cs.calcShedFlatRoof(carport, stolpe, bræt, vinkelbeslag, skruer, beklædning, skrue1, skrue2, lægte, stalddørsgrebene, hængselet, planker);

            } else
            {
                cs.calcShedSlopeRoof(carport, stolpe, bræt, vinkelbeslag, skruer, beklædning, skrue1, skrue2, lægte, stalddørsgrebene, hængselet, planker);
            }
        }
    }

    private Map<Integer, Material> getMaterials() throws DataException
    {

        Map<Integer, Material> map = new HashMap<>();

        List<Material> materials = db.getMaterials();
        for (Material material : materials)
        {
            map.put(material.getId(), material);
        }
        return map;
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

    public RoofType getRoofTypeById(int typeId) throws DataException
    {
        RoofType type = null;

        return db.getRoof(typeId);

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

    public Order getOrder(int order_id) throws DataException
    {
        return db.getOrder(order_id);
    }

    public List<Order> getOrders() throws DataException
    {
        return db.getOrders();
    }

    public List<Order> getOrdersByEmail(String email) throws DataException
    {
        return db.getOrdersByEmail(email);
    }

    public String drawingOfRoof(Carport carport)
    {
       
    return GD.drawRoofFromTop(carport);
        
    }
    
}
