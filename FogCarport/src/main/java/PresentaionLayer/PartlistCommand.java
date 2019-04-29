/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentaionLayer;

import DataLayer.DataException;
import FunctionLayer.Carport;
import FunctionLayer.FunctionManager;
import FunctionLayer.Roof;
import FunctionLayer.RoofType;
import FunctionLayer.Shed;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author aamandajuhl
 */
public class PartlistCommand implements Command
{

    private String target;

    public PartlistCommand(String target)
    {
        this.target = target;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, FunctionManager manager) throws CommandException, DataException
    {
        HttpSession session = request.getSession();
        int depth = Integer.parseInt(request.getParameter("depth"));
        int width = Integer.parseInt(request.getParameter("width"));
        String roofkind = request.getParameter("roof");
        int typeId = Integer.parseInt(request.getParameter("type"));
        int slope = 0;
        String slopeGet = request.getParameter("slope");
        if (slopeGet != null)
        {
            slope = Integer.parseInt(slopeGet);
        }

        int shedDepth = Integer.parseInt(request.getParameter("shedDepth"));
        int shedWidth = Integer.parseInt(request.getParameter("shedWidth"));
        String shedOrNot = request.getParameter("shed");

        if ("shed".equals(shedOrNot))
        {
            if (shedDepth > depth - 30 || shedWidth > width - 30)
            {
                session.setAttribute("errorMessageShed", "Skuret skal være mindst 30 cm smallere og kortere end carporten. "
                        + "Målene for dit skur er lige nu  " + (shedDepth - (depth - 30)) + " for dybe og " + (shedWidth - (width - 30)) + " for bred. Prøv igen.");

                return "shop.jsp";
            }
        }

        if (depth != 0 && width != 0 && typeId != 0 && roofkind != null)
        {
            RoofType type = manager.getRoofType(roofkind, typeId);
            Roof roof = new Roof(slope, type);
            Carport carport = null;

            if (shedOrNot == null)
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

            session.setAttribute("carport", carport);

            return target;
        } else
        {
            return target;
        }

    }

}
