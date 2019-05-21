/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentaionLayer;

import DataLayer.DataException;
import FunctionLayer.FunctionManager;
import FunctionLayer.Enum.Role;
import FunctionLayer.HelpingClasses.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author aamandajuhl
 */
public class AddUserCommand implements Command
{
    private String target;
    private String error;
    private String noUser;

    public AddUserCommand(String target, String error, String noUser)
    {
        this.target = target;
        this.error = error;
        this.noUser = noUser;

    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, FunctionManager manager) throws CommandException, DataException
    {
        HttpSession session = request.getSession();

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String zipcode = request.getParameter("zipcode");
        String phone = request.getParameter("phone");

        User user = new User(email, password, name, address, zipcode, phone, Role.CUSTOMER);
        String message = manager.newUser(user);

        request.setAttribute("message", message);

        if (message.equals("Din bruger er nu oprettet"))
        {
            if (session.getAttribute("carport") != null)
            {
                return noUser;
            } else
            {
                return target;
            }
        } else
        {
            return error;
        }
    }
}
