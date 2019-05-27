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
public class EmployeeUpdateCommand implements Command
{

    private String target;
    private String denied;

    public EmployeeUpdateCommand(String target, String denied)
    {
        this.target = target;
        this.denied = denied;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, FunctionManager manager) throws CommandException, DataException
    {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user.getRole().equals(Role.ADMIN))
        {
            User employee = (User) session.getAttribute("employee");
            String email = request.getParameter("email");
            String name = request.getParameter("name");
            String address = request.getParameter("address");
            String zipcode = request.getParameter("zipcode");
            String phone = request.getParameter("phone");

            String message = manager.updateEmployee(employee, email, name, address, zipcode, phone);

            request.setAttribute("message", message);
            return target;
            
        } else
        {
            request.setAttribute("message", "Adgang nægtet");
            return denied;
        }
    }
}
