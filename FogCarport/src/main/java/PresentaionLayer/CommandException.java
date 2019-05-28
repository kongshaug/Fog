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

    /**
     * This is a customized Exception class that executes target as
     * error.jsp, if an error occurs
     * 
     * @param message the message that will be shown, if an error occurs
     */
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
