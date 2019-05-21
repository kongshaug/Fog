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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author aamandajuhl
 */
public class EmployeeInfoCommand implements Command
{
    private String target;
    private String denied;

    public EmployeeInfoCommand(String target, String denied)
    {
        this.target = target;
        this.denied = denied;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, FunctionManager manager) throws CommandException, DataException
    {
        HttpSession session = request.getSession();
        int user_id = Integer.parseInt(request.getParameter("selected"));
        User user = (User) session.getAttribute("user");

        if (user.getRole().equals(Role.ADMIN))
        {
            User employee = manager.getUser(user_id);
            session.setAttribute("employee", employee);

            return target;

        } else
        {
            request.setAttribute("message", "Adgang nægtet");
            return denied;
        }
    }
}
