/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentaionLayer;

import DataLayer.DataException;
import FunctionLayer.Carport;
import FunctionLayer.FunctionManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author aamandajuhl
 */
public class PartlistCommand implements Command
{
    private String target;

    public PartlistCommand(String target)
    {
        this.target = target;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, FunctionManager manager) throws CommandException, DataException
    {
        int depth = Integer.parseInt(request.getParameter("depth"));
        int width = Integer.parseInt(request.getParameter("width"));
        Carport carport = new Carport(width, depth, null);
        
        manager.calCarport(carport);
        
        
        return target;
    }
    
}
