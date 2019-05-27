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

    /**
     * Ininitializing target and denied for the command
     * 
     * @param target a String, reference to jsp
     * @param denied a String, reference to jsp
     */
    public AddEmployeeCommand(String target, String denied)
    {
        this.target = target;
        this.denied = denied;
    }

    /**
     * 
     * If access is denied, forwards to employee.jsp 
     * Retrieves parameters email, name, address, zipcode, phone and role, creates object User
     * and adds object as employee to the database, a string is returned as repsonse -
     * forwards to addemployee.jsp 
     * 
     * @param request a HttpServletRequest
     * @param response a HttpServletResponse
     * @param manager an instance of FunctionManager
     * @return target or denied
     * @throws CommandException if an error occours
     * @throws DataException if retrievel was not possible 
     * @see FunctionLayer.FunctionManager#newUser(FunctionLayer.HelpingClasses.User) 
     */
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
