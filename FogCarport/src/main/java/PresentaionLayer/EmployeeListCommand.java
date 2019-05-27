/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentaionLayer;

import DataLayer.DataException;
import FunctionLayer.FunctionManager;
import FunctionLayer.HelpingClasses.Order;
import FunctionLayer.HelpingClasses.User;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author aamandajuhl
 */
public class EmployeeListCommand implements Command
{
    private String target;

    public EmployeeListCommand(String target)
    {
        this.target = target;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, FunctionManager manager) throws CommandException, DataException
    {
        String search = request.getParameter("search");

        if (search == null || search.isEmpty())
        {
            List<User> users = manager.getEmployeesAndAdmins();
            request.setAttribute("users", users);

        } else
        {
            List<User> users = new ArrayList<>();
            User employee = (User) manager.getEmployeeByEmail(search);
            users.add(employee);
            request.setAttribute("users", users);
        }
        return target;
    }

}
