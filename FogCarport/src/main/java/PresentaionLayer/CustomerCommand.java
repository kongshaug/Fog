/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentaionLayer;

import DataLayer.DataException;
import FunctionLayer.FunctionManager;
import FunctionLayer.HelpingClasses.Order;
import FunctionLayer.HelpingClasses.User;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sofieamalielandt
 */
public class CustomerCommand implements Command
{
    private String target;

    /**
     * Ininitializing target and denied for the command
     * 
     * @param target a String, reference to jsp
     */
    public CustomerCommand(String target)
    {
        this.target = target;
    }

    /**
     * 
     * Retrives the attribute User from session, a List of objects from the class
     * Order with the user equal to the attribute user attached is also
     * retrieved - forwards to customer.jsp
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

        if (session.getAttribute("orders") == null)
        {
            List<Order> orders = manager.getOrdersByEmail(user.getEmail());
            request.setAttribute("orders", orders);
        }

        return target;
    }

}
