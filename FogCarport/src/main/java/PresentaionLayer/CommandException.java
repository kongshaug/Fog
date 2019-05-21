/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentaionLayer;

/**
 *
 * @author sofieamalielandt
 */
public class CommandException extends Exception
{
    private final String target;

    public CommandException(String message)
    {
        super(message);
        this.target = "error.jsp";
    }

    public final String getTarget()
    {
        return target;
    }
}
