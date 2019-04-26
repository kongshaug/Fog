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
        List<RoofType> rooftypes = null;
        RoofType type = null;
        int slope = 0;

        if (roofkind.equals("flat"))
        {
            rooftypes = manager.getFlatRoofs();
            for (RoofType rooftype : rooftypes)
            {
                if (rooftype.getId() == typeId)
                {
                    type = rooftype;
                }
            }
        } else
        {
            slope = Integer.parseInt(request.getParameter("slope"));
            rooftypes = manager.getSlopedRoofs();
            for (RoofType rooftype : rooftypes)
            {
                if (rooftype.getId() == typeId)
                {
                    type = rooftype;
                }
            }
        }

        Roof roof = new Roof(slope, type);
        Carport carport = new Carport(width, depth, roof);

        manager.calCarport(carport);
        if (carport.getRoof().getType().getRoof_class().equals("flat"))
        {
            manager.calFlatroof(carport);
        } else
        {
            manager.calSlopeRoof(carport);
        }

        session.setAttribute("carport", carport);

        return target;
    }

}
