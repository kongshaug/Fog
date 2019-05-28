/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentaionLayer;

import DataLayer.DataException;
import FunctionLayer.FunctionManager;
import FunctionLayer.HelpingClasses.Order;
import java.text.ParseException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sofieamalielandt
 */
public class EmployeeCommand implements Command
{

    private String target;

    /**
     * Ininitializing target and denied for the command
     * 
     * @param target a String, reference to jsp
     */
    public EmployeeCommand(String target)
    {
        this.target = target;
    }

    /**
     * Retrieves the parameter search from request, if search is empty a list
     * of orders is retrieved from database and saved on request, otherwise a
     * list of orders by an employee email is retrieved and saved on request
     * - forward to employee.jsp
     * 
     * Retrives the attribute User from session, a List of objects from the class
     * Order with the user equal to the attribute user attached is also
     * retrieved - forwards to customer.jsp
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
        String search = request.getParameter("search");
        
        if (search == null || search.isEmpty())
        {
            try
            {
                List<Order> orders = manager.getOrders();
                manager.GDPRCheck(orders);
                request.setAttribute("orders", orders);

            } catch (ParseException ex)
            {
                throw new CommandException(ex.getMessage());
            }

        } else
        {
            List<Order> orders = manager.getOrdersByEmail(search);
            request.setAttribute("orders", orders);
        }
        return target;
    }
}
