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
public class LogoutCommand implements Command
{

    private String target;

    /**
     * Ininitializing target and denied for the command
     *
     * @param target a String, reference to jsp
     */
    public LogoutCommand(String target)
    {
        this.target = target;
    }

    /**
     * Retrieves attribute user from session, and 
     * removes attributes material, materials_classes, employee, 
     * rooftype, slopedroofs, flatroofs, order, carport and user 
     * from session - forwards to index.jsp
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

        if (user == null)
        {
            return target;
        } else
        {
            if (user.getRole().equals(Role.EMPLOYEE) || user.getRole().equals(Role.ADMIN))
            {
                session.removeAttribute("marterial");
                session.removeAttribute("material_classes");
                session.removeAttribute("employee");
                session.removeAttribute("rooftype");
                session.removeAttribute("slopedroofs");
                session.removeAttribute("flatroofs");
            }

            session.removeAttribute("order");
            session.removeAttribute("carport");
            session.removeAttribute("user");

            return target;
        }
    }

}
