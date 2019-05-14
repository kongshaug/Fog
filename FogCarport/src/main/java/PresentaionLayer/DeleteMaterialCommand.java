/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentaionLayer;

import DataLayer.DataException;
import FunctionLayer.FunctionManager;
import FunctionLayer.HelpingClasses.Material;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sofieamalielandt
 */
public class DeleteMaterialCommand implements Command
{

    private String target;

    public DeleteMaterialCommand(String target)
    {
        this.target = target;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, FunctionManager manager) throws CommandException, DataException
    {
        HttpSession session = request.getSession();

        String material_str = request.getParameter("material");
        
        if(material_str == null)
        {
            request.setAttribute("message", "Vælg venligst et materiale");
            return "materials.jsp";
        }
        
        int material_id = Integer.parseInt(material_str);
        Material material = manager.getMaterial(material_id);

        String message = manager.deleteMaterial(material);
        request.setAttribute("message", message);

        if (message.equals("Materialet er slettet"))
        {
            List<Material> materials = manager.getAllMaterials();
            session.setAttribute("materials", materials);
        }
        return target;
    }

}
