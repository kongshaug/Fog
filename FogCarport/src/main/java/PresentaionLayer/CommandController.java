/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentaionLayer;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author sofieamalielandt
 */
public class CommandController
{

    private static CommandController instance = null;
    private final Map<String, Command> commands = new HashMap();

    private CommandController()
    {
        Command shop = new ShopCommand("shop.jsp");
        commands.put("shop", new ShopCommand("shop.jsp"));
        commands.put("calculate", new PartlistCommand("partlist.jsp")); //skal laves om
        commands.put("login", new LoginCommand("/Fog?command=shop", "/Fog?command=employee"));
        commands.put("newuser", new NewUserCommand("newuser.jsp"));
        commands.put("adduser", new AddUserCommand("index.jsp", "newuser.jsp", "nouser.jsp"));
        commands.put("back", shop);
        commands.put("placeorder", new PlaceOrderCommand("placeorder.jsp", "nouser.jsp"));
        commands.put("regretorder", new RegretOrderCommand("shop.jsp"));
        commands.put("logout", new LogoutCommand("index.jsp"));
        commands.put("noUser", new noUserCommand("partlist.jsp")); // skal laves om
        commands.put("employee", new EmployeeCommand("employee.jsp"));
        commands.put("customer", new CustomerCommand("customer.jsp"));
        commands.put("drawing", new DrawingCommand("drawing.jsp")); // skal laves om 
        commands.put("employeeorder", new EmployeeOrderCommand("employeeorder.jsp"));
        commands.put("profit", new ProfitCommand("employeeorder.jsp"));
        commands.put("update", new UpdateCommand("employeeorder.jsp"));
        commands.put("viewpartlist", new ViewPartlistCommand("partlist.jsp"));
    }

    public static synchronized Command from(String path)
    {
        if (path == null)
        {
            path = "login";
        }
        if (instance == null)
        {
            instance = new CommandController();
        }
        return instance.commands.get(path);
    }

}
