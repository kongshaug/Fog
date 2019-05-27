/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentaionLayer;

import DataLayer.DataException;
import FunctionLayer.HelpingClasses.Carport;
import FunctionLayer.FunctionManager;
import FunctionLayer.HelpingClasses.Roof;
import FunctionLayer.HelpingClasses.RoofType;
import FunctionLayer.HelpingClasses.Shed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author aamandajuhl
 */
public class DrawingCommand implements Command
{
    private String target;

    public DrawingCommand(String target)
    {
        this.target = target;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, FunctionManager manager) throws CommandException, DataException
    {
        HttpSession session = request.getSession();
        int depth = Integer.parseInt(request.getParameter("depth"));
        int width = Integer.parseInt(request.getParameter("width"));

        String rooftype = request.getParameter("roof");
        String typeId = request.getParameter("type");
        String slope = request.getParameter("slope");

        int shedDepth = Integer.parseInt(request.getParameter("shedDepth"));
        int shedWidth = Integer.parseInt(request.getParameter("shedWidth"));
        String shedOrNot = request.getParameter("shed");

        if (typeId == null)
        {
            request.setAttribute("errormessage", "Vælg venligst tagtype!");
            return "shop.jsp";
        }
        if (rooftype.equals("sloped") && slope == null)
        {
            request.setAttribute("errormessage", "Vælg venligst hældning!");
            return "shop.jsp";
        }
        if (shedOrNot == null)
        {
            request.setAttribute("errormessage", "Vælg venligst om der ønskes skur eller ej!");
            return "shop.jsp";
        }

        if ("Med skur".equals(shedOrNot))
        {
            if (shedDepth > depth - 30 || shedWidth > width - 30)
            {
                request.setAttribute("errormessage", "Skuret skal være mindst 30 cm smallere og kortere end carporten. "
                        + "Målene for dit skur er lige nu  " + (shedDepth - (depth - 30)) + " for dybe og " + (shedWidth - (width - 30)) + " for bred. Prøv igen.");

                return "shop.jsp";
            }
        }

        if (depth != 0 && width != 0)
        {
            Carport carport = null;
            Roof roof = null;
            RoofType type = manager.getRoofTypeById(Integer.parseInt(typeId));

            if (rooftype.equals("flat"))
            {
                roof = new Roof(0, type);
            } else
            {
                roof = new Roof(Integer.parseInt(slope), type);
            }

            if ("Uden skur".equals(shedOrNot))
            {
                carport = new Carport(width, depth, roof);

            } else if (shedDepth != 0 && shedWidth != 0)
            {
                Shed shed = new Shed(shedDepth, shedWidth);
                carport = new Carport(width, depth, roof, shed);
                manager.calcShed(carport);
            }

            manager.calcCarport(carport);
            manager.calcRoof(carport);
            String carportDrawing = manager.drawCarport(carport);

            session.setAttribute("carport", carport);
            session.setAttribute("carportDrawing", carportDrawing);

            return target;

        } else
        {
            return target;
        }
    }
}
