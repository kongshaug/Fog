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

/**
 *
 * @author sofieamalielandt
 */
public class AddEmployeeCommand implements Command
{
    private String target;

    public AddEmployeeCommand(String target)
    {
        this.target = target;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, FunctionManager manager) throws CommandException, DataException
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

        User user = new User(email, "1234", name, address, zipcode, phone, role);
        String message = manager.newUser(user);

        request.setAttribute("message", message);

        return target;
    }
}
