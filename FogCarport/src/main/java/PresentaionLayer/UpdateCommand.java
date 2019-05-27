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

    /**
     * Ininitializing target and denied for the command
     * 
     * @param target a String, reference to jsp
     */
    public UpdateCommand(String target)
    {
        this.target = target;
    }

    /**
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
        Order order = (Order) session.getAttribute("order");
        String change_paid = request.getParameter("paid");
        String change_status = request.getParameter("status");

        Paid paid = Paid.valueOf(change_paid);
        Status status = Status.valueOf(change_status);

        manager.updateStatusAndPaid(order, status, paid);

        return target;
    }
}
