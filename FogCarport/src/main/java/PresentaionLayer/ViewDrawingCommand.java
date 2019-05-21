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

    public ViewDrawingCommand(String target)
    {
        this.target = target;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, FunctionManager manager) throws CommandException, DataException
    {
        HttpSession session = request.getSession();
        Order order = (Order) session.getAttribute("order");

        String roofDrawing = manager.drawingOfRoof(order.getCarport());
        session.setAttribute("carport", order.getCarport());
        session.setAttribute("roofDrawing", roofDrawing);

        return target;
    }

}
