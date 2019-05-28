/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentaionLayer;

import DataLayer.DataException;
import FunctionLayer.FunctionManager;
import FunctionLayer.Enum.Role;
import FunctionLayer.HelpingClasses.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author aamandajuhl
 */
public class LoginCommand implements Command
{

    String CustomerTarget;
    String EmployeeTarget;

    /**
     * Ininitializing target and denied for the command
     * 
     * @param CustomerTarget a String, reference to jsp
     * @param EmployeeTarget a String, reference to jsp
     */
    public LoginCommand(String CustomerTarget, String EmployeeTarget)
    {
        this.CustomerTarget = CustomerTarget;
        this.EmployeeTarget = EmployeeTarget;
    }

    /**
     * 
     * Retrieves parameters email and password, the user is retrieved from database
     * and forwards to either shop.jsp as customer or employee.jsp as employee 
     * 
     * @param request a HttpServletRequest
     * @param response a HttpServletResponse
     * @param manager an instance of FunctionManager
     * @return CustomerTarget or EmpoloyeeTarget
     * @throws CommandException if an error occours
     * @throws DataException if retrievel was not possible 
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, FunctionManager manager) throws CommandException, DataException
    {
        HttpSession session = request.getSession();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = manager.login(email, password);

        if (user != null)
        {
            session.setAttribute("user", user);
            if (user.getRole() == Role.CUSTOMER)
            {
                return CustomerTarget;
            } else
            {
                return EmployeeTarget;
            }
        }
        request.setAttribute("message", "Forkert email eller adgangskode!");

        return "index.jsp";
    }
}
