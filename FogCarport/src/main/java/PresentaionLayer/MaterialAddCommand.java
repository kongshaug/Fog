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

    public MaterialAddCommand(String target)
    {
        this.target = target;
    }

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
