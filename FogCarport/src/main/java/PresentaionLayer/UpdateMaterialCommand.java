/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentaionLayer;

import DataLayer.DataException;
import FunctionLayer.FunctionManager;
import FunctionLayer.HelpingClasses.Material;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sofieamalielandt
 */
public class UpdateMaterialCommand implements Command
{
    private String target;
    
    /**
     * Ininitializing target and denied for the command
     * 
     * @param target a String, reference to jsp
     */
    public UpdateMaterialCommand(String target)
    {
        this.target = target;
    }

    /**
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
        Material material = (Material) session.getAttribute("material");
        String name = request.getParameter("name");
        String unit = request.getParameter("unit");
        String material_class = request.getParameter("material_class");
        double price = Double.parseDouble(request.getParameter("price"));
        
        String message = manager.updateMaterial(material, name, unit, material_class, price);
        request.setAttribute("message", message);
        
        return target; 
    }
}
