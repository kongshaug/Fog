/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentaionLayer;

import DataLayer.DataException;
import FunctionLayer.FunctionManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sofieamalielandt
 */
public class TargetCommand implements Command
{
    private String target;
    
    /**
     * Ininitializing target and denied for the command
     * 
     * @param target a String, reference to jsp
     */
    public TargetCommand(String target)
    {
        this.target = target;
    }

    /**
     * 
     * This command return to a jsp.
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
        return target;
    } 
}
