/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentaionLayer;

import DataLayer.DataException;
import FunctionLayer.Enum.Role;
import FunctionLayer.FunctionManager;
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
    private String denied;

    /**
     * Ininitializing target and denied for the command
     * 
     * @param target a String, reference to jsp
     * @param denied a String, reference to jsp
     */
    public EmployeeListCommand(String target, String denied)
    {
        this.target = target;
        this.denied = denied;
    }

    /**
     * 
     * Retrieves the parameter search from request, if search is empty a list
     * of employees as users is retrieved from database and saved on request, otherwise a
     * list of users by an employee email is retrieved and saved on request 
     * - forwards to employeelist.jsp
     * If access is denied, forwards to employee.jsp 
     * 
     * @param request a HttpServletRequest
     * @param response a HttpServletResponse
     * @param manager an instance of FunctionManager
     * @return target or denied
     * @throws CommandException if an error occours
     * @throws DataException if retrievel was not possible 
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, FunctionManager manager) throws CommandException, DataException
    {
        HttpSession session = request.getSession();
        String password = request.getParameter("password");
        User user = (User) session.getAttribute("user");

        if (password.equals(user.getPassword()) && user.getRole().equals(Role.ADMIN) || password.equals(user.getPassword()) && user.getRole().equals(Role.EMPLOYEE))
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

        } else
        {
            request.setAttribute("message", "Adgang nægtet");
            return denied;
        }

    }

}
