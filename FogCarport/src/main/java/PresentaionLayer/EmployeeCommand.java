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
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            try
            {
                List<Order> orders = manager.getOrders();
                manager.GDPRCheck(orders);
                session.setAttribute("orders", orders);

            } catch (ParseException ex)
            {
                throw new CommandException(ex.getMessage());
            }

        } else
        {
            List<Order> orders = manager.getOrdersByEmail(search);
            session.setAttribute("orders", orders);
        }
        return target;
    }
}
