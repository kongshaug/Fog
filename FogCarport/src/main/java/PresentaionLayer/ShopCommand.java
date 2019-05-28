/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentaionLayer;

import DataLayer.DataException;
import FunctionLayer.FunctionManager;
import FunctionLayer.HelpingClasses.RoofType;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sofieamalielandt
 */
public class ShopCommand implements Command
{
    private String target;

    /**
     * Ininitializing target and denied for the command
     * 
     * @param target a String, reference to jsp
     */
    public ShopCommand(String target)
    {
        this.target = target;
    }

    /**
     * 
     * Retrieves the rooftypes slopedRoofs and flatRoofs from database as two lists
     * - these two list are saved on request - forward to shop.jsp
     * 
     * @param request a HttpServletRequest
     * @param response a HttpServletResponse
     * @param manager an instance of FunctionManager
     * @return target
     * @throws CommandException if an error occours
     * @throws DataException if retrievel was not possible  
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, FunctionManager manager) throws CommandException, DataException
    {
        List<RoofType> slopedRoofs = manager.getSlopedRoofs();
        List<RoofType> flatRoofs = manager.getFlatRoofs();
        request.setAttribute("slopedroofs", slopedRoofs);
        request.setAttribute("flatroofs", flatRoofs);

        return target;
    }
}
