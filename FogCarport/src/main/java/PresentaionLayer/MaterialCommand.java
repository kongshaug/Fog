/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentaionLayer;

import DataLayer.DataException;
import FunctionLayer.FunctionManager;
import FunctionLayer.HelpingClasses.Material;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sofieamalielandt
 */
public class MaterialCommand implements Command
{
    private String target;
    
    public MaterialCommand(String target)
    {
        this.target = target;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, FunctionManager manager) throws CommandException, DataException
    {
        HttpSession session = request.getSession();
        int material_id = Integer.parseInt(request.getParameter("material"));
        Material material = manager.getMaterial(material_id);
        List<String> material_classes = new ArrayList<>();
        material_classes.add("træ");
        material_classes.add("tag");
        material_classes.add("beslag og skruer");
        
        session.setAttribute("material", material);
        session.setAttribute("material_classes", material_classes);
        
        return target;
        
    }
    
}
