/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentaionLayer;

import DataLayer.DataException;
import FunctionLayer.FunctionManager;
import FunctionLayer.HelpingClasses.Order;
import FunctionLayer.HelpingClasses.RoofType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sofieamalielandt
 */
public class UpdateCarportCommand implements Command
{
    private String target;

    /**
     * Ininitializing target and denied for the command
     * 
     * @param target a String, reference to jsp
     */
    public UpdateCarportCommand(String target)
    {
        this.target = target;
    }

    /**
     * 
     * Retrieves the order from session and the parameters roof, type, slope, depth, width
     * shedDepth, shedWidth and shed from request, if input invalid a String is returned as
     * response and forward to employeeorder.jsp
     * if input is valid - retrieves rooftype from database and generates a roof, the 
     * carport and salesprice are recalculated and a String is retuned as response 
     * - forward to employeeorder.jsp
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
        
        String rooftype = request.getParameter("roof");
        String typeId = request.getParameter("type");
        String slope_str = request.getParameter("slope");
        int slope;

        int depth = Integer.parseInt(request.getParameter("depth"));
        int width = Integer.parseInt(request.getParameter("width"));

        int shedDepth = Integer.parseInt(request.getParameter("shedDepth"));
        int shedWidth = Integer.parseInt(request.getParameter("shedWidth"));
        String shedOrNot = request.getParameter("shed");

        if (typeId == null)
        {
            request.setAttribute("errormessage", "Vælg venligst tagtype!");
            return target;
        }
        if (rooftype.equals("sloped") && slope_str.equals("0"))
        {
            request.setAttribute("errormessage", "Vælg venligst hældning!");
            return target;
        }

        if ("Med skur".equals(shedOrNot))
        {
            if (shedDepth > depth - 30 || shedWidth > width - 30)
            {
                request.setAttribute("errormessage", "Skuret skal være mindst 30 cm smallere og kortere end carporten. "
                        + "Målene for dit skur er lige nu  " + (shedDepth - (depth - 30)) + " for dybe og " + (shedWidth - (width - 30)) + " for bred. Prøv igen.");

                return target;
            }
        }

        if (depth != 0 && width != 0)
        {
            RoofType type = manager.getRoofTypeById(Integer.parseInt(typeId));

            if ("Uden skur".equals(shedOrNot))
            {
                shedDepth = 0;
                shedWidth = 0;
            }
            if (slope_str == null)
            {
                slope = 0;

            } else
            {
                slope = Integer.parseInt(slope_str);
            }
            
            String res = manager.updateCarport(order.getCarport(), depth, width, type, slope, shedWidth, shedDepth);
            order.calcSalesPrice();
            request.setAttribute("errormessage", res);
            session.setAttribute("order", order);

            return target;

        } else
        {
            request.setAttribute("errormessage", "Vælg venligst dybte og bredde på carporten!");
            return target;
        }
    }
}
