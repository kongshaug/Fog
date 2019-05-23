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

    public LogoutCommand(String target)
    {
        this.target = target;
    }

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
                session.removeAttribute("materials");
                session.removeAttribute("marterial");
                session.removeAttribute("material_classes");
                session.removeAttribute("employee");
                session.removeAttribute("users");
                session.removeAttribute("rooftype");
                session.removeAttribute("rooftypes");
            }

            session.removeAttribute("order");
            session.removeAttribute("carport");
            session.removeAttribute("orders");
            session.removeAttribute("roofDrawing");
            session.removeAttribute("slopedroofs");
            session.removeAttribute("flatroofs");
            session.removeAttribute("user");

            return target;
        }
    }

}
