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
 * @author sofieamalielandt
 */
public class ViewPartlistCommand implements Command
{
    private String emptarget;
    private String custarget;

    public ViewPartlistCommand(String emptarget, String custarget)
    {
        this.emptarget = emptarget;
        this.custarget = custarget;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, FunctionManager manager) throws CommandException, DataException
    {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user.getRole().equals(Role.ADMIN) || user.getRole().equals(Role.EMPLOYEE))
        {
            return emptarget;
        } else
        {

            return custarget;
        }
    }

}
