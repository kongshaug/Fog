/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer.HelpingClasses;

import java.util.ArrayList;

/**
 *
 * @author sofieamalielandt
 */
public class Carport
{

    private int id;
    private int width;
    private int depth;
    private ArrayList<Part> parts;
    private Roof roof;
    private Shed shed;
    private double total_price;

    /**
     * constructor for new carport with shed
     * @param width int
     * @param depth int 
     * @param roof roof Object
     * @param shed Roof Object
     */
    public Carport(int width, int depth, Roof roof, Shed shed)
    {
        this.width = width;
        this.depth = depth;
        this.roof = roof;
        this.shed = shed;
        this.parts = new ArrayList<>();
        this.total_price = 0.0;
    }

    /**
     * Constructor for new carport without shed
     * @param width int
     * @param depth int
     * @param roof roof object
     */
    public Carport(int width, int depth, Roof roof)
    {
        this.width = width;
        this.depth = depth;
        this.roof = roof;
        this.parts = new ArrayList<>();
        this.total_price = 0.0;
    }

    /**
     * constructor for carport from database with id assigned with shed
     * @param id int
     * @param width int
     * @param depth int
     * @param roof roof object
     * @param shed shed object
     */
    public Carport(int id, int width, int depth, Roof roof, Shed shed)
    {
        this.id = id;
        this.width = width;
        this.depth = depth;
        this.roof = roof;
        this.shed = shed;
        this.parts = new ArrayList<>();
        this.total_price = 0.0;
    }

    /**
     * constructor for carport object from database with id without shed
     * @param id int
     * @param width int
     * @param depth int 
     * @param roof roof object
     */
    public Carport(int id, int width, int depth, Roof roof)
    {
        this.id = id;
        this.width = width;
        this.depth = depth;
        this.roof = roof;
        this.parts = new ArrayList<>();
        this.total_price = 0.0;
    }

    /**
     * 
     * @return id of carport
     */
    public int getId()
    {
        return id;
    }

    /**
     * sets the id of a carport
     * @param id 
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     *
     * @return width of carport
     */
    public int getWidth()
    {
        return width;
    }

    /**
     * sets the width of a carport
     * @param width 
     */
    public void setWidth(int width)
    {
        this.width = width;
    }

    /**
     *
     * @return depth of a carport
     */
    public int getDepth()
    {
        return depth;
    }

    /**
     * sets the depth of a carport
     * @param depth
     */
    public void setDepth(int depth)
    {
        this.depth = depth;
    }

    /**
     *
     * @return the roof of a carport
     */
    public Roof getRoof()
    {
        return roof;
    }

    /**
     * sets the roof of a carport on the carport object
     * @param roof
     */
    public void setRoof(Roof roof)
    {
        this.roof = roof;
    }

    /**
     * 
     * @return the shed of  a carport
     */
    public Shed getShed()
    {
        return shed;
    }

    /**
     * sets the shed of a carport on a carport
     * @param shed 
     */
    public void setShed(Shed shed)
    {
        this.shed = shed;
    }

    /**
     *
     * @return a list of the parts needed to make the carport
     */
    public ArrayList<Part> getParts()
    {
        return parts;
    }

    /**
     * sets the list of materials needed to make a carport on a carport
     * @param parts
     */
    public void setParts(ArrayList<Part> parts)
    {
        this.parts = parts;
    }

    /**
     * Empty the list of parts needed to make the carport
     */
    public void resetParts()
    {
        this.parts = new ArrayList<>();
    }

    private double calcTotalPrice()
    {
        double totalPrice = 0.0;
        for (Part part : parts)
        {
            totalPrice += part.getTotal_price();
        }
        for (Part part : getRoof().getParts())
        {
            totalPrice += part.getTotal_price();
        }
        if (shed != null)
        {
            for (Part part : getShed().getParts())
            {
                totalPrice += part.getTotal_price();
            }
        }
        return totalPrice;
    }

    /**
     *
     * @return The total price of the carport
     */
    public double getTotal_price()
    {
        total_price = calcTotalPrice();
        total_price = Math.round(total_price * 100.0) / 100.0;
        return total_price;
    }
    
    /**
     * Change the price of a carport
     * @param total_price integer
     */
    public void setTotal_price(double total_price)
    {
        this.total_price = total_price;
    }
}
