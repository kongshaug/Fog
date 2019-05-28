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
     * Initializes the instance of DataFacade as db and
     * a newly created CalcualtePackages, CalculateRoof,
     * CalculateShed, CalculateCarport and GenerateDrawing,
     * for further use in methods 
     *
     * @throws DataException if initializing not possible
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
     * Creates and initializes an instance of FunctionManager
     *
     * @return an instance of FunctionManager as a singelton
     * @throws DataException if initializing is not possible
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
     * @param user_id is used to detect the specific User
     * @return an object from the class User with the specific user_id
     * @throws DataException if retrieval not possible
     * @see DataLayer.DataFacade#getUser(int) 
     */
    public User getUser(int user_id) throws DataException
    {
        return db.getUser(user_id);
    }

    /**
     *
     * @param email is used to detect the specific User
     * @return an object from the class User with the specific email
     * @throws DataException if retrieval not possible
     * @see DataLayer.DataFacade#getEmployeeByEmail(java.lang.String)
     */
    public User getEmployeeByEmail(String email) throws DataException
    {
        return db.getEmployeeByEmail(email);
    }

    /**
     *
     * @return a list of object from the class User, with all the employees and admins
     * @throws DataException if retrieval not possible
     * @see DataLayer.DataFacade#getEmployeesAndAdmins()
     */
    public List<User> getEmployeesAndAdmins() throws DataException
    {
        return db.getEmployeesAndAdmins();
    }

    /**
     * Retrieve a User associated with the specific email and password in database
     *
     * @param email is used to detect the specific User
     * @param password a String
     * @return an object from the class User
     * @throws DataException if retrieval not possible
     * @see DataLayer.DataFacade#login(java.lang.String, java.lang.String)
     */
    public User login(String email, String password) throws DataException
    {
        return db.login(email, password);
    }

    /**
     * 
     * The User is inserted into the database, if and only if the User is not null 
     * and the User object does not represents the same email as an User from the database.
     *
     * @param user the User to insert in database
     * @return a string telling whether the insert is succesful or not
     * @throws DataException if insert is not possible
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
     * 
     * The User is deleted in the database, if the User is not null 
     *
     * @param user the User to delete in database
     * @return a string telling whether the delete is succesful or not
     * @throws DataException if delete is not possible
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
     *
     * The User (customer) information will be updated in the database, 
     * if and only if the User is not null and the User object does 
     * not represents the same email as an User from the database.
     * 
     * @param user the User to update in database
     * @param email a String
     * @param name a String
     * @param oldpassword a String
     * @param newpassword a String
     * @param address a String
     * @param zipcode a String
     * @param phone a String
     * @return a String telling whether the update is succesful or not
     * @throws DataException if update is not possible
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
     * 
     * The User (employee) information will be updated in the database, 
     * if and only if the User is not null and the User object does 
     * not represents the same email as an User from the database.
     *
     * @param user the User to update in database
     * @param email a String
     * @param name a String
     * @param address a String
     * @param zipcode a String
     * @param phone a String
     * @return a String telling whether the update is succesful or not
     * @throws DataException if update is not possible
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
     * The password for a specific User will be updated in the database, 
     * if and only if the new password is not null and is longer than four digits 
     * 
     * @param user the User, which password to update in database
     * @param oldpassword a String
     * @param newpassword a String
     * @return a String telling whether the update is succesful or not
     * @throws DataException  if update is not possible
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
     * Retrieve a specific order from the database 
     *
     * @param order_id is used to detect the specific order
     * @return an object from the class Order
     * @throws DataException if retrieval not possible
     * @see DataLayer.DataFacade#getOrder(int)
     */
    public Order getOrder(int order_id) throws DataException
    {
        return db.getOrder(order_id);
    }

    /**
     * 
     * Retrieves all orders from the database
     *
     * @return a list of object from the class Order
     * @throws DataException if retrieval not possible
     * @see DataLayer.DataFacade#getOrders() 
     */
    public List<Order> getOrders() throws DataException
    {
        return db.getOrders();
    }

    /**
     * Retrieves a list of orders from a User with a specific email 
     *
     * @param email a String - is used to detect a list of orders made by a user with the specific email
     * @return a list of object from the class Order
     * @throws DataException if retrieval not possible
     * @see DataLayer.DataFacade#getOrdersByEmail(java.lang.String)
     */
    public List<Order> getOrdersByEmail(String email) throws DataException
    {
        return db.getOrdersByEmail(email);
    }

    /**
     * The wanted Carport is inserted in the database, thereafter the 
     * order is inserted in the database with the specific carport
     *
     * @param order the Order to insert in database
     * @return a String telling whether the insert is succesful or not
     * @throws DataException if insert not possible
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

    /**
     * Retrieves a list of orders and checks the date for each order
     * if the order is older than 3 years, the order is removed in the database.
     * 
     * @param orders a list of the object Order
     * @throws ParseException if parsing a String to Date not possible
     * @throws DataException if remove not possible
     */
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

    /**
     * 
     * The Order, carport, roof and possible shed is deleted in the database 
     * 
     * @param order the Order to delete in database
     * @throws DataException if delete is not possible
     */
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
     * Updates an Order with a specific order_id if it is shipped.
     * 
     * @param order the Order to update in database
     * @throws DataException if update is not possible
     * @see DataLayer.DataFacade#orderShipped(int) 
     */
    public void isShipped(Order order) throws DataException
    {
        String shipped = db.orderShipped(order.getOrder_id());
        order.setShipped(shipped);
    }

    /**
     * Updates salesprice in the database
     *
     * @param order_id an integer - is used to detect the specific Order
     * @param salesprice a double - the new value to insert on salesprice
     * @throws DataException if update is not possible
     * @see DataLayer.DataFacade#updateSalesPrice(int, double)
     */
    public void updateSalesPrice(int order_id, double salesprice) throws DataException
    {
        db.updateSalesPrice(order_id, salesprice);
    }

    /**
     * Update Status and Paid in the database for a specific Order
     * order
     *
     * @param order - the order, which status and paid, to update in database
     * @param status - an object of the enum class Status
     * @param paid - an object of the enum class Paid
     * @throws DataException if update is not possible
     * @see DataLayer.DataFacade#updateStatusAndPaid(int, FunctionLayer.Enum.Status, FunctionLayer.Enum.Paid)
     */
    public void updateStatusAndPaid(Order order, Status status, Paid paid) throws DataException
    {
        order.setPaid(paid);
        order.setStatus(status);
        db.updateStatusAndPaid(order.getOrder_id(), status, paid);
    }

    /**
     * The Carport's measurments will be updated in the database, 
     * if the measurments is not null - a shed can be removed and will
     * in that case be removed from the database, if a shed is wanted
     * a shed will be inserted in the database - when the carport is 
     * updated succesfully, the list of parts will reset and calculated again.
     *
     * @param carport the carport to update in database 
     * @param carport_depth an Integer
     * @param carport_width an Integer
     * @param rooftype an object of the class RoofType
     * @param roofslope an Integer
     * @param shed_width an Integer
     * @param shed_depth an Integer
     * @return a String telling whether the update is succesful or not
     * @throws DataException if insert is not possible
     * @see DataLayer.DataFacade#updateCarport(FunctionLayer.HelpingClasses.Carport)
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
     * Retrieves a list of RoofType from database 
     *
     * @return a list of objects from the class RoofType
     * @throws DataException if retrieval is not possible
     * @see DataLayer.DataFacade#getRoofs()
     */
    public List<RoofType> getRoofs() throws DataException
    {
        return db.getRoofs();
    }

    /**
     * Retrieves a list of RoofType from database and saves the rooftypes
     * which are sloped in a new list
     *
     * @return a list of slopedRoofs 
     * @throws DataException if retrieval is not possible
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
     * Retrieves a list of RoofType from database and saves the rooftypes
     * which are flat in a new list
     *
     * @return a list of flatRoofs 
     * @throws DataException if retrieval is not possible
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
     * Retrieve a RoofType from the database with a specific id
     *
     * @param typeId an Integer - to detect a specific RoofType
     * @return an object of the class RoofType
     * @throws DataException if retrieval is not possible
     * @see DataLayer.DataFacade#getRoof(int)
     */
    public RoofType getRoofTypeById(int typeId) throws DataException
    {
        return db.getRoof(typeId);
    }

    /**
     * A rooftype is inserted in the database if the rooftype object does
     * not represents the same name as a rooftype from the database or
     * if the selected materials to create a rooftype are not already used
     * on an existing rooftype in the database.
     *
     * @param rooftype the rooftype to insert in database 
     * @return a String telling whether the insert is succesful or not
     * @throws DataException if insert is not possible
     * @see DataLayer.DataFacade#addRoofType(FunctionLayer.HelpingClasses.RoofType)
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
     * Removes a specific rooftype from the database
     *
     * @param rooftype the rooftype to delete in database
     * @return a String telling whether the delete is succesful or not
     * @throws DataException if delete is not possible
     * @see DataLayer.DataFacade#deleteRoofType(FunctionLayer.HelpingClasses.RoofType)
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
     * A rooftype is updated in the database if the rooftype object does
     * not represents the same name as a rooftype from the database or
     * if the selected materials to create a rooftype are not already used
     * on an existing rooftype in the database.
     *
     * @param rooftype the rooftype to update in database
     * @param name a String
     * @param m1 an object of the class Material
     * @param m2 an object of the class Material
     * @return a String telling whether the update is succesful or not
     * @throws DataException if update is not possible
     * @see DataLayer.DataFacade#updateRoofType(FunctionLayer.HelpingClasses.RoofType)
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
            if (rooftype.getId() != r.getId() && name.toLowerCase().equals(r.getName().toLowerCase()))
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
     * Retrieves a specific material from database
     *
     * @param material_id an Integer - to detect a speicific material 
     * @return an object of the class Material
     * @throws DataException if retrieval is not possible
     * @see DataLayer.DataFacade#getMaterial(int) 
     */
    public Material getMaterial(int material_id) throws DataException
    {
        return db.getMaterial(material_id);
    }

    /**
     * Retrieves a list of materials from database
     *
     * @return a list of objects from the class Material
     * @throws DataException if retrieval is not possible
     * @see DataLayer.DataFacade#getMaterials() 
     */
    public List<Material> getAllMaterials() throws DataException
    {
        return db.getMaterials();
    }

    /**
     * A material is inserted in the database if the material object is not
     * null and does not represents the same name as a material from the database.
     *
     * @param newMaterial the material to update in database
     * @return a String telling whether the insert is succesful or not
     * @throws DataException if insert is not possible
     * @see DataLayer.DataFacade#addMaterial(FunctionLayer.HelpingClasses.Material)
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

        if (newMaterial.getPrice() <= 0)
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
     * Deletes a material from database
     *
     * @param material the material to delete from database
     * @return a String telling whether the delete is succesful or not
     * @throws DataException if delete is not possible
     * @see DataLayer.DataFacade#deleteMaterial(FunctionLayer.HelpingClasses.Material)
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
     * A material is updated in the database if the material object is not
     * null and does not represents the same name as a material from the database.
     *
     * @param material an object to update from the class Material
     * @param material_name a String
     * @param unit a String
     * @param material_class a String 
     * @param price a String 
     * @return a String telling whether the update is succesful or not
     * @throws DataException if update is not possible
     * @see DataLayer.DataFacade#updateMaterial(FunctionLayer.HelpingClasses.Material)
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

        if (price <= 0)
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
     * Calculates the materials for a specific carport and checks its measurments
     *
     * @param carport an object of the class Carport
     * @throws DataException if retrieval of carport is not possible
     * @see FunctionLayer.CalculateCarport#calcCarport(FunctionLayer.HelpingClasses.Carport) 
     */
    public void calcCarport(Carport carport) throws DataException 
    {
        if (carport.getWidth() <= 750 && carport.getDepth() <= 800)
        {
            c.calcCarport(carport);
        }
    }

    /**
     * Calculates the materials for a flat or sloped roof for a specific carport
     *
     * @param carport an object of the class Carport
     * @throws DataException if retrieval of carport is not possible
     * @see FunctionLayer.CalculateRoof#calculateFlatRoof(FunctionLayer.HelpingClasses.Carport) 
     * @see FunctionLayer.CalculateRoof#calculatePlatsmo(FunctionLayer.HelpingClasses.Carport) 
     * @see FunctionLayer.CalculateRoof#calculateSlopeRoof(FunctionLayer.HelpingClasses.Carport) 
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
     * Calculates the materials for a shed and checks its the measurments
     *
     * @param carport an object of the class Carport
     * @throws DataException if retrieval of carport is not possible
     * @see FunctionLayer.CalculateShed#calcShed(FunctionLayer.HelpingClasses.Carport) 
     */
    public void calcShed(Carport carport) throws DataException
    {
        if (carport.getShed().getWidth() <= carport.getWidth() - 30 && carport.getShed().getDepth() <= carport.getDepth() - 30)
        {
            cs.calcShed(carport);
        }
    }

    /**
     * Generates a drawing of a Carport seen from the front, side and top
     * - the drawing is in SVG format.
     *
     * @param carport an object of the class Carport, that is showed as a drawing
     * @return SVG format Drawing as a String
     * @see FunctionLayer.GenerateDrawing#drawCarport(FunctionLayer.HelpingClasses.Carport) 
     *
     */
    public String drawCarport(Carport carport)
    {
        return GD.drawCarport(carport);
    }
}
