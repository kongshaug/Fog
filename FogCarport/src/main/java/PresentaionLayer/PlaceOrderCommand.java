/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentaionLayer;

import DataLayer.DataException;
import FunctionLayer.FunctionManager;
import FunctionLayer.HelpingClasses.Carport;
import FunctionLayer.HelpingClasses.Order;
import FunctionLayer.HelpingClasses.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author aamandajuhl
 */
public class PlaceOrderCommand implements Command
{
    private String target;
    private String noUser;

    /**
     * Ininitializing target and denied for the command
     * 
     * @param target a String, reference to jsp
     * @param noUser a String, reference to jsp
     */
    public PlaceOrderCommand(String target, String noUser)
    {
        this.target = target;
        this.noUser = noUser;
    }

    /**
     * 
     * Retrieves attributes user and carport from session creates object Order
     * and adds object to the database, a string is returned as repsonse -
     * forwards to placeorder.jsp 
     * if nouser - forward to nouser.jsp
     * 
     * @param request a HttpServletRequest
     * @param response a HttpServletResponse
     * @param manager an instance of FunctionManager
     * @return target or noUser
     * @throws CommandException if an error occours
     * @throws DataException if retrievel was not possible 
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, FunctionManager manager) throws CommandException, DataException
    {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Carport carport = (Carport) session.getAttribute("carport");

        if (user == null)
        {
            return noUser;
        }

        Order order = new Order(user, carport);
        String message = manager.placeOrder(order);
        request.setAttribute("message", message);

        if (message.equals("Tak for din forespørgsel. Vi vil behandle den hurtigst muligt"))
        {
            session.removeAttribute("carport");
        }

        return target;
    }
}
