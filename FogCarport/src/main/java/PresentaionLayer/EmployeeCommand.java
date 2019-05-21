/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentaionLayer;

import DataLayer.DataException;
import FunctionLayer.FunctionManager;
import FunctionLayer.HelpingClasses.Order;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sofieamalielandt
 */
public class EmployeeCommand implements Command
{
    private String target;

    public EmployeeCommand(String target)
    {
        this.target = target;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, FunctionManager manager) throws CommandException, DataException
    {
        HttpSession session = request.getSession();
        String search = request.getParameter("search");

        if (search == null || search.isEmpty())
        {
            List<Order> orders = manager.getOrders();
            session.setAttribute("orders", orders);

        } else
        {
            List<Order> orders = manager.getOrdersByEmail(search);
            session.setAttribute("orders", orders);
        }
        return target;
    }
}
