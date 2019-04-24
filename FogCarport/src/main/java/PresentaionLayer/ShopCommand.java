/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentaionLayer;

import FunctionLayer.FunctionManager;
import FunctionLayer.RoofType;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sofieamalielandt
 */
public class ShopCommand implements Command
{
    private String target;

    public ShopCommand(String target)
    {
        this.target = target;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, FunctionManager manager) throws CommandException, DataLayer.DataException
    {
        HttpSession session = request.getSession();
        List<RoofType> rooftypes = manager.getRoofs();
        RoofType roof = manager.getRoof();

        session.setAttribute("roofs", rooftypes);
        session.setAttribute("roof", roof);

        return target;
    }

}
