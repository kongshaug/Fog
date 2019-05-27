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

    /**
     * Ininitializing target and denied for the command
     * 
     * @param emptarget a String, reference to jsp
     * @param custarget a String, reference to jsp
     */
    public ViewPartlistCommand(String emptarget, String custarget)
    {
        this.emptarget = emptarget;
        this.custarget = custarget;
    }

    /**
     * 
     * Retrieves attribute user from session 
     * - forward to partlist.jsp if user is admin or employee
     * - forward to customerpartlist.jsp if user is customer
     * 
     * @param request a HttpServletRequest
     * @param response a HttpServletResponse
     * @param manager an instance of FunctionManager
     * @return emptarget or custarget
     * @throws CommandException if an error occours
     * @throws DataException if retrievel was not possible 
     */
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
