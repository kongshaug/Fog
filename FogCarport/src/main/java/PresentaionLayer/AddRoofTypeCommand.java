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

    public AddRoofTypeCommand(String target, String error)
    {
        this.error = error;
        this.target = target;
    }

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
        
        if(name == null)
        {
            message = "Venligst indtast navn på tagtypen";
            request.setAttribute("message", message);
            return error;
        }
        if(m1_str == null || m2_str == null)
        {
            message = "Vælg venligst materialer til tagtypen";
            request.setAttribute("message", message);
            return error;
        }
        if(roof_class == null)
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
            List<Material> materials = manager.getAllMaterials();
            session.setAttribute("materials", materials);
            return target;
            
        } else
        {
            return error;
        }
    }

}
