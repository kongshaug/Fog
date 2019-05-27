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

        commands.put("newuser", new TargetCommand("newuser.jsp"));
        commands.put("adduser", new AddUserCommand("index.jsp", "newuser.jsp", "nouser.jsp"));

        commands.put("placeorder", new PlaceOrderCommand("placeorder.jsp", "nouser.jsp"));
        commands.put("regretorder", new RegretOrderCommand("shop.jsp"));
        commands.put("noUser", new noUserCommand("drawing.jsp"));
        commands.put("drawing", new DrawingCommand("drawing.jsp"));

        commands.put("shipped", new ShippedCommand("employeeorder.jsp"));
        commands.put("profit", new ProfitCommand("employeeorder.jsp"));
        commands.put("update", new UpdateCommand("employeeorder.jsp"));
        commands.put("updatecarport", new UpdateCarportCommand("employeeorder.jsp"));
        commands.put("viewpartlist", new ViewPartlistCommand("partlist.jsp", "customerpartlist.jsp"));
        commands.put("viewdrawing", new ViewDrawingCommand("viewdrawing.jsp"));

        commands.put("employee", new EmployeeCommand("employee.jsp"));
        commands.put("employeeorder", new EmployeeOrderCommand("employeeorder.jsp", "employee.jsp"));
        commands.put("employeelist", new EmployeeListCommand("employeelist.jsp", "employee.jsp"));
        commands.put("employeeinfo", new EmployeeInfoCommand("employeeinfo.jsp", "employeelist.jsp"));
        commands.put("employeeupdate", new EmployeeUpdateCommand("employeeinfo.jsp", "employee.jsp"));
        commands.put("deleteemployee", new DeleteEmployeeCommand("employeelist.jsp"));
        commands.put("newemployee", new NewEmployeeCommand("addemployee.jsp", "employee.jsp"));
        commands.put("addemployee", new AddEmployeeCommand("addemployee.jsp", "employee.jsp"));
        commands.put("employeeprofile", new TargetCommand("employeeprofile.jsp"));
        commands.put("employeeupdatepassword", new PasswordCommand("employeeprofile.jsp"));

        commands.put("customer", new CustomerCommand("customer.jsp"));
        commands.put("customerorder", new CustomerOrderCommand("customerorder.jsp"));
        commands.put("customerinfo", new TargetCommand("customerinfo.jsp"));
        commands.put("customerupdate", new CustomerUpdateCommand("customerinfo.jsp"));
        commands.put("deletecustomer", new DeleteCustomerCommand("index.jsp"));

        commands.put("materials", new MaterialsCommand("materials.jsp", "employee.jsp"));
        commands.put("material", new MaterialCommand("material.jsp"));
        commands.put("materialupdate", new UpdateMaterialCommand("material.jsp"));
        commands.put("addmaterial", new AddMaterialCommand("materials.jsp", "addmaterial.jsp"));
        commands.put("materialadd", new MaterialAddCommand("addmaterial.jsp"));
        commands.put("deletematerial", new DeleteMaterialCommand("materials.jsp"));

        commands.put("addrooftype", new TargetCommand("addrooftype.jsp"));
        commands.put("rooftypeadd", new AddRoofTypeCommand("materials.jsp", "addrooftype.jsp"));
        commands.put("rooftypes", new RooftypesCommand("rooftypes.jsp", "employee.jsp"));
        commands.put("rooftype", new RooftypeInfoCommand("rooftype.jsp"));
        commands.put("updaterooftype", new UpdateRooftypeCommand("rooftype.jsp"));
        commands.put("deleterooftype", new DeleteRooftypeCommand("rooftypes.jsp"));
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
