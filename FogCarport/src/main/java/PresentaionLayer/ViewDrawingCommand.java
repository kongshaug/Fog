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
public class ViewDrawingCommand implements Command
{
    private String target;

    /**
     * Ininitializing target and denied for the command
     * 
     * @param target a String, reference to jsp
     */
    public ViewDrawingCommand(String target)
    {
        this.target = target;
    }

    /**
     * 
     * Retrieves attribute order from session and the carport drawing is generated based on
     * the details from the order - the carport is saved on session, while carportDrawing is 
     * saved on request - forward to viewdrawing.jsp
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

        String carportDrawing = manager.drawCarport(order.getCarport());
        session.setAttribute("carport", order.getCarport());
        request.setAttribute("carportDrawing", carportDrawing);

        return target;
    }

}
