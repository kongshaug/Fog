/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentaionLayer;

import DataLayer.DataException;
import FunctionLayer.FunctionManager;
import FunctionLayer.HelpingClasses.Order;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sofieamalielandt
 */
public class CustomerOrderCommand implements Command
{
    private String target;

    /**
     * Ininitializing target and denied for the command
     * 
     * @param target a String, reference to jsp
     */
    public CustomerOrderCommand(String target)
    {
        this.target = target;
    }

    /**
     * 
     * Retrieves parameter selected and uses this parameter as order_id for
     * retrieval of an order for a specific User, and calculates the Carport -
     * this order is saved in session as order, and forwards to customerorder.jsp.
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
        int order_id = Integer.parseInt(request.getParameter("selected"));
        Order order = manager.getOrder(order_id);

        if (order.getCarport().getShed() != null)
        {
            manager.calcShed(order.getCarport());
        }

        manager.calcCarport(order.getCarport());
        manager.calcRoof(order.getCarport());

        session.setAttribute("order", order);

        return target;
    }

}
