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
    
    public PasswordCommand(String target)
    {
        this.target = target;
    }

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
