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
public class AddMaterialCommand implements Command
{
    private String target;
    private String error;

    /**
     * Ininitializing target and denied for the command
     * 
     * @param target a String, reference to jsp
     * @param error a String, reference to jsp
     */
    public AddMaterialCommand(String target, String error)
    {
        this.target = target;
        this.error = error;
    }

    /**
     * If access is denied, forwards to addmaterial.jsp 
     * Retrieves parameters name, unit, material_class and price and creates object Materisl
     * and adds object to the database, a string is returned as repsonse -
     * forwards to materials.jsp 
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

        String name = request.getParameter("name");
        String unit = request.getParameter("unit");
        String material_class = request.getParameter("material_class");
        double price = Double.parseDouble(request.getParameter("price"));

        Material material = new Material(name, unit, material_class, price);

        String message = manager.addMaterial(material);
        request.setAttribute("message", message);

        if (message.equals("Materialet er tilføjet til listen"))
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
