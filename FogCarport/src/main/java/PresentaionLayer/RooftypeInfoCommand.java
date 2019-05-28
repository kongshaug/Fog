/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentaionLayer;

import DataLayer.DataException;
import FunctionLayer.Enum.Role;
import FunctionLayer.FunctionManager;
import FunctionLayer.HelpingClasses.RoofType;
import FunctionLayer.HelpingClasses.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sofieamalielandt
 */
public class RooftypeInfoCommand implements Command
{
    private String target;

    /**
     * Ininitializing target and denied for the command
     * 
     * @param target a String, reference to jsp
     */
    public RooftypeInfoCommand(String target)
    {
        this.target = target;
    }

    /**
     * 
     * Retrieves the user from session and checks the role of user, if the rooftype
     * from request is empty a String is returned and forwatd to rooftypes.jsp
     * else the specific rooftype with a rooftype_id is retrieved from the database
     * and saved on session - forward to rooftype.jsp 
     * if access is denied forward to shop.jsp
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
            String rooftype_str = request.getParameter("rooftype");

            if (rooftype_str == null)
            {
                request.setAttribute("message", "Vælg venligst en tagtype");
                return "rooftypes.jsp";
            }

            int rooftype_id = Integer.parseInt(rooftype_str);
            RoofType rooftype = manager.getRoofTypeById(rooftype_id);
            session.setAttribute("rooftype", rooftype);
            return target;

        } else
        {
            request.setAttribute("errormessage", "Adgang nægtet");
            return "shop.jsp";
        }
    }
}
