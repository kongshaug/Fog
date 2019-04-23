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
      //Command shop = new ShopCommand("shop.jsp");
      commands.put("shop", new ShopCommand("shop.jsp"));
      commands.put("calculate", new PartlistCommand("partlist.jsp"));
//      commands.put("shoppingcart", new ShoppingcartCommand("shoppingcart.jsp"));
//      commands.put("placeorder", new PlaceOrderCommand("placeorder.jsp"));
//      commands.put("back", shop);
//      commands.put("logout", new LogoutCommand("index.jsp"));
//      commands.put("customer", new CustomerCommand("customer.jsp"));
//      commands.put("employee", new EmployeeCommand("employee.jsp"));
//      commands.put("customerorder", new CustomerOrderCommand("customerorder.jsp"));
//      commands.put("employeeorder", new EmployeeOrderCommand("employeeorder.jsp"));
//      commands.put("shipped", new ShippedCommand("employeeorder.jsp"));
//      
      
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
