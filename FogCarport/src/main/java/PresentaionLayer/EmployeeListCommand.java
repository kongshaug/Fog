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
        HttpSession session = request.getSession();
        String search = request.getParameter("search");

        if (search == null || search.isEmpty())
        {
            List<User> users = manager.getUsers();
            session.setAttribute("users", users);

        } else
        {
            List<User> users = (List<User>) manager.getEmployeeByEmail(search);
            session.setAttribute("users", users);

        }
        return target;
    }
    
}
