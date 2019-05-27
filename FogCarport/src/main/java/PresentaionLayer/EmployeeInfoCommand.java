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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author aamandajuhl
 */
public class EmployeeInfoCommand implements Command
{
    private String target;
    private String denied;

    /**
     * Ininitializing target and denied for the command
     * 
     * @param target a String, reference to jsp
     * @param denied a String, reference to jsp
     */
    public EmployeeInfoCommand(String target, String denied)
    {
        this.target = target;
        this.denied = denied;
    }

    /**
     * 
     * Retrieves the parameter selected and user from session, checks the role
     * of the user and retrieves the employee from database and saves as employee
     * on session - forwards to employeeinfo.jsp
     * If access is denied, forwards to employeelist.jsp 
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
        int user_id = Integer.parseInt(request.getParameter("selected"));
        User user = (User) session.getAttribute("user");

        if (user.getRole().equals(Role.ADMIN))
        {
            User employee = manager.getUser(user_id);
            session.setAttribute("employee", employee);

            return target;

        } else
        {
            request.setAttribute("message", "Adgang nægtet");
            return denied;
        }
    }
}
