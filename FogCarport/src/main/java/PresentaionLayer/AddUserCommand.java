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

    /**
     * Ininitializing target and denied for the command
     * 
     * @param target a String, reference to jsp
     * @param error a String, reference to jsp
     * @param noUser a String, reference to jsp
     */
    public AddUserCommand(String target, String error, String noUser)
    {
        this.target = target;
        this.error = error;
        this.noUser = noUser;

    }
    
    /**
     * 
     * Retrieves parameters email, name, address, zipcode and phone, creates object User
     * and adds object as customer to the database, a string is returned as repsonse -
     * forwards to index.jsp 
     * If a user went to shop before logged in - forards to noUser.jsp
     * If an error occurs a String is returnd as response - forwards to addrooftype.jsp 
     * 
     * @param request a HttpServletRequest
     * @param response a HttpServletResponse
     * @param manager an instance of FunctionManager
     * @return target, error or noUser
     * @throws CommandException if an error occours
     * @throws DataException if retrievel was not possible 
     */
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
