/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentaionLayer;

import DataLayer.DataException;
import FunctionLayer.Enum.Role;
import FunctionLayer.FunctionManager;
import FunctionLayer.HelpingClasses.Material;
import FunctionLayer.HelpingClasses.User;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sofieamalielandt
 */
public class MaterialsCommand implements Command
{
    private String target;
    private String denied;

    /**
     * Ininitializing target and denied for the command
     * 
     * @param target a String, reference to jsp
     * @param denied a String, reference to jsp
     */
    public MaterialsCommand(String target, String denied)
    {
        this.target = target;
        this.denied = denied;
    }
    
    /**
     * 
     * If access is denied, forwards to employee.jsp 
     * Retrieves parameter user from session and password from request
     * and retrieves and saves, a list materials of the object Material 
     * from database, on request
     * - forward to materials.jsp
     * 
     * @param request a HttpServletRequest
     * @param response a HttpServletResponse
     * @param manager an instance of FunctionManager
     * @return target or denied 
     * @throws CommandException if an error occours
     * @throws DataException if retrievel was not possible 
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, FunctionManager manager) throws CommandException, DataException
    {
        HttpSession session = request.getSession();
        String password = request.getParameter("password");
        User user = (User) session.getAttribute("user");

        if (password.equals(user.getPassword()) && user.getRole().equals(Role.ADMIN) || password.equals(user.getPassword()) && user.getRole().equals(Role.EMPLOYEE))
        {
            List<Material> materials = manager.getAllMaterials();
            session.setAttribute("materials", materials);
            return target;
        } else
        {
            request.setAttribute("message", "Adgang nægtet");
            return denied;
        }
    }
}
