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

    public MaterialsCommand(String target, String denied)
    {
        this.target = target;
        this.denied = denied;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, FunctionManager manager) throws CommandException, DataException
    {
        HttpSession session = request.getSession();
        String password = request.getParameter("password");
        User user = (User) session.getAttribute("user");

        if (password.equals(user.getPassword()) && user.getRole().equals(Role.ADMIN) || password.equals(user.getPassword()) && user.getRole().equals(Role.EMPLOYEE))
        {
            List<Material> materials = manager.getAllMaterials();
            request.setAttribute("materials", materials);
            return target;
        } else
        {
            request.setAttribute("message", "Adgang nægtet");
            return denied;
        }
    }
}
