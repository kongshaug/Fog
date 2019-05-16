/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentaionLayer;

import DataLayer.DataException;
import FunctionLayer.FunctionManager;
import FunctionLayer.HelpingClasses.RoofType;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sofieamalielandt
 */
public class DeleteRooftypeCommand implements Command
{
    private String target;
    
    public DeleteRooftypeCommand(String target)
    {
        this.target = target;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, FunctionManager manager) throws CommandException, DataException
    {
        HttpSession session = request.getSession();

        String rooftype_str = request.getParameter("rooftype");

        if (rooftype_str == null)
        {
            request.setAttribute("message", "Vælg venligst et tagtype");
            return "rooftypes.jsp";
        }

        int material_id = Integer.parseInt(rooftype_str);
        RoofType Rooftype = manager.getRoofTypeById(material_id);

        String message = manager.deleteRoofType(Rooftype);
        request.setAttribute("message", message);

        if (message.equals("Tagtypen er slettet"))
        {
            List<RoofType> roofTypes = manager.getRoofs();
            session.setAttribute("rooftypes", roofTypes);
        }
        return target;
    }
}


