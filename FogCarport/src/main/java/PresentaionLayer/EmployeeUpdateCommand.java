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

    /**
     * Ininitializing target and denied for the command
     * 
     * @param target a String, reference to jsp
     * @param denied a String, reference to jsp
     */
    public EmployeeUpdateCommand(String target, String denied)
    {
        this.target = target;
        this.denied = denied;
    }

    /**
     * 
     * Retrieves parameters email, name, address, zipcode and phone,
     * user is retrieved from session and updates the object User in the database, 
     * a string is returned as repsonse - forwards to employeeinfo.jsp 
     * If access denied - forward to employee.jsp
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
