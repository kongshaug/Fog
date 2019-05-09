/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentaionLayer;

import DataLayer.DataException;
import FunctionLayer.Enum.Paid;
import FunctionLayer.Enum.Status;
import FunctionLayer.FunctionManager;
import FunctionLayer.HelpingClasses.Order;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author aamandajuhl
 */
public class UpdateCommand implements Command
{

    private String target;

    public UpdateCommand(String target)
    {
        this.target = target;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, FunctionManager manager) throws CommandException, DataException
    {

        HttpSession session = request.getSession();
        Order order = (Order) session.getAttribute("order");
        String change_paid = request.getParameter("paid");
        String change_status = request.getParameter("status");
        
        Paid paid = Paid.valueOf(change_paid);
        Status status = Status.valueOf(change_status);
        
        order.setPaid(paid);
        order.setStatus(status);
        manager.updateStatusAndPaid(order.getOrder_id(), status, paid);


        return target;

    }

}
