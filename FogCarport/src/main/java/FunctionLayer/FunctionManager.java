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
import FunctionLayer.HelpingClasses.RoofType;
import FunctionLayer.HelpingClasses.Shed;
import FunctionLayer.HelpingClasses.User;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author sofieamalielandt
 */
public class FunctionManager
{

    private static FunctionManager instance = null;
    private DataFacade db;
    private CalculateCarport c;
    private CalculateRoof cr;
    private CalculatePackages cp;
    private CalculateShed cs;
    private GenerateDrawing GD;

    /**
     *
     * @throws DataException
     */
    public FunctionManager() throws DataException
    {
        db = DataFacade.getInstance();
        cp = new CalculatePackages();
        cr = new CalculateRoof(db, cp);
        cs = new CalculateShed(db, cp);
        c = new CalculateCarport(db);
        GD = new GenerateDrawing();
    }

    /**
     *
     * @return instance of Function Manager as a singelton
     * @throws DataException
     */
    public static FunctionManager getInstance() throws DataException
    {
        if (instance == null)
        {
            instance = new FunctionManager();
        }
        return instance;
    }

    /**
     *
     * @param user_id
     * @return
     * @throws DataException
     */
    public User getUser(int user_id) throws DataException
    {
        return db.getUser(user_id);
    }

    /**
     *
     * @param email String
     * @return a employee user with the passed email
     * @throws DataException
     * @see DataLayer.DataFacade#getEmployeeByEmail(java.lang.String)
     */
    public User getEmployeeByEmail(String email) throws DataException
    {
        return db.getEmployeeByEmail(email);
    }

    /**
     * findes all the employees and admins in the database and returnes them
     *
     * @return a list of all users that are employees and admins
     * @throws DataException
     * @see DataLayer.DataFacade#getEmployeesAndAdmins()
     */
    public List<User> getEmployeesAndAdmins() throws DataException
    {
        return db.getEmployeesAndAdmins();
    }

    /**
     * finds the user in the database with the information passed
     *
     * @param email String
     * @param password String
     * @return User with the given login input or null if not exists
     * @throws DataException
     * @see DataLayer.DataFacade#login(java.lang.String, java.lang.String)
     */
    public User login(String email, String password) throws DataException
    {
        return db.login(email, password);
    }

    /**
     * creates a new user in the database
     *
     * @param user object
     * @return if information entered is wrong the String returned tells the
     * user what they did wrong, else it returns sayting that everything ok
     * @throws DataException
     * @see DataLayer.DataFacade#newUser(FunctionLayer.HelpingClasses.User)
     */
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
        for (char ch : str.toCharArray())
        {
            if (!Character.isDigit(ch))
            {
                return false;
            }
        }
        return true;
    }

    private boolean isCaracter(String str)
    {
        for (char ch : str.toCharArray())
        {
            if (Character.isDigit(ch))
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Removes a user from the database
     *
     * @param user Object
     * @return information on how the database call whent
     * @throws DataException
     * @see DataLayer.DataFacade#removeOrder(FunctionLayer.HelpingClasses.Order)
     */
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

    /**
     * Updates information about a customer in the database
     *
     * @param user Object
     * @param email String
     * @param name String
     * @param oldpassword String
     * @param newpassword String
     * @param address String
     * @param zipcode int
     * @param phone int
     * @return status for update, if wrong information was entered the string
     * contains the mistake
     * @throws DataException
     * @see DataLayer.DataFacade#updateUser(FunctionLayer.HelpingClasses.User)
     */
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

    /**
     * updates the information of a employee in the database
     *
     * @param user Object
     * @param email String
     * @param name String
     * @param address String
     * @param zipcode int
     * @param phone int
     * @return status of opdate, or mistake if data entered is wrong
     * @throws DataException
     * @see DataLayer.DataFacade#updateUser(FunctionLayer.HelpingClasses.User)
     */
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

    /**
     *
     * @param user Object
     * @param oldpassword String
     * @param newpassword String
     * @return Status for update, if wrong information was entered the string
     * contains the mistake
     * @throws DataException
     * @see DataLayer.DataFacade#updatePassword(int, java.lang.String)
     */
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

    /**
     * Findes the order with the id passed in the datbase
     *
     * @param order_id int
     * @return a order from the database
     * @throws DataException
     * @see DataLayer.DataFacade#getOrder(int)
     */
    public Order getOrder(int order_id) throws DataException
    {
        return db.getOrder(order_id);
    }

    /**
     *
     * @return @throws DataException
     */
    public List<Order> getOrders() throws DataException
    {
        return db.getOrders();
    }

    /**
     * Gets all the orders that is from a client with the email passed
     *
     * @param email String
     * @return a list of all the orders with the email passed
     * @throws DataException
     * @see DataLayer.DataFacade#getOrdersByEmail(java.lang.String)
     */
    public List<Order> getOrdersByEmail(String email) throws DataException
    {
        return db.getOrdersByEmail(email);
    }

    /**
     * places an order in the database
     *
     * @param order object
     * @return if information entered is wrong the String returned tells the
     * user what they did wrong, else it returns sayting that everything ok
     * @throws DataException
     * @see DataLayer.DataFacade#placeOrder(FunctionLayer.HelpingClasses.Order)
     */
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

    public void GDPRCheck(List<Order> orders) throws ParseException, DataException
    {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -3);
        Date date3YearsAgo = cal.getTime();
        List<Order> oldOrders = new ArrayList<>();

        for (Order order : orders)
        {
            Date date = dateFormatter.parse(order.getOrder_date());

            if (date.before(date3YearsAgo))
            {
                removeOrder(order);
                oldOrders.add(order);
            }
        }

        for (Order oldOrder : oldOrders)
        {
            orders.remove(oldOrder);
        }
    }

    public void removeOrder(Order order) throws DataException
    {
        if (order.getCarport().getShed() != null)
        {
            db.removeShed(order.getCarport());
        }
        db.removeOrder(order);
        db.removeCarport(order.getCarport());
        db.removeRoof(order.getCarport().getRoof());

    }

    /**
     * findes a order based on the id passed and returns the shipping status
     *
     * @param order Object
     * @throws DataException
     * @see DataLayer.DataFacade#
     */
    public void isShipped(Order order) throws DataException
    {
        String shipped = db.orderShipped(order.getOrder_id());
        order.setShipped(shipped);
    }

    /**
     * Changes the price of a order in the database
     *
     * @param order_id int
     * @param salesprice double
     * @throws DataException
     * @see DataLayer.DataFacade#updateSalesPrice(int, double)
     */
    public void updateSalesPrice(int order_id, double salesprice) throws DataException
    {
        db.updateSalesPrice(order_id, salesprice);
    }

    /**
     * Updates the status of shippment and order status in the database of an
     * order
     *
     * @param order_id int
     * @param status enum
     * @param paid enum
     * @throws DataException
     * @see DataLayer.DataFacade#updateStatusAndPaid(int,
     * FunctionLayer.Enum.Status, FunctionLayer.Enum.Paid)
     */
    public void updateStatusAndPaid(int order_id, Status status, Paid paid) throws DataException
    {
        db.updateStatusAndPaid(order_id, status, paid);
    }

    /**
     * updates the information on a carport in the database
     *
     * @param carport object
     * @param carport_depth int
     * @param carport_width int
     * @param rooftype rooftype object
     * @param roofslope int
     * @param shed_width int
     * @param shed_depth int
     * @return status for update, if wrong information was entered the string
     * contains the mistake
     * @throws DataException
     * @see
     * DataLayer.DataFacade#updateCarport(FunctionLayer.HelpingClasses.Carport)
     */
    public String updateCarport(Carport carport, int carport_depth, int carport_width, RoofType rooftype, int roofslope, int shed_width, int shed_depth) throws DataException
    {
        String res = "";

        if (carport_width > 750 || carport_depth > 800)
        {
            res += "Carporten må maksimum være 750 cm bred og 800 cm dyb\n";
        }
        if (roofslope < 15 || roofslope > 45 || roofslope % 5 != 0)
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
                    db.removeShed(carport);
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

    /**
     * Gets all the roofs from the database
     *
     * @return list of all roofs contains the mistake
     * @throws DataException
     * @see DataLayer.DataFacade#getRoofs()
     */
    public List<RoofType> getRoofs() throws DataException
    {
        return db.getRoofs();
    }

    /**
     *
     * @return @throws DataException
     */
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

    /**
     * gets a list of all the flat roofs in the database
     *
     * @return a list of all the Falt Roofs
     * @throws DataException
     */
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

    /**
     * Gets a roof in the database with the id passed
     *
     * @param typeId int
     * @return a roof with the id passed
     * @throws DataException
     * @see DataLayer.DataFacade#getRoof(int)
     */
    public RoofType getRoofTypeById(int typeId) throws DataException
    {
        return db.getRoof(typeId);
    }

    /**
     * Adds a rooftype in the database
     *
     * @param rooftype object
     * @return Status for update, if wrong information was entered the string
     * contains the mistake
     * @throws DataException
     * @see
     * DataLayer.DataFacade#addRoofType(FunctionLayer.HelpingClasses.RoofType)
     */
    public String addRoofType(RoofType rooftype) throws DataException
    {
        String res = "";

        for (RoofType r : db.getRoofs())
        {
            String name = r.getName();
            if (rooftype.getName().toLowerCase().equals(r.getName().toLowerCase()))
            {
                res += "Tagtype med samme navn eksisterer allerede\n";
            }
            if (rooftype.getM1().getId() == r.getM1().getId())
            {
                res += "Materialet er allerede tilknyttet en anden tagtype\n";
                break;
            }
            if (rooftype.getM2() != null)
            {
                if (rooftype.getM2().getId() == r.getM1().getId())
                {
                    res += "Materialet er allerede tilknyttet en anden tagtype\n";
                    break;
                }
            }
            if (r.getM2() != null)
            {
                if (rooftype.getM1().getId() == r.getM2().getId())
                {
                    res += "Materialet er allerede tilknyttet en anden tagtype\n";
                    break;
                }

                if (rooftype.getM2() != null)
                {
                    if (rooftype.getM2().getId() == r.getM2().getId() || rooftype.getM2().getId() == r.getM1().getId())
                    {
                        res += "Materialet er allerede tilknyttet en anden tagtype\n";
                        break;
                    }
                }
            }
        }
        if (!rooftype.getRoof_class().equals("flat") && !rooftype.getRoof_class().equals("slope"))
        {
            res += "Vælg venligst imellem de to kategorier (fladt tag, tag med rejsning)\n";
        }
        if (rooftype.getM1() == rooftype.getM2())
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

    /**
     * deletes a rooftype from the database
     *
     * @param rooftype object
     * @return Status for update, if wrong information was entered the string
     * contains the mistake
     * @throws DataException
     * @see
     * DataLayer.DataFacade#deleteRoofType(FunctionLayer.HelpingClasses.RoofType)
     */
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

    /**
     * updates a roof type in the database
     *
     * @param rooftype object
     * @param name String
     * @param m1 Material
     * @param m2 Material
     * @return status for update, if wrong information was entered the string
     * contains the mistake
     * @throws DataException
     * @see
     * DataLayer.DataFacade#updateRoofType(FunctionLayer.HelpingClasses.RoofType)
     */
    public String updateRoofType(RoofType rooftype, String name, Material m1, Material m2) throws DataException
    {
        String res = "";

        if (m1 == m2)
        {
            res += "Vælg venligst 2 forskellige materiale eller kun 1\n";
        }
        
        for (RoofType r : db.getRoofs())
        {
            if (name.toLowerCase().equals(r.getName().toLowerCase()) && rooftype.getId() != r.getId())
            {
                res += "Tagtype med samme navn eksisterer allerede\n";
            }

            if (m2 != null)
            {
                if (m1.getId() == r.getM1().getId() && rooftype.getId() != r.getId() || m2.getId() == r.getM1().getId() && rooftype.getId() != r.getId())
                {
                    res += "Materialet er allerede tilknyttet en anden tagtype\n";
                    break;
                }

                if (r.getM2() != null)
                {
                    if (m1.getId() == r.getM2().getId() && rooftype.getId() != r.getId() || m2.getId() == r.getM2().getId() && rooftype.getId() != r.getId())
                    {
                        res += "Materialet er allerede tilknyttet en anden tagtype\n";
                        break;
                    }
                }

            } else
            {
                if (m1.getId() == r.getM1().getId() && rooftype.getId() != r.getId())
                {
                    res += "Materialet er allerede tilknyttet en anden tagtype\n";
                    break;
                }

                if (r.getM2() != null)
                {
                    if (m1.getId() == r.getM2().getId() && rooftype.getId() != r.getId())
                    {
                        res += "Materialet er allerede tilknyttet en anden tagtype\n";
                        break;
                    }
                }

            }

        }

        if (res.isEmpty())
        {
            rooftype.setName(name);
            rooftype.setM1(m1);
            rooftype.setM2(m2);
            db.updateRoofType(rooftype);
            res = "Tagtypen er opdateret";
        }
        return res;
    }

    /**
     * Gets a material with the passed id
     *
     * @param material_id int
     * @return material from database with same id as passed
     * @throws DataException
     */
    public Material getMaterial(int material_id) throws DataException
    {
        return db.getMaterial(material_id);
    }

    /**
     * gets all the materials from the database
     *
     * @return all materials in a list
     * @throws DataException
     */
    public List<Material> getAllMaterials() throws DataException
    {
        return db.getMaterials();
    }

    /**
     * adds a material in the database
     *
     * @param newMaterial Material
     * @return status for update, if wrong informations is added String contains
     * mistake
     * @throws DataException
     * @see
     * DataLayer.DataFacade#addMaterial(FunctionLayer.HelpingClasses.Material)
     */
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

    /**
     *
     * @param material
     * @return status for update, if wrong information was entered the string
     * contains the mistake
     * @throws DataException
     * @see
     * DataLayer.DataFacade#deleteMaterial(FunctionLayer.HelpingClasses.Material)
     */
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

    /**
     *
     * @param material
     * @param material_name
     * @param unit
     * @param material_class
     * @param price
     * @return
     * @throws DataException
     * @see
     * DataLayer.DataFacade#updateMaterial(FunctionLayer.HelpingClasses.Material)
     */
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
            res += "Angiv venligst en pris for materialet\n";
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

    /**
     * calculates the Materials needed to bouild the carport and puts the
     * Materials in a list on the carport
     *
     * @param carport object
     * @throws DataException
     */
    public void calcCarport(Carport carport) throws DataException
    {
        if (carport.getWidth() <= 750 && carport.getDepth() <= 800)
        {
            c.calcCarport(carport);
        }
    }

    /**
     * Calculates the materials needed to build the roofe and puts them in a
     * list on the roof object
     *
     * @param carport object
     * @throws DataException
     *
     */
    public void calcRoof(Carport carport) throws DataException
    {
        if (carport.getRoof().getType().getRoof_class().equals("flat"))
        {
            cr.calculateFlatRoof(carport);
            cr.calculatePlatsmo(carport);

        } else
        {
            cr.calculateSlopeRoof(carport);
        }
    }

    /**
     * calculates the materials needed to build the shed and puts the Materials
     * in a list on the shead
     *
     * @param carport Object
     * @throws DataException
     */
    public void calcShed(Carport carport) throws DataException
    {
        if (carport.getShed().getWidth() <= carport.getWidth() - 30 && carport.getShed().getDepth() <= carport.getDepth() - 30)
        {
            cs.calcShed(carport);
        }
    }

    /**
     * Makes a drawing of a Roof for a Carport in SVG format
     *
     * @param carport Object
     * @return a SVG format Drawing in a String format
     *
     */
    public String drawCarport(Carport carport)
    {
        return GD.drawCarport(carport);
    }
}
