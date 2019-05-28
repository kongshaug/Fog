/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentaionLayer;

import DataLayer.DataException;
import FunctionLayer.Enum.Role;
import FunctionLayer.FunctionManager;
import FunctionLayer.HelpingClasses.User;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sofieamalielandt
 */
public class MaterialAddCommand implements Command
{
    private String target;

    /**
     * Ininitializing target and denied for the command
     * 
     * @param target a String, reference to jsp
     */
    public MaterialAddCommand(String target)
    {
        this.target = target;
    }

    /**
     * 
     * Retrieves attribute user from session and a list of added material_classes is saved
     * in session and forward to addmaterials.jsp
     * If access denied - forward to shop.jsp
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
        User user = (User) session.getAttribute("user");

        if (user.getRole().equals(Role.ADMIN) || user.getRole().equals(Role.EMPLOYEE))
        {
            List<String> material_classes = new ArrayList<>();
            material_classes.add("træ");
            material_classes.add("tag");
            material_classes.add("beslag og skruer");

            session.setAttribute("material_classes", material_classes);
            return target;
        } else
        {
            request.setAttribute("errormessage", "Adgang nægtet");
            return "shop.jsp";
        }
    }

}
