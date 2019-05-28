/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentaionLayer;

import DataLayer.DataException;
import FunctionLayer.FunctionManager;
import FunctionLayer.HelpingClasses.Material;
import FunctionLayer.HelpingClasses.RoofType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sofieamalielandt
 */
public class UpdateRooftypeCommand implements Command
{

    private String target;

    /**
     * Ininitializing target and denied for the command
     * 
     * @param target a String, reference to jsp
     */
    public UpdateRooftypeCommand(String target)
    {
        this.target = target;
    }

    /**
     * 
     * Retrieves attribute from session and parameters name, material1 and material2 from request
     * the two materials are retrieved from database and the rooftype is updated in the database
     * as well, a String is returned as response and forward to rooftype.jsp
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
        RoofType rooftype = (RoofType) session.getAttribute("rooftype");

        String message = "";
        String name = request.getParameter("name");
        String m1_str = request.getParameter("material1");
        String m2_str = request.getParameter("material2");

        if (m1_str != null && m2_str != null & name != null)
        {
            int m1 = Integer.parseInt(m1_str);
            int m2 = Integer.parseInt(m2_str);
            Material material1 = manager.getMaterial(m1);
            Material material2 = null;

            if (m2 != 0)
            {
                material2 = manager.getMaterial(m2);
            }
            
            message = manager.updateRoofType(rooftype, name, material1, material2);

            request.setAttribute("message", message);
            return target;

        } else
        {
            request.setAttribute("message", "Vælg venligst materialer til tagtypen");
            return target;
        }
    }
}
