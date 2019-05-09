/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentaionLayer;

import DataLayer.DataException;
import FunctionLayer.FunctionManager;
import FunctionLayer.HelpingClasses.User;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sofieamalielandt
 */
public class DeleteEmployeeCommand implements Command
{

    private String target;

    public DeleteEmployeeCommand(String target)
    {
        this.target = target;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, FunctionManager manager) throws CommandException, DataException
    {
        HttpSession session = request.getSession();
        User employee = (User) session.getAttribute("employee");

        session.removeAttribute("employee");
        session.removeAttribute("users");
        
        String message = manager.removeUser(employee);

        request.setAttribute("message", message);
        List<User> users = manager.getEmployeesAndAdmins();
        session.setAttribute("users", users);

        return target;

    }

}
