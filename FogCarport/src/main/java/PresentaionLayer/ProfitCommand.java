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
public class ProfitCommand implements Command
{
    private String target;

    public ProfitCommand(String target)
    {
        this.target = target;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, FunctionManager manager) throws CommandException, DataException
    {
        HttpSession session = request.getSession();
        Order order = (Order) session.getAttribute("order");
        double salesprice = Double.parseDouble(request.getParameter("salesprice"));
        int update = Integer.parseInt(request.getParameter("update"));

        if (salesprice >= (order.getCarport().getTotal_price() * 1.10))
        {
            order.setSales_price(salesprice);
            if (update == 1)
            {
                manager.updateSalesPrice(order.getOrder_id(), order.getSales_price());
            }
        } else
        {
            
            String errormessage = "Der skal minimum være en profit på 10%";
            request.setAttribute("pricemessage", errormessage);
        }

        return target;
    }
}
