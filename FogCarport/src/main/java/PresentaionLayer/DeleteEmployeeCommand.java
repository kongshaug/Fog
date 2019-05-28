/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentaionLayer;

import DataLayer.DataException;
import FunctionLayer.FunctionManager;
import FunctionLayer.HelpingClasses.User;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sofieamalielandt
 */
public class DeleteEmployeeCommand implements Command
{
    private String target;

    /**
     * Ininitializing target and denied for the command
     * 
     * @param target a String, reference to jsp
     */
    public DeleteEmployeeCommand(String target)
    {
        this.target = target;
    }

    /**
     * 
     * Retrieves employee as User from session, removes employee and a string 
     * is returned as repsonse - a list of users are retrieved from the database
     * and saved in session as users
     * - forwards to employeelist.jsp 
     * 
     * @param request a HttpServletRequest
     * @param response a HttpServletResponse
     * @param manager an instance of FunctionManager
     * @return target
     * @throws CommandException if an error occours
     * @throws DataException if retrievel was not possible 
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, FunctionManager manager) throws CommandException, DataException
    {
        HttpSession session = request.getSession();
        User employee = (User) session.getAttribute("employee");

        session.removeAttribute("employee");

        String message = manager.removeUser(employee);
        request.setAttribute("message", message);
        List<User> users = manager.getEmployeesAndAdmins();
        session.setAttribute("users", users);

        return target;
    }
}
