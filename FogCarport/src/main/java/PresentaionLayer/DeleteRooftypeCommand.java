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

/**
 *
 * @author sofieamalielandt
 */
public class DeleteRooftypeCommand implements Command
{
    private String target;

    /**
     * Ininitializing target and denied for the command
     * 
     * @param target a String, reference to jsp
     */
    public DeleteRooftypeCommand(String target)
    {
        this.target = target;
    }

    /**
     * 
     * Retrieves and checks, parameter rooftype from request, if rooftype null 
     * a String is returned as response - forward to rooftypes.jsp
     * An object of RoofType is recieved and deleted from the database
     * a String is retuned as response and the new list of rooftypes is retrieved - forward to rooftypes.jsp
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
        String rooftype_str = request.getParameter("rooftype");

        if (rooftype_str == null)
        {
            request.setAttribute("message", "Vælg venligst et tagtype");
            return target;
        }

        int material_id = Integer.parseInt(rooftype_str);
        RoofType Rooftype = manager.getRoofTypeById(material_id);

        String message = manager.deleteRoofType(Rooftype);
        request.setAttribute("message", message);

        if (message.equals("Tagtypen er slettet"))
        {
            List<RoofType> roofTypes = manager.getRoofs();
            request.setAttribute("rooftypes", roofTypes);
        }
        return target;
    }
}
