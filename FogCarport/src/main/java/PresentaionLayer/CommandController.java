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
        commands.put("back", shop);
        commands.put("shop", new ShopCommand("shop.jsp"));
        commands.put("logout", new LogoutCommand("index.jsp"));
        commands.put("login", new LoginCommand("/Fog?command=shop", "/Fog?command=employee"));
        
        commands.put("newuser", new NewUserCommand("newuser.jsp"));
        commands.put("adduser", new AddUserCommand("index.jsp", "newuser.jsp", "nouser.jsp"));
        
        commands.put("placeorder", new PlaceOrderCommand("placeorder.jsp", "nouser.jsp"));
        commands.put("regretorder", new RegretOrderCommand("shop.jsp"));
        commands.put("noUser", new noUserCommand("drawing.jsp"));
        
        commands.put("drawing", new DrawingCommand("drawing.jsp")); 
        commands.put("customer", new CustomerCommand("customer.jsp"));
        commands.put("employee", new EmployeeCommand("employee.jsp"));
        
        commands.put("customerorder", new CustomerOrderCommand("customerorder.jsp"));
        commands.put("employeeorder", new EmployeeOrderCommand("employeeorder.jsp", "employee.jsp"));
        commands.put("shipped", new ShippedCommand("employeeorder.jsp"));
        commands.put("profit", new ProfitCommand("employeeorder.jsp"));
        commands.put("update", new UpdateCommand("employeeorder.jsp"));
        commands.put("viewpartlist", new ViewPartlistCommand("partlist.jsp", "customerpartlist.jsp"));
        commands.put("viewdrawing", new ViewDrawingCommand("viewdrawing.jsp"));
        
        commands.put("newemployee", new NewEmployeeCommand("addemployee.jsp", "employee.jsp"));
        commands.put("addemployee", new AddEmployeeCommand("addemployee.jsp"));
        commands.put("employeelist", new EmployeeListCommand("employeelist.jsp"));
        commands.put("employeeinfo", new EmployeeInfoCommand("employeeinfo.jsp"));
        
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
