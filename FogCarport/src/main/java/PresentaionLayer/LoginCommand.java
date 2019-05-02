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

    public LoginCommand(String CustomerTarget, String EmployeeTarget)
    {
        this.CustomerTarget = CustomerTarget;
        this.EmployeeTarget = EmployeeTarget;
    }

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
