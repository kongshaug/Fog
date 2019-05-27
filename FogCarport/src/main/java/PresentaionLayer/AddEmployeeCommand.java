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
 * @author sofieamalielandt
 */
public class AddEmployeeCommand implements Command
{

    private String target;
    private String denied;

    public AddEmployeeCommand(String target, String denied)
    {
        this.target = target;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, FunctionManager manager) throws CommandException, DataException
    {
        HttpSession session = request.getSession();
        String password = request.getParameter("password");
        User user = (User) session.getAttribute("user");

        if (password.equals(user.getPassword()) && user.getRole().equals(Role.ADMIN) || password.equals(user.getPassword()) && user.getRole().equals(Role.EMPLOYEE))
        {
            String email = request.getParameter("email");
            String name = request.getParameter("name");
            String address = request.getParameter("address");
            String zipcode = request.getParameter("zipcode");
            String phone = request.getParameter("phone");
            String choosen_role = request.getParameter("role");
            Role role;

            if (choosen_role.equals("Administrator"))
            {
                role = Role.ADMIN;
            } else
            {
                role = Role.EMPLOYEE;
            }

            User employee = new User(email, "1234", name, address, zipcode, phone, role);
            String message = manager.newUser(employee);

            request.setAttribute("message", message);

            return target;
        } else
        {
            request.setAttribute("message", "Adgang nægtet");
            return denied;
        }
    }

}
