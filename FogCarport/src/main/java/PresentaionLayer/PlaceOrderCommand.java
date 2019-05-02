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

    public PlaceOrderCommand(String target)
    {
        this.target = target;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, FunctionManager manager) throws CommandException, DataException
    {
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");
        Carport carport = (Carport) session.getAttribute("carport");
        Order order = new Order(user, carport);
        session.setAttribute("order", order);

        String message = manager.placeOrder(order);
        request.setAttribute("message", message);

        if (message.equals("Tak for din forespørgsel. Vi vil behandle den hurtigst muligt"))
        {
            session.removeAttribute("carport");
        }

        return target;
    }

}
