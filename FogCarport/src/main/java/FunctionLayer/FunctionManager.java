/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

import DataLayer.DataException;
import DataLayer.DataFacade;
import FunctionLayer.Enum.Paid;
import FunctionLayer.Enum.Role;
import FunctionLayer.Enum.Status;
import FunctionLayer.HelpingClasses.Carport;
import FunctionLayer.HelpingClasses.Material;
import FunctionLayer.HelpingClasses.Order;
import FunctionLayer.HelpingClasses.Roof;
import FunctionLayer.HelpingClasses.RoofType;
import FunctionLayer.HelpingClasses.Shed;
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
                res += "Email er allerede i brug\n";
            }
        }

        if (!user.getEmail().contains("@") && !user.getEmail().contains("."))
        {
            res += "Venligst indtast en gyldig email\n";
        }
        if (user.getName().isEmpty() || user.getName() == null || isCaracter(user.getName()) == false)
        {
            res += "Venligst indtast dit navn (må kun indeholde bogstaver)\n";
        }
        if (user.getPassword().isEmpty() || user.getPassword() == null || user.getPassword().length() < 4)
        {
            res += "Venligst indtast en adgangskode med en minimumslængde på 4\n";
        }
        if (user.getAddress().isEmpty() || user.getAddress() == null)
        {
            res += "Venligst indtast din adresse\n";
        }
        if (user.getZipcode().isEmpty() || user.getZipcode() == null || user.getZipcode().length() != 4 || isNumber(user.getZipcode()) == false)
        {
            res += "Venligst indtast et gyldigt postnummer på 4 cifre\n";
        }

        if (user.getPhone().isEmpty() || user.getPhone() == null || user.getPhone().length() != 8 || isNumber(user.getPhone()) == false)
        {
            res += "Venligst indtast et gyldigt 8-cifret telefonnummer\n";
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

            if (user.getRole().equals(Role.CUSTOMER))
            {
                res = "Din bruger er nu slettet";
            } else
            {
                res = "Medarbejderen er nu slettet";
            }
        } else
        {
            res = "Brugeren kunne ikke slettes";
        }
        return res;
    }

    public List<User> getEmployeesAndAdmins() throws DataException
    {
        return db.getEmployeesAndAdmins();
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

    public void updateSalesPrice(int order_id, double salesprice) throws DataException
    {
        db.updateSalesPrice(order_id, salesprice);
    }

    public void updateStatusAndPaid(int order_id, Status status, Paid paid) throws DataException
    {
        db.updateStatusAndPaid(order_id, status, paid);
    }

    public User getEmployeeByEmail(String email) throws DataException
    {
        return db.getEmployeeByEmail(email);
    }

    public String updateEmployee(User user, String email, String name, String address, String zipcode, String phone) throws DataException
    {
        String res = "";
        List<User> users = db.getUsers();

        for (User u : users)
        {
            if (u.getEmail().equals(email) && u.getId() != user.getId())
            {
                res += "Email er allerede i brug\n";
            }
        }

        if (!email.contains("@") && !email.contains("."))
        {
            res += "Venligst indtast en gyldig email\n";
        }

        if (name == null || name.isEmpty() || isCaracter(name) == false)
        {
            res += "Venligst indtast dit navn (må kun indeholde bogstaver)\n";
        }

        if (address == null || address.isEmpty())
        {
            res += "Venligst indtast din adresse\n";
        }
        if (zipcode == null || zipcode.isEmpty() || zipcode.length() != 4 || isNumber(zipcode) == false)
        {
            res += "Venligst indtast et gyldigt postnummer på 4 cifre\n";
        }
        if (phone == null || phone.isEmpty() || phone.length() != 8 || isNumber(phone) == false)
        {
            res += "Venligst indtast et gyldigt 8-cifret telefonnummer\n";
        }

        if (res.isEmpty())
        {
            user.setEmail(email);
            user.setName(name);
            user.setAddress(address);
            user.setZipcode(zipcode);
            user.setPhone(phone);
            db.updateUser(user);
            res = "Medarbejderens information er opdateret";
        }

        return res;

    }

    public String updateCustomer(User user, String email, String name, String oldpassword, String newpassword, String address, String zipcode, String phone) throws DataException
    {
        String res = "";
        List<User> users = db.getUsers();

        for (User u : users)
        {
            if (u.getEmail().equals(email) && u.getId() != user.getId())
            {
                res += "Email er allerede i brug\n";
            }
        }
        if (!email.contains("@") && !email.contains("."))
        {
            res += "Venligst indtast en gyldig email\n";
        }

        if (!user.getPassword().equals(oldpassword))
        {
            res += "Venligst indtast din nuværende adgangskode, for at ændre adgangskoden\n";
        }
        if (newpassword == null || newpassword.isEmpty() || newpassword.length() < 4)
        {
            res += "Venligst indtast en adgangskode med en minimumslængde på 4\n";
        }

        if (name == null || name.isEmpty() || isCaracter(name) == false)
        {
            res += "Venligst indtast dit navn (må kun indeholde bogstaver)\n";
        }

        if (address == null || address.isEmpty())
        {
            res += "Venligst indtast din adresse\n";
        }
        if (zipcode == null || zipcode.isEmpty() || zipcode.length() != 4 || isNumber(zipcode) == false)
        {
            res += "Venligst indtast et gyldigt postnummer på 4 cifre\n";
        }
        if (phone == null || phone.isEmpty() || phone.length() != 8 || isNumber(phone) == false)
        {
            res += "Venligst indtast et gyldigt 8-cifret telefonnummer\n";
        }

        if (res.isEmpty())
        {
            user.setEmail(email);
            user.setName(name);
            user.setPassword(newpassword);
            user.setAddress(address);
            user.setZipcode(zipcode);
            user.setPhone(phone);
            db.updateUser(user);
            res = "Dine information er opdateret";
        }

        return res;

    }

    public String updatePassword(User user, String oldpassword, String newpassword) throws DataException
    {
        String res = "";

        if (!user.getPassword().equals(oldpassword))
        {
            res += "Venligst indtast din nuværende adgangskode, for at ændre adgangskoden\n";
        }
        if (newpassword == null || newpassword.isEmpty() || newpassword.length() < 4)
        {
            res += "Venligst indtast en adgangskode med en minimumslængde på 4\n";
        }

        if (res.isEmpty())
        {
            user.setPassword(newpassword);
            db.updatePassword(user.getId(), newpassword);
            res = "Din adgangskode er ændret";
        }

        return res;

    }

    public User getUser(int user_id) throws DataException
    {
        return db.getUser(user_id);
    }

    public String updateMaterial(Material material, String material_name, String unit, String material_class, double price) throws DataException
    {
        String res = "";

        for (Material m : getAllMaterials())
        {
            if (material_name.toLowerCase().equals(m.getName().toLowerCase()) && material.getId() != m.getId())
            {
                res += "Materiale med samme navn eksisterer allerede\n";
            }
        }

        if (material_name == null || material_name.isEmpty())
        {
            res += "Udfyld venligst materialets nye navn\n";
        }
        if (unit == null || unit.isEmpty() || isCaracter(unit) == false)
        {
            res += "Udfyld venligst en passende enhed for materialet\n";
        }
        if (material_class == null || material_class.isEmpty() || isCaracter(material_class) == false)
        {
            res += "Angiv venligst en passende kategori for materialet\n";
        }

        if (price == 0)
        {
            res += "Angiv venligst en ny pris for materialet\n";
        }

        if (res.isEmpty())
        {
            material.setName(material_name);
            material.setUnit(unit);
            material.setMaterial_class(material_class);
            material.setPrice(price);
            db.updateMaterial(material);

            res = "Materialet er opdateret";
        }

        return res;

    }

    public String deleteMaterial(Material material) throws DataException
    {
        String res = "";
        if (material != null)
        {
            db.deleteMaterial(material);
            res = "Materialet er slettet";
        } else
        {
            res = "Materialet kunne ikke slettes";
        }
        return res;
    }

    public String addMaterial(Material newMaterial) throws DataException
    {
        String res = "";

        for (Material m : getAllMaterials())
        {
            if (newMaterial.getName().toLowerCase().equals(m.getName().toLowerCase()))
            {
                res += "Materiale med samme navn eksisterer allerede\n";
            }
        }

        if (newMaterial.getName() == null || newMaterial.getName().isEmpty())
        {
            res += "Udfyld venligst materialets navn\n";
        }
        if (newMaterial.getUnit() == null || newMaterial.getUnit().isEmpty() || isCaracter(newMaterial.getUnit()) == false)
        {
            res += "Udfyld venligst en passende enhed for materialet\n";
        }
        if (newMaterial.getMaterial_class() == null || newMaterial.getMaterial_class().isEmpty() || isCaracter(newMaterial.getMaterial_class()) == false)
        {
            res += "Angiv venligst en passende kategori for materialet\n";
        }

        if (newMaterial.getPrice() == 0)
        {
            res += "Angiv venligst en pris for materialet\n";
        }

        if (res.isEmpty())
        {
            db.addMaterial(newMaterial);
            res = "Materialet er tilføjet til listen";
        }

        return res;

    }

    public List<Material> getAllMaterials() throws DataException
    {
        return db.getMaterials();
    }

    public Material getMaterial(int material_id) throws DataException
    {
        return db.getMaterial(material_id);
    }

    public String updateCarport(Carport carport, int carport_depth, int carport_width, RoofType rooftype, int roofslope, int shed_width, int shed_depth) throws DataException
    {
        String res = "";

        if (carport_width > 750 || carport_depth > 800)
        {
            res += "Carporten må maksimum være 750 cm bred og 800 cm dyb\n";
        }
        if (roofslope % 5 != 0)
        {
            res += "Vælg venligst en hældning fra menuen\n";
        }
        if (shed_width > carport_width - 30 || shed_depth > carport_depth - 30)
        {
            res += "Skuret må minimum være 30 cm kortere end selve carporten på begge led\n";
        }

        if (res.isEmpty())
        {
            carport.setDepth(carport_depth);
            carport.setWidth(carport_width);
            carport.getRoof().setSlope(roofslope);
            carport.getRoof().setType(rooftype);
            if (carport.getShed() != null)
            {
                if (shed_width == 0 && shed_depth == 0)
                {
                    db.deleteShedId(carport);
                    db.removeShed(carport.getShed());
                    carport.setShed(null);
                    res = "Carporten er opdateret og skuret er fjernet";
                } else
                {
                    carport.getShed().setDepth(shed_depth);
                    carport.getShed().setWidth(shed_width);

                    res = "Carporten er opdateret";
                }

            } else if (shed_width != 0 && shed_depth != 0)
            {
                Shed shed = new Shed(shed_depth, shed_width);
                carport.setShed(shed);
                res = "Carporten er opdateret og skuret er tilføjet";
            } else
            {
                res = "Carporten er opdateret";
            }
        }

        db.updateCarport(carport);

        carport.resetParts();
        carport.getRoof().resetParts();
        calcCarport(carport);
        calcRoof(carport);

        if (carport.getShed() != null)
        {
            carport.getShed().resetParts();
            calcShed(carport);
        }

        return res;

    }

    public String addRoofType(RoofType rooftype) throws DataException
    {
        String res = "";

        for (RoofType r : db.getRoofs())
        {
            if (rooftype.getName().toLowerCase().equals(r.getName().toLowerCase()))
            {
                res += "Tagtype med samme navn eksisterer allerede\n";
            }
            if (rooftype.getM1() == r.getM1())
            {
                res += "Materialet er allerede tilknyttet en anden tagtype\n";
            }
            if (r.getM2() != null)
            {
                if (rooftype.getM2() == r.getM2())
                {
                    res += "Materialet er allerede tilknyttet en anden tagtype\n";
                }
            }
        }
        if (!rooftype.getRoof_class().equals("flat") && !rooftype.getRoof_class().equals("slope"))
        {
            res += "Vælg venligst imellem de to kategorier (fladt tag, tag med rejsning)\n";
        }
        if(rooftype.getM1() == rooftype.getM2())
        {
            res += "Vælg venligst to forskellige materialer eller kun en enkelt\n";
        }

        if (res.isEmpty())
        {
            db.addRoofType(rooftype);
            res = "Tagtypen er tilføjet";
        }
        return res;

    }
    
    public List<RoofType> getRoofs() throws DataException
    {
        return db.getRoofs();
    }
    
    public String deleteRoofType(RoofType rooftype) throws DataException
    {
        String res = "";
        if (rooftype != null)
        {
            db.deleteRoofType(rooftype);
            res = "Tagtypen er slettet";
        } else
        {
            res = "Tagtypen kunne ikke slettes";
        }
        return res;
    }
}
