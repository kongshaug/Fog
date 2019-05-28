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

/**
 *
 * @author sofieamalielandt
 */
public class DeleteMaterialCommand implements Command
{
    private String target;

    /**
     * Ininitializing target and denied for the command
     * 
     * @param target a String, reference to jsp
     */
    public DeleteMaterialCommand(String target)
    {
        this.target = target;
    }

    /**
     * Retrieves and checks, parameter material from request, if material null 
     * a String is returned as response - forward to materials.jsp
     * !if material null, an object of Material is recieved and deleted from the database
     * a String is retuned as response and the new list of materials is retrieved - forward to materials.jsp
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
        String material_str = request.getParameter("material");

        if (material_str == null)
        {
            request.setAttribute("message", "Vælg venligst et materiale");
            return target;
        }

        int material_id = Integer.parseInt(material_str);
        Material material = manager.getMaterial(material_id);

        String message = manager.deleteMaterial(material);
        request.setAttribute("message", message);

        if (message.equals("Materialet er slettet"))
        {
            List<Material> materials = manager.getAllMaterials();
            request.setAttribute("materials", materials);
        }
        return target;
    }
}
