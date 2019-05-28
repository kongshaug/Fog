/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentaionLayer;

import DataLayer.DataException;
import FunctionLayer.FunctionManager;
import FunctionLayer.HelpingClasses.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sofieamalielandt
 */
public class PasswordCommand implements Command
{
    private String target;
    
    /**
     * Ininitializing target and denied for the command
     * 
     * @param target a String, reference to jsp
     */
    public PasswordCommand(String target)
    {
        this.target = target;
    }

    /**
     * 
     * Retrieves attribute user from session, oldpassword and newpassword from request,
     * updates the password of the object User in database, a String is returned in response
     * - forward to employeeprofile.jsp
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
        User user = (User) session.getAttribute("user");

        String oldpassword = request.getParameter("oldpassword");
        String newpassword = request.getParameter("newpassword");
        
        String message = manager.updatePassword(user, oldpassword, newpassword);

        request.setAttribute("message", message);
        return target;
    }
}
