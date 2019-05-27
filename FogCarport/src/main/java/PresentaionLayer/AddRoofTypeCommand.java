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
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author aamandajuhl
 */
public class AddRoofTypeCommand implements Command
{
    private String target;
    private String error;

    /**
     * Ininitializing target and denied for the command
     * 
     * @param target a String, reference to jsp
     * @param error a String, reference to jsp
     */
    public AddRoofTypeCommand(String target, String error)
    {
        this.error = error;
        this.target = target;
    }

    /**
     * 
     * Retrieves parameters name, material1, material2 and roof_class creates, object RoofType
     * and adds object to the database, a string is returned as repsonse -
     * forwards to materials.jsp 
     * If an error occurs a String is returnd as response - forwards to addrooftype.jsp 
     * 
     * @param request a HttpServletRequest
     * @param response a HttpServletResponse
     * @param manager an instance of FunctionManager
     * @return target or error
     * @throws CommandException if an error occours
     * @throws DataException if retrievel was not possible 
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, FunctionManager manager) throws CommandException, DataException
    {
        HttpSession session = request.getSession();
        RoofType rooftype;
        String message = "";
        String name = request.getParameter("name");
        String m1_str = request.getParameter("material1");
        String m2_str = request.getParameter("material2");
        String roof_class = request.getParameter("roof_class");

        if (name == null)
        {
            message = "Venligst indtast navn på tagtypen";
            request.setAttribute("message", message);
            return error;
        }
        if (m1_str == null || m2_str == null)
        {
            message = "Vælg venligst materialer til tagtypen";
            request.setAttribute("message", message);
            return error;
        }
        if (roof_class == null)
        {
            message = "Vælg venligst kategori";
            request.setAttribute("message", message);
            return error;
        }

        int m1 = Integer.parseInt(m1_str);
        int m2 = Integer.parseInt(m2_str);
        Material material1 = manager.getMaterial(m1);
        
        if (m2 != 0)
        {
            Material material2 = manager.getMaterial(m2);
            rooftype = new RoofType(name, roof_class, material1, material2);
            
        } else
        {
            rooftype = new RoofType(name, roof_class, material1);
        }

        message = manager.addRoofType(rooftype);
        request.setAttribute("message", message);

        if (message.equals("Tagtype er tilføjet"))
        {  
            List<RoofType> rooftypes = manager.getRoofs();
            session.setAttribute("rooftypes", rooftypes);
            return target;

        } else
        {
            return error;
        }
    }

}
